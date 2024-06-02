package cc.baka9.catseedloginblinding;


import cc.baka9.catseedlogin.bukkit.CatSeedLoginAPI;
import cc.baka9.catseedlogin.bukkit.event.CatSeedPlayerLoginEvent;
import cc.baka9.catseedlogin.bukkit.event.CatSeedPlayerRegisterEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CatSeedLoginBlinding extends JavaPlugin {

    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents(new Listener() {

            @EventHandler
            public void onPlayerJoin(PlayerJoinEvent event){
                Player player = event.getPlayer();
                if (!CatSeedLoginAPI.isLogin(player.getName())) {
                    PotionEffect effect = new PotionEffect(PotionEffectType.BLINDNESS, 20 * 60 * 60 * 24, 0);
                    player.addPotionEffect(effect, true);
                }
            }

            @EventHandler
            public void onPlayerLogin(CatSeedPlayerLoginEvent event){

                Player player = event.getPlayer();
                if (event.getResult() == CatSeedPlayerLoginEvent.Result.SUCCESS) {
                    player.removePotionEffect(PotionEffectType.BLINDNESS);
                }

            }

            @EventHandler
            public void onPlayerRegister(CatSeedPlayerRegisterEvent event){

                event.getPlayer().removePotionEffect(PotionEffectType.BLINDNESS);

            }


        }, this);
    }
}
