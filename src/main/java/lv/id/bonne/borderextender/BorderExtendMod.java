package lv.id.bonne.borderextender;


import com.mojang.logging.LogUtils;

import iskallia.vault.core.vault.Vault;
import iskallia.vault.core.vault.stat.StatCollector;
import iskallia.vault.event.event.VaultLeaveEvent;
import lv.id.bonne.borderextender.configs.Configuration;
import lv.id.bonne.borderextender.util.Util;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import org.slf4j.Logger;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(BorderExtendMod.MODID)
@Mod.EventBusSubscriber(modid = BorderExtendMod.MODID)
public class BorderExtendMod
{
    /**
     * Logger
     */
    private static final Logger LOGGER = LogUtils.getLogger();

    /**
     * Configuration
     */
    public static Configuration CONFIGURATION;

    public final static String MODID = "borderextendermod";

    public BorderExtendMod()
    {
        MinecraftForge.EVENT_BUS.register(this);

        BorderExtendMod.CONFIGURATION = new Configuration();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON,
            Configuration.GENERAL_SPEC,
            "border_extend_config.toml");
    }


    /**
     * Common setup event.
     *
     * @param event The event.
     */
    @SubscribeEvent
    public static void vaultLeaveEvent(VaultLeaveEvent event)
    {
        Vault vault = event.getVault();

        if (vault == null)
        {
            return;
        }

        vault.ifPresent(Vault.OWNER, ownerId -> {
            if (!event.getPlayer().getUUID().equals(ownerId) && CONFIGURATION.getOnlyOwner())
            {
                return;
            }

            vault.ifPresent(Vault.STATS, collector ->
            {
                StatCollector stats = collector.get(event.getPlayer().getUUID());

                if (stats != null)
                {
                    Util.increaseWorldBorder(event.getPlayer(), stats.getCompletion());
                }
            });
        });
    }
}
