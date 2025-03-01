package org.maxgamer.quickshop.Listeners;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class ListenerHelper {
    private static Set<Class> disabledListener = new HashSet<>();

    public static void disableEvent(@NotNull Class eventClass) {
        if (disabledListener.contains(eventClass)) {
            return;
        }
        disabledListener.add(eventClass);
    }

    public static void enableEvent(@NotNull Class eventClass) {
        if (!disabledListener.contains(eventClass)) {
            return;
        }
        disabledListener.remove(eventClass);
    }

    public static boolean isDisabled(Class eventClass) {
        return disabledListener.contains(eventClass);
    }
}
