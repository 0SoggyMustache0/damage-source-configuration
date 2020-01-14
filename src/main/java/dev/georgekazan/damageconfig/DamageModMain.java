package dev.georgekazan.damageconfig;

import java.util.Arrays;

import net.minecraft.entity.EntityList;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author George K. Kazanjian
 */
@Mod(modid = DamageModMain.MOD_ID, name = DamageModMain.MOD_ID, version = "1.12.2-0.69")
public class DamageModMain {

	public static final String MOD_ID = "damage";

	@Instance(DamageModMain.MOD_ID)
	public static DamageModMain modInstance;

	@Mod.EventBusSubscriber(modid = DamageModMain.MOD_ID)
	public static class Handlers {

		@SubscribeEvent
		public static void attackEvent(final LivingAttackEvent event) {
			if(DamageModConfig.serverOptions.enabled) {
				if(DamageModConfig.serverOptions.entityFirst) {
					if(EntityList.getKey(event.getEntity()) != null && Arrays.asList(DamageModConfig.serverOptions.affectedEntities).contains(EntityList.getKey(event.getEntity()).toString())) 
						if(Arrays.asList(DamageModConfig.serverOptions.damageSources).contains(event.getSource().getDamageType())) 
							event.setCanceled(true);
				}
				else {
					if(Arrays.asList(DamageModConfig.serverOptions.damageSources).contains(event.getSource().getDamageType())) 
						if(EntityList.getKey(event.getEntity()) != null && Arrays.asList(DamageModConfig.serverOptions.affectedEntities).contains(EntityList.getKey(event.getEntity()).toString()))
							event.setCanceled(true);
				}
			}
		}
	}
}
