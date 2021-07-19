package im.dnn.StonecutterDamage;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class StonecutterListener implements Listener {
    List<UUID> hurtPlayers = new ArrayList<>();
    List<String> deathMessages;

    public StonecutterListener () {
        deathMessages = Settings.getStringList("deathMessages");
    }

    private void onPlayerEnter (Player player) {
        hurtPlayers.add(player.getUniqueId());
        sendDamage(player);
    }

    private void onPlayerLeave (Player player) {
        hurtPlayers.remove(player.getUniqueId());
    }

    private void addKnockback (Player player) {
        if (Settings.getBoolean("knockback.enabled")) {
            Double knockbackRate = Settings.getDouble("knockback.rate") * -1.0;
            player.setVelocity(player.getLocation().getDirection().multiply(knockbackRate));
        }
    }

    private void sendDamage (Player player) {
        if (player.hasPermission("stonecutterDamage.bypass")) {
            Logger.info(player.getName() + " avoid stonecutter damage");
        } else {
            Double damageAmount = Settings.getDouble("damage.amount");
            Double damageRate = Settings.getDouble("damage.rate");

            Random random = new Random();
            Double randomDamageRate = random.nextDouble() * (damageRate * (random.nextBoolean() ? 1 : -1));
            Double finalDamage = damageAmount + randomDamageRate;

            Logger.info(player.getName() + " got " + finalDamage.toString() + "pts of damage meanwhile stepping on stonecutter");
            player.damage(finalDamage);
            addKnockback(player);
        }
    }

    private boolean isGroundStonecutter (Player player) {
        return hurtPlayers.contains(player.getUniqueId());
    }

    private String getDeathMessage (Player player) {
        Random random = new Random();
        int randomInt = random.nextInt (deathMessages.size() - 1);
        String deathMessage = deathMessages.get(randomInt);
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
