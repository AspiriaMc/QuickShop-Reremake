package org.maxgamer.quickshop.Command.SubCommands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.*;
import org.maxgamer.quickshop.Command.CommandProcesser;
import org.maxgamer.quickshop.QuickShop;
import org.maxgamer.quickshop.Shop.Shop;
import org.maxgamer.quickshop.Util.MsgUtil;
import org.maxgamer.quickshop.Util.Util;

public class SubCommand_SilentRemove implements CommandProcesser {
    private QuickShop plugin = QuickShop.instance;

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] cmdArg) {
        return new ArrayList<>();
    }

    @Override
    public void onCommand(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] cmdArg) {
        if (cmdArg.length < 4) {
            Util.debugLog("Exception on command, cancel.");
            return;
        }
        Player p = (Player) sender;
        Shop shop = plugin.getShopManager().getShop(new Location(Bukkit.getWorld(cmdArg[0]), Integer.parseInt(cmdArg[1]),
                Integer.parseInt(cmdArg[2]), Integer.parseInt(cmdArg[3])));
        if (shop == null) {
            sender.sendMessage(MsgUtil.getMessage("not-looking-at-shop"));
            return;
        }
        if (shop.getModerator().isModerator(p.getUniqueId()) || QuickShop.getPermissionManager().hasPermission(sender,"quickshop.other.destroy")) {
            shop.onUnload();
            shop.delete();
        } else {
            sender.sendMessage(ChatColor.RED + MsgUtil.getMessage("no-permission"));
        }
    }
}
