package org.maxgamer.quickshop.Command.SubCommands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.*;
import org.maxgamer.quickshop.Command.CommandContainer;
import org.maxgamer.quickshop.Command.CommandProcesser;
import org.maxgamer.quickshop.QuickShop;
import org.maxgamer.quickshop.Util.Util;

public class SubCommand_ROOT implements CommandProcesser {
    private QuickShop plugin = QuickShop.instance;

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] strings) {
        List<String> candidate = new ArrayList<>();
        for (CommandContainer container : plugin.getCommandManager().getCmds()) {
            if (container.getPrefix().startsWith(strings[0]) || container.getPrefix().equals(strings[0])) {
                List<String> requirePermissions = container.getPermissions();
                if (requirePermissions != null) {
                    for (String requirePermission : requirePermissions) {
                        if (requirePermission != null && !requirePermission.isEmpty() && !QuickShop.getPermissionManager().hasPermission(sender,requirePermission)) {
                            Util.debugLog("Sender " + sender
                                    .getName() + " trying tab-complete the command: " + commandLabel + ", but no permission " + requirePermission);
                            return null;
                        }
                    }
                }
                    if (!container.isHidden()) {
                        candidate.add(container.getPrefix());
                    }
            }
        }
        return candidate;
    }

    @Override
    public void onCommand(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] cmdArg) {
        new SubCommand_Help().onCommand(sender, commandLabel, cmdArg);
    }
}

