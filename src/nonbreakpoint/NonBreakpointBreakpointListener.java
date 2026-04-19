package nonbreakpoint;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IResource;
import org.eclipse.debug.core.IBreakpointListener;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.jdt.debug.core.IJavaLineBreakpoint;

/**
 * Watches for breakpoint additions and automatically removes any non-breakpoint
 * on the same line to prevent conflicts.
 *
 * Breakpoints take priority over non-breakpoints. When a breakpoint is added
 * to a line that is already marked as a non-breakpoint, the non-breakpoint is
 * removed so that the two annotations never coexist on the same line.
 */
public class NonBreakpointBreakpointListener implements IBreakpointListener {

    /**
     * Called when a breakpoint is added.
     * If the same line is marked as a non-breakpoint, the non-breakpoint is removed.
     *
     * @param breakpoint the breakpoint that was added
     */
    @Override
    public void breakpointAdded(IBreakpoint breakpoint) {
        if (!(breakpoint instanceof IJavaLineBreakpoint)) return;
        try {
            IMarker marker = breakpoint.getMarker();
            IResource resource = marker.getResource();
            int lineNumber = ((IJavaLineBreakpoint) breakpoint).getLineNumber();
            String key = resource.getFullPath().toString() + ":" + lineNumber;
            if (NonBreakpointRegistry.isMarked(key)) {
                NonBreakpointRegistry.toggle(key);
                NonBreakpointMarkerManager.removeMarker(resource, lineNumber);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** No action needed when a breakpoint is removed. */
    @Override
    public void breakpointRemoved(IBreakpoint breakpoint, IMarkerDelta delta) {
    }

    /** No action needed when a breakpoint is changed. */
    @Override
    public void breakpointChanged(IBreakpoint breakpoint, IMarkerDelta delta) {
    }
}
