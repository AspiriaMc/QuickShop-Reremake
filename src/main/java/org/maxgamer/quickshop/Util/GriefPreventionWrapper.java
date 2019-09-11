package org.maxgamer.quickshop.Util;

import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GriefPreventionWrapper
{
    public boolean canEditBlock(@NotNull final Player player, @NotNull final Block block) {
        if (!GriefPrevention.instance.claimsEnabledForWorld(player.getWorld())) {
            return true;
        }

        final String message = GriefPrevention.instance.allowBuild(player, block.getLocation());
        if (message != null) {
            player.sendMessage(ChatColor.RED + message);
            return false;
        }

        return true;
    }
}