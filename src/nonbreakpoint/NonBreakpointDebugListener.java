package nonbreakpoint;

import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.jdt.debug.core.IJavaStackFrame;

/**
 * Monitors debug events and automatically steps over lines marked as non-breakpoints.
 *
 * Eclipse notifies debug events through the IDebugEventSetListener interface.
 * Once registered with DebugPlugin, handleDebugEvents() is called every time
 * the program suspends.
 *
 * How it works:
 *   1. Check that the event is a SUSPEND event with detail STEP_END.
 *   2. Retrieve the current line number and source path from the top stack frame.
 *   3. Match against the keys registered in NonBreakpointRegistry.
 *   4. If a match is found, call stepInto() to continue stepping into called methods.
 *      If the resulting frame is inside JDK-internal code, stepInto() is called
 *      repeatedly until user code is reached.
 */
public class NonBreakpointDebugListener implements IDebugEventSetListener {
	private volatile boolean autoStepping = false;
	private boolean isJdkInternal(IJavaStackFrame frame) {
		try {
			String typeName = frame.getDeclaringTypeName();
			return typeName.startsWith("java.")
					|| typeName.startsWith("javax.")
					|| typeName.startsWith("sun.")
					|| typeName.startsWith("com.sun.")
					|| typeName.startsWith("jdk.");
		}catch(Exception e) {
			return false;
		}
		
	}
	
    /**
     * Called by Eclipse whenever one or more debug events occur.
     *
     * Multiple events may be delivered together in an array, so they are
     * processed in a loop. Only "suspension after a step operation" events
     * are of interest here; all others (RESUME, TERMINATE, etc.) are ignored.
     *
     * @param events array of debug events that have occurred
     */
    @Override
    public void handleDebugEvents(DebugEvent[] events) {
        for (DebugEvent event : events) {

            // Only handle SUSPEND events caused by a step operation (STEP_END).
            // Suspensions caused by breakpoints (BREAKPOINT) are intentionally ignored.
            if (event.getKind() == DebugEvent.SUSPEND
                    && event.getDetail() == DebugEvent.STEP_END) {

                Object source = event.getSource();

                // Skip if the event source is not a thread (safety type check).
                if (!(source instanceof IThread)) continue;
                IThread thread = (IThread) source;

                try {
                    // The top stack frame holds information about the currently suspended line.
                    IStackFrame frame = thread.getTopStackFrame();

                    // Skip non-Java stack frames (e.g. Groovy or other JVM languages).
                    if (!(frame instanceof IJavaStackFrame)) continue;

                    IJavaStackFrame javaFrame = (IJavaStackFrame) frame;

                    // Retrieve the current line number and package-relative source path.
                    // Example: lineNumber=42, path="com/example/Foo.java"
                    int lineNumber = javaFrame.getLineNumber();
                    String path = javaFrame.getSourcePath();

                    // Build a suffix key matching the registry format ("path:lineNumber")
                    // and perform a suffix match, because getSourcePath() returns a
                    // package-relative path while registry keys use full project paths.
                    String key = path.replace("\\", "/") + ":" + lineNumber;

                    boolean hit = false;
                    for (String marked : NonBreakpointRegistry.getAll()) {
                        if (marked.endsWith(key)) {
                            hit = true;
                            break;
                        }
                    }

                    // If execution stopped on a non-breakpoint line, step into it automatically.
                    // If the step lands inside JDK-internal code, keep stepping into until
                    // user code is reached.
                    if (hit) {
                    	autoStepping = true;
                    	thread.stepInto();
                    }else if(autoStepping) {
                    	if(isJdkInternal(javaFrame)) {
                        	thread.stepInto();
                    	}else {
                        	autoStepping = false;
                    	}
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
