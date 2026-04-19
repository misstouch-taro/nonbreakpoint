package nonbreakpoint;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.debug.core.IJavaLineBreakpoint;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.texteditor.ITextEditor;

/**
 * Handler executed when the "Toggle Non-Breakpoint" menu item or keyboard
 * shortcut (Ctrl+Shift+N) is invoked by the user.
 *
 * In Eclipse's command framework, this class is bound to the toggle command
 * via the &lt;handler&gt; element in plugin.xml, and execute() is called
 * whenever the user triggers the command.
 *
 * How it works:
 *   1. Retrieve the active editor and the selected line range.
 *   2. Obtain the IResource for the open Java file.
 *   3. For each line in the selection, toggle the "filePath:lineNumber" key
 *      in NonBreakpointRegistry.
 *   4. Add or remove the editor marker according to the new state.
 *
 * When multiple lines are selected, all lines are toggled in the same direction
 * as the first (start) line. Lines that already conflict with a regular
 * breakpoint are skipped, and a single warning dialog is shown at the end.
 */
public class ToggleNonBreakpointHandler extends AbstractHandler {

    /**
     * Called by Eclipse when the command is executed.
     *
     * Extending AbstractHandler provides default implementations of
     * isEnabled() and isHandled() that always return {@code true}.
     * This method always returns {@code null} (reserved for future use
     * by the command framework).
     *
     * @param event the execution event containing the current UI context
     * @return always {@code null} (as required by the command framework)
     */
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {

        // HandlerUtil provides access to the current UI state, such as the active editor.
        IEditorPart editor = HandlerUtil.getActiveEditor(event);

        // Do nothing if the active part is not a text editor (e.g. a visual designer).
        if (!(editor instanceof ITextEditor)) return null;

        ITextEditor textEditor = (ITextEditor) editor;

        // Retrieve the current text selection.
        // Even a bare cursor position is returned as an ITextSelection with length 0.
        ISelection selection = textEditor.getSelectionProvider().getSelection();
        if (!(selection instanceof ITextSelection)) return null;

        ITextSelection textSelection = (ITextSelection) selection;

        // getStartLine() and getEndLine() are both 0-based, so add 1 to get the
        // 1-based line numbers shown in the editor.
        // When no text is selected, startLine == endLine and only one line is processed.
        int startLine = textSelection.getStartLine() + 1;
        int endLine   = textSelection.getEndLine()   + 1;

        // getEditorInputJavaElement() returns the ICompilationUnit (the JDT model
        // object for a single .java file) for the file open in the editor.
        // Returns null for non-Java files (.xml etc.), so return early in that case.
        ICompilationUnit cu = (ICompilationUnit) JavaUI.getEditorInputJavaElement(editor.getEditorInput());
        if (cu == null) return null;

        // ICompilationUnit is not a subtype of IResource, so an explicit cast is needed.
        IResource resource = (IResource) cu.getResource();

        // Compute the file path portion of the registry key once, outside the loop.
        // Example: /MyProject/src/com/example/Foo.java
        String filePath = cu.getResource().getFullPath().toString();

        // Capture the initial state of the start line before the loop begins.
        // This value must not change during iteration, as toggling the start line
        // in the first iteration would otherwise corrupt the condition for subsequent lines.
        String startLineKey   = filePath + ":" + startLine;
        boolean startLineMarked = NonBreakpointRegistry.isMarked(startLineKey);

        // Process each line in the selection.
        // All lines are toggled in the same direction as the start line:
        //   - If the start line was ON  → turn ON  lines OFF (skip lines already OFF).
        //   - If the start line was OFF → turn OFF lines ON  (skip lines already ON),
        //     unless a regular breakpoint exists on that line.
        int skippedCount = 0;
        for (int lineNumber = startLine; lineNumber <= endLine; lineNumber++) {
            String key = filePath + ":" + lineNumber;

            if (startLineMarked && NonBreakpointRegistry.isMarked(key)) {
                // Start line was ON → remove the non-breakpoint from this line.
                NonBreakpointRegistry.toggle(key);
                NonBreakpointMarkerManager.removeMarker(resource, lineNumber);

            } else if (!startLineMarked && !NonBreakpointRegistry.isMarked(key)) {
                // Start line was OFF → add a non-breakpoint to this line,
                // but only if no regular breakpoint is already set here.
                if (hasBreakpoint(resource, lineNumber)) {
                    skippedCount++;
                } else {
                    NonBreakpointRegistry.toggle(key);
                    NonBreakpointMarkerManager.addMarker(resource, lineNumber);
                }
            }
        }

        // Show a single warning if any lines were skipped due to breakpoint conflicts.
        if (skippedCount > 0) {
            MessageDialog.openWarning(null, "Non-Breakpoint",
                    skippedCount + " line(s) were skipped because a breakpoint is already set there.\n"
                    + "Please remove the breakpoint first.");
        }

        return null;
    }

    /**
     * Returns whether a Java line breakpoint exists on the specified line of the given resource.
     *
     * @param resource   the file to check
     * @param lineNumber the line number to check (1-based)
     * @return {@code true} if a breakpoint is set on that line, {@code false} otherwise
     */
    private boolean hasBreakpoint(IResource resource, int lineNumber) {
        IBreakpoint[] breakpoints = DebugPlugin.getDefault()
                .getBreakpointManager().getBreakpoints();
        for (IBreakpoint bp : breakpoints) {
            if (bp instanceof IJavaLineBreakpoint) {
                IMarker marker = bp.getMarker();
                if (marker.getResource().equals(resource)) {
                    if (marker.getAttribute(IMarker.LINE_NUMBER, -1) == lineNumber) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
