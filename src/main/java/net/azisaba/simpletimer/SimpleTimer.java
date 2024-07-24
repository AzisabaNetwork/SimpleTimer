package net.azisaba.simpletimer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleTimer extends JavaPlugin implements Listener {
    private final Map<UUID, Timer> timerMap = new ConcurrentHashMap<>();

    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("timer")).setExecutor(new TimerCommand(this));
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                Timer timer = timerMap.get(player.getUniqueId());
                if (timer != null) {
                    player.sendActionBar(ChatColor.AQUA + timer.getElapsedTimeInString());
                }
            }
        }, 1, 1);
    }

    public @NotNull Map<UUID, Timer> getTimerMap() {
        return timerMap;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        timerMap.remove(e.getPlayer().getUniqueId());
    }
}
