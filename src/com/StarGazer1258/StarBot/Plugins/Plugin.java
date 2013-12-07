package com.StarGazer1258.StarBot.Plugins;

import com.StarGazer1258.StarBot.StarBotMain;

public abstract class Plugin {
	protected String pluginName = "Untitled Plugin";
	protected String shortName = "Unknown";
	protected String pluginAuthor = "Unknown Author";
	protected String pluginVersion = "Unknown Version";
	protected String pluginDescription = "No Description";
	protected int id = -1;
	
	/**
	 * A plugin for StarBot
	 * @param name Name of the plugin
	 * @param shortName Shortened name of the plugin
	 * @param author Author(s) of the plugin
	 * @param version Current version of the plugin
	 * @param description Short description of the plugin
	 * @param id Unique identification number of the plugin
	 */
	public Plugin(String name, String shortName, String author, String version, String description, int id) {
		pluginName = name;
		this.shortName = shortName;
		pluginAuthor = author;
		pluginVersion = version;
		pluginDescription = description;
		this.id = id;
		validate();
	}
	
	public String define() {
		if(!isValid()) return "Invalid Plugin!";
		return pluginName + " " + pluginVersion + " by " + pluginAuthor + " - " + pluginDescription;
	}
	
	public String getName() {
		return pluginName;
	}
	
	public String getShortName() {
		return shortName;
	}
	
	protected void validate() {
		if(isValid()) {
			accept();
		} else {
			decline();
		}
	}
	
	protected boolean isValid() {
		if(id != -1) {
			return true;
		} else {
			return false;
		}
	}
	
	private void accept() {
		System.err.println("Plugin " + getShortName() + " validated.");
		load();
	}
	
	private void decline() {
		System.out.println("Declined!");
		if(cleanUp()) {
			
		} else StarBotMain.bot.sendMessage(StarBotMain.room, "Failed to clean up " + getShortName() + " plugin! This could cause issues if not addressed.");
	}
	
	protected void load() {
		System.err.println("Now attempting to load \"" + getShortName() + "\" plugin!");
		System.err.println("Loaded!");
	}
	
	protected void unload() {
		if(cleanUp()) {
			
		} else StarBotMain.bot.sendMessage(StarBotMain.room, "Failed to clean up " + getShortName() + " plugin! This could cause issues if not addressed.");
	}
	
	protected void sendMessage(String target, String message) {
		StarBotMain.bot.sendMessage(target, message);
	}
	
	protected void sendAction(String target, String message) {
		StarBotMain.bot.sendAction(target, message);
	}
	
	/**
	 * Cleans up any uneeded resources.
	 * @return success
	 */
	protected abstract boolean cleanUp();
}
