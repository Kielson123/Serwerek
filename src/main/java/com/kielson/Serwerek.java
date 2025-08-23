package com.kielson;

import com.kielson.event.ModEvents;
import com.kielson.event.ModItemTooltips;
import com.kielson.item.ModItemGroups;
import com.kielson.item.ModItems;
import com.kielson.util.ModComponents;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Serwerek implements ModInitializer {
	public static final String MOD_ID = "serwerek";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize(){
		ModItems.initialize();
		ModItemGroups.initialize();
		ModEvents.initialize();
		ModTags.initialize();
		ModComponents.initialize();
	}
}