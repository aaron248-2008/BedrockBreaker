package com.merlin.bukkit.plugins.BedrockBreaker;

/*
    This file is part of BedrockBreaker

    Foobar is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Foobar is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.bedrockPattern;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.craftPattern;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.craftingEnableCommand;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.craftingEnablePattern;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.craftingInfoCommand;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.craftingInfoPattern;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.debugEnableCommand;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.debugEnablePattern;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.debugInfoCommand;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.debugInfoPattern;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.debugPattern;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.minePattern;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.miningDamageCommand;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.miningDamagePattern;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.miningDropCommand;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.miningDropPattern;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.miningEnableCommand;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.miningEnablePattern;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.miningGapCommand;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.miningGapPattern;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.miningInfoCommand;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.miningInfoPattern;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.miningKeepAliveCommand;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.miningKeepAlivePattern;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.miningStrikesCommand;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.miningStrikesPattern;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.miningTimeCommand;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.miningTimePattern;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.placePattern;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.placingEnableCommand;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.placingEnablePattern;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.placingInfoCommand;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.placingInfoPattern;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.reloadConfigCommand;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.reloadConfigPattern;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.showBedrockCommands;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.showCraftCommands;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.showDebugCommands;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.showMineCommands;
import static com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary.showPlaceCommands;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.merlin.bukkit.plugins.BedrockBreaker.commands.BedrockCommandLibrary;
import com.merlin.bukkit.plugins.merlin.commands.CommandMetaData;
import com.merlin.bukkit.plugins.merlin.commands.executors.AdviceCommandLibraryExecutor;
import com.merlin.bukkit.plugins.merlin.commands.executors.LibraryTabCompleter;
import com.merlin.bukkit.plugins.merlin.core.commands.libraries.SimpleCommandLibrary;

public class BedrockBreaker extends JavaPlugin {

	//Command Library
    private SimpleCommandLibrary library = new SimpleCommandLibrary(new CommandMetaData(false));

	//ClassListeners
	private final CommandExecutor commandExecutor = new AdviceCommandLibraryExecutor(library,7);
	private final TabCompleter completer = new LibraryTabCompleter(library);
	private final BedrockBreakerEventListener eventListener = new BedrockBreakerEventListener(this);
	
	//Configuration Constants
	//Mining
	public final String MINING_ENABLED = "mining.enabled";
	public final String MINING_TIME = "mining.time";
	public final String MINING_STRIKES = "mining.strikes";
	public final String MINING_KEEPALIVE = "mining.keepAlive";
	public final String MINING_GAP = "mining.gap";
	public final String MINING_DROP_ENABLED = "mining.drop";
	public final String MINING_DAMAGE = "mining.damage";
	
	//Placement
	public final String PLACEMENT_ENABLED = "placement.enabled";
	
	//Crafting
	public final String CRAFTING_ENABLED = "crafting.enabled";
	
	//Debug
	public final String DEBUG_ENABLED = "debug.enabled";
	
	public void onDisable() {
		// add any code you want to be executed when your plugin is disabled
		
	}

	public void onEnable() { 

		
		PluginManager pm = this.getServer().getPluginManager();

		getCommand("bedrock").setExecutor(commandExecutor);
		getCommand("bedrock").setTabCompleter(completer);

		// you can register multiple classes to handle events if you want
		// just call pm.registerEvents() on an instance of each class
		pm.registerEvents(eventListener, this);
		
		// do any other initialisation you need here...
		BedrockBreakerTickTracker.reset();

		loadRecipes();

		try {
			loadLibrary();
		} catch(Exception e) {
			getServer().broadcastMessage("Error loading command library: " + e.getMessage());
		}

		//Comment
		this.saveDefaultConfig();

		this.getConfig().options().copyDefaults(true);
	}
	
	public void loadRecipes() {
		if(this.getConfig().getBoolean(CRAFTING_ENABLED)) {
			ShapedRecipe bedrockRecipe = new ShapedRecipe(new ItemStack(Material.BEDROCK, 1));
			bedrockRecipe.shape("AA","AA");
			bedrockRecipe.setIngredient('A',Material.OBSIDIAN);
			this.getServer().addRecipe(bedrockRecipe);
		}
	}

	public void removeRecipes() {
	        Iterator<Recipe> it = getServer().recipeIterator();
	        while(it.hasNext()){
	            Recipe itRecipe = it.next();
	            if(itRecipe.getResult().getType()==Material.BEDROCK) {
	            it.remove();
	            if(getConfig().getBoolean(DEBUG_ENABLED)) {
	            	Bukkit.getServer().broadcastMessage("Bedrock Recipe Removed");
	            }
	        }
	    }
	}
	
	public void loadLibrary() throws Exception {
		BedrockCommandLibrary.initialize(this);
		library.addCommand(bedrockPattern,showBedrockCommands);
		library.addCommand(minePattern,showMineCommands);
		library.addCommand(placePattern,showPlaceCommands);
		library.addCommand(craftPattern,showCraftCommands);
		library.addCommand(debugPattern,showDebugCommands);

		library.addCommand(miningEnablePattern,miningEnableCommand);
		library.addCommand(miningKeepAlivePattern,miningKeepAliveCommand);
		library.addCommand(miningStrikesPattern,miningStrikesCommand);
		library.addCommand(miningDamagePattern,miningDamageCommand);
		library.addCommand(miningDropPattern,miningDropCommand);
		library.addCommand(miningGapPattern,miningGapCommand);
		library.addCommand(miningInfoPattern,miningInfoCommand);
		library.addCommand(miningTimePattern,miningTimeCommand);
		
		library.addCommand(placingEnablePattern,placingEnableCommand);
		library.addCommand(placingInfoPattern, placingInfoCommand);
		
		library.addCommand(craftingEnablePattern,craftingEnableCommand);
		library.addCommand(craftingInfoPattern,craftingInfoCommand);
		
		library.addCommand(debugEnablePattern, debugEnableCommand);
		library.addCommand(debugInfoPattern,debugInfoCommand);

		library.addCommand(reloadConfigPattern,reloadConfigCommand);		
	}
}
