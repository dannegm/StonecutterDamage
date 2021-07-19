package im.dnn.StonecutterDamage;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StonecutterListener implements Listener {
    List<UUID> players = new ArrayList<>();

    private void onPlayerEnter (Player player) {
        players.add(player.getUniqueId());
        sendDamage(player);
    }

    private void onPlayerLeave (Player player) {
        players.remove(player.getUniqueId());
    }

    private void sendDamage (Player player) {
        if (player.hasPermission("stonecutterDamage.bypass")) {
            Logger.info(player.getName() + " avoid stonecutter damage");
        } else {
            Double damageAmount = Settings.getDouble("damageAmount");
            Logger.info(player.getName() + " got " + damageAmount.toString() + "pts of damage meanwhile stepping on stonecutter");
            player.damage(damageAmount);
        }
    }

    private boolean isGroundStonecutter (Player player) {
        return players.contains(player.getUniqueId());
    }

    private String getDeathMessage (Player player) {
        String deathMessage = Settings.getString("deathMessage");
        return deathMessage.replaceAll("<player>", player.getDisplayName());
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Block targetBlock  = event.getTo().getBlock();

        if (targetBlock.getType() == Material.STONECUTTER) {
            onPlayerEnter(player);
        } else {
            onPlayerLeave(player);
        }
    }

    @EventHandler
    private void onPlayeDreathByStonecutter (PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (isGroundStonecutter(player)) {
            Logger.info(player.getName() + " died stepping on stonecutter");
            event.setDeathMessage(getDeathMessage(player));
            onPlayerLeave(player);
        }
    }
}
