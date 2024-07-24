package net.azisaba.simpletimer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TimerCommand implements TabExecutor {
    private final SimpleTimer plugin;

    public TimerCommand(@NotNull SimpleTimer plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = Bukkit.getPlayerExact(args[1]);
        if (player == null) {
            sender.sendMessage("player not found");
            return true;
        }
        if (args[0].equals("start")) {
            Timer timer = new Timer();
            plugin.getTimerMap().put(player.getUniqueId(), timer);
            timer.start();
        }
        if (args[0].equals("stop")) {
            Timer timer = plugin.getTimerMap().remove(player.getUniqueId());
            if (timer != null) {
                String message = String.join(" ", Arrays.copyOfRange(args, 2, args.length)).replace("<time>", timer.getElapsedTimeInString());
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 1) {
            return Arrays.asList("start", "stop");
        }
        if (args.length == 2) {
            return null;
        }
        return Collections.emptyList();
    }
}
