package nonbreakpoint;

import java.util.HashSet;
import java.util.Set;

/**
 * Registry that manages lines marked as non-breakpoints.
 *
 * Mark information is stored in an in-memory Set and is valid only for the
 * current Eclipse session. Keys follow the format "filePath:lineNumber"
 * (e.g. /MyProject/src/Foo.java:42).
 * All methods are static, so this class does not need to be instantiated.
 */
public class NonBreakpointRegistry {

    /**
     * Set that holds the keys of all marked lines.
     * HashSet provides O(1) performance for contains(), add(), and remove().
     * As a static field, the state is retained for the duration of the plugin session.
     */
    private static final Set<String> markedLines = new HashSet<>();

    /**
     * Unconditionally marks the given key as a non-breakpoint (for restore on startup).
     * Unlike toggle(), this method does not change the state if the key is already marked.
     *
     * @param key identifier in the format "filePath:lineNumber"
     */
    public static void mark(String key) {
        markedLines.add(key);
    }

    /**
     * Toggles the mark state of the given key (ON &harr; OFF).
     *
     * If the key is already marked it is removed; otherwise it is added.
     * This is the method called when the user selects the menu item or uses
     * the keyboard shortcut to toggle a line.
     *
     * @param key identifier in the format "filePath:lineNumber"
     */
    public static void toggle(String key) {
        if (markedLines.contains(key)) {
            markedLines.remove(key);
        } else {
            markedLines.add(key);
        }
    }

    /**
     * Returns whether the given key is currently marked.
     *
     * Used to check the current state before adding or removing a marker.
     *
     * @param key identifier in the format "filePath:lineNumber"
     * @return {@code true} if the line is marked, {@code false} otherwise
     */
    public static boolean isMarked(String key) {
        return markedLines.contains(key);
    }

    /**
     * Returns the set of all registered keys.
     *
     * Used by NonBreakpointDebugListener to compare all registered lines
     * against the current suspension point during debugging.
     * The returned Set is an internal reference — callers must not modify it.
     *
     * @return the Set of marked line keys (treat as read-only)
     */
    public static Set<String> getAll() {
        return markedLines;
    }
}
