package nonbreakpoint;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

/**
 * Manages Eclipse markers that represent non-breakpoint annotations in the editor.
 *
 * An Eclipse marker is metadata associated with a specific line of a file.
 * By defining a custom marker type in plugin.xml, this plugin can display
 * icons in the left vertical ruler and the overview ruler, using the same
 * mechanism as error and warning markers.
 *
 * This class provides only two operations — addMarker and removeMarker —
 * and consists entirely of static methods, so it does not need to be instantiated.
 */
public class NonBreakpointMarkerManager {

    /**
     * The marker type ID defined in plugin.xml under the
     * org.eclipse.core.resources.markers extension point.
     * Passed to IResource.createMarker() and findMarkers() to identify the marker kind.
     */
    public static final String MARKER_TYPE = "nonbreakpoint.marker";

    /**
     * Adds a non-breakpoint marker to the specified line of the given resource.
     *
     * Setting IMarker.LINE_NUMBER causes Eclipse to display the marker icon
     * on the corresponding line in the editor.
     * IMarker.MESSAGE is shown as a tooltip when the user hovers over the marker.
     *
     * @param resource   the file to annotate (passed as IResource)
     * @param lineNumber the line number to mark (1-based)
     */
    public static void addMarker(IResource resource, int lineNumber) {
        try {
            IMarker marker = resource.createMarker(MARKER_TYPE);
            marker.setAttribute(IMarker.LINE_NUMBER, lineNumber);
            marker.setAttribute(IMarker.MESSAGE, "Non-Breakpoint");
        } catch (CoreException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes the non-breakpoint marker from the specified line of the given resource.
     *
     * findMarkers() retrieves all markers of this plugin's type on the file.
     * The second argument {@code false} means subtypes are not included (exact match).
     * The third argument {@code IResource.DEPTH_ZERO} limits the search to this
     * resource only (no subdirectories).
     * Only the marker whose LINE_NUMBER attribute matches is deleted.
     *
     * @param resource   the file from which to remove the marker
     * @param lineNumber the line number to unmark (1-based)
     */
    public static void removeMarker(IResource resource, int lineNumber) {
        try {
            IMarker[] markers = resource.findMarkers(MARKER_TYPE, false, IResource.DEPTH_ZERO);
            for (IMarker marker : markers) {
                if (marker.getAttribute(IMarker.LINE_NUMBER, -1) == lineNumber) {
                    marker.delete();
                }
            }
        } catch (CoreException e) {
            e.printStackTrace();
        }
    }
}
