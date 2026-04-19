package nonbreakpoint;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * Plugin entry point that manages the lifecycle of the NonBreakpoint plugin.
 *
 * Eclipse manages plugins as OSGi bundles. This Activator, which extends
 * AbstractUIPlugin, is responsible for the plugin's start and stop lifecycle.
 * It registers NonBreakpointDebugListener with the debug framework on startup
 * and unregisters it on shutdown to prevent resource leaks.
 */
public class Activator extends AbstractUIPlugin {

    /** Listener that receives debug events (held for the lifetime of the plugin). */
    private static NonBreakpointDebugListener listener;

    /** Listener that watches for breakpoint additions to remove conflicting non-breakpoints. */
    private static NonBreakpointBreakpointListener breakpointListener;

    /**
     * Called by Eclipse when the plugin starts.
     *
     * DebugPlugin is the singleton manager for the entire debug framework.
     * Registering a listener via addDebugEventListener() allows this plugin
     * to receive events such as breakpoint hits and step operations.
     * Existing non-breakpoint markers are also restored into the in-memory
     * registry so that step-skipping works correctly after an Eclipse restart.
     *
     * @param context the OSGi bundle context provided by the framework
     */
    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        listener = new NonBreakpointDebugListener();
        DebugPlugin.getDefault().addDebugEventListener(listener);

        // Restore non-breakpoint markers persisted from the previous session.
        // IMarker data is automatically saved to the workspace metadata folder,
        // so we only need to reload it into the in-memory registry on startup.
        try {
            IMarker[] markers = ResourcesPlugin.getWorkspace().getRoot()
                    .findMarkers(NonBreakpointMarkerManager.MARKER_TYPE, false, IResource.DEPTH_INFINITE);
            for (IMarker marker : markers) {
                int line = marker.getAttribute(IMarker.LINE_NUMBER, -1);
                if (line > 0) {
                    String path = marker.getResource().getFullPath().toString();
                    NonBreakpointRegistry.mark(path + ":" + line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        breakpointListener = new NonBreakpointBreakpointListener();
        DebugPlugin.getDefault().getBreakpointManager().addBreakpointListener(breakpointListener);
    }

    /**
     * Called by Eclipse when the plugin stops.
     *
     * Both listeners must be unregistered here. Failing to do so would leave
     * stale instances in memory, causing resource leaks or duplicate execution.
     *
     * @param context the OSGi bundle context
     */
    @Override
    public void stop(BundleContext context) throws Exception {
        if (listener != null) {
            DebugPlugin.getDefault().removeDebugEventListener(listener);
        }
        if (breakpointListener != null) {
            DebugPlugin.getDefault().getBreakpointManager().removeBreakpointListener(breakpointListener);
        }
        super.stop(context);
    }
}
