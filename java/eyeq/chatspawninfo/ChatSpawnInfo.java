package eyeq.chatspawninfo;

import eyeq.chatspawninfo.event.ChatSpawnInfoEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static eyeq.chatspawninfo.ChatSpawnInfo.MOD_ID;

@Mod(modid = MOD_ID, version = "1.0", dependencies = "after:eyeq_util")
public class ChatSpawnInfo {
    public static final String MOD_ID = "eyeq_chatspawninfo";

    @Mod.Instance(MOD_ID)
    public static ChatSpawnInfo instance;

    public static boolean isOnlyEntityLivingBase;
    public static boolean isOnlyModEntity;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ChatSpawnInfoEventHandler());
        load(new Configuration(event.getSuggestedConfigurationFile()));
    }

    public static void load(Configuration config) {
        config.load();

        String category = "Boolean";
        isOnlyEntityLivingBase = config.get(category, "isOnlyEntityLivingBase", false).getBoolean(false);
        isOnlyModEntity = config.get(category, "isOnlyModEntity", true).getBoolean(false);

        if(config.hasChanged()) {
            config.save();
        }
    }
}
