package dev.georgekazan.damageconfig;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = DamageModMain.MOD_ID, name = "DamageSourceConfiguration")
@Config.LangKey("config.damage.title")
public class DamageModConfig{

	public static final Options serverOptions = new Options();

	public static class Options {
		@Config.Name("Affected Entities")
		@Config.Comment("Entity IDs that will ignore the damage source")
		public String[] affectedEntities = new String[0];
		
		@Config.Name("Damage Sources")
		@Config.Comment("Damage sources that the entities will ignore")
		public String[] damageSources = new String[0];
		
		@Config.Name("Check Entity First")
		@Config.Comment("If enable the code will check if the entity is the right entity before checking the source")
		public boolean entityFirst = true;
		
		@Config.Name("Enable Mod")
		@Config.Comment("I think this enables the mod")
		public boolean enabled = true;
	}

	@Mod.EventBusSubscriber(modid = DamageModMain.MOD_ID)
	private static class EventHandler {
		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(DamageModMain.MOD_ID)) {
				ConfigManager.sync(DamageModMain.MOD_ID, Config.Type.INSTANCE);
			}
		}
	}
}
