package eyeq.chatspawninfo.event;

import eyeq.chatspawninfo.ChatSpawnInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatSpawnInfoEventHandler {
    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if(event.getWorld().isRemote) {
            return;
        }
        Entity entity = event.getEntity();
        if(ChatSpawnInfo.isOnlyEntityLivingBase && !(entity instanceof EntityLivingBase)) {
            return;
        }
        if(ChatSpawnInfo.isOnlyModEntity) {
            ResourceLocation registryName = EntityList.getKey(entity);
            if(registryName != null && registryName.getResourceDomain().equals("minecraft")) {
                return;
            }
        }
        ITextComponent text = entity.getDisplayName().appendText(": " + entity.getPosition());
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(text);
    }
}
