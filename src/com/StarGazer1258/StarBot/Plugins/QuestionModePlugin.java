package com.StarGazer1258.StarBot.Plugins;

import com.StarGazer1258.StarBot.StarBotMain;

public class QuestionModePlugin extends Plugin {
	
	private static String pluginName = "Question Mode Plugin";
	private static String shortName = "Questions";
	private static String pluginAuthor = "StarGazer1258 (Star_)";
	private static String pluginVersion = "V1.0";
	private static String pluginDescription = "Allows users to ask the bot questions.";
	private static int id = 0;
	
	static boolean questionMode = false;
	static String asker;
	
	public QuestionModePlugin() {
		super(pluginName, shortName, pluginAuthor, pluginVersion, pluginDescription, id);
	}
	
	public void onMessage(String channel, String sender, String login, String hostname, String message) {
		if(message.equalsIgnoreCase("!definePlugin " + shortName)) {
			sendMessage(StarBotMain.room, define());
		}
		
		if(message.equals("StarCat_?")) {
			sendMessage(channel, sender + ": Yes?");
			questionMode = true;
			asker = sender;
		} else if(message.contains(getName())) {
			sendMessage(channel, "Meoeoeeoeoeow!");
			try {
				Thread.sleep(2000);
			} catch(Exception e) {
				e.printStackTrace();
			}
			sendMessage(channel, "That's my scary meow ;)");
			sendMessage(channel, "Practicing for halloween!");
		}
		
		if(questionMode) {
			if(message.equalsIgnoreCase("Are you broken?")) {
				sendMessage(channel, asker + ": I dont think so...");
				questionMode = false;
			}
			if(message.equalsIgnoreCase("Meow for me?") || message.equalsIgnoreCase("Can you meow for me?") || message.equalsIgnoreCase("Will you meow for me?")) {
				sendMessage(channel, asker + ": Sure!");
				try {
					Thread.sleep(1500);
				} catch(Exception e) {
					Thread.currentThread().interrupt();
				}
				sendMessage(channel, "MEOW!");
				questionMode = false;
			}
			
			if(message.equalsIgnoreCase("Purr for me?") || message.equalsIgnoreCase("Can you purr for me?") || message.equalsIgnoreCase("Will you purr for me?")) {
				sendAction(StarBotMain.room, "purrs");
			}
			 
			if(message.equalsIgnoreCase("Want a drink?") || message.equalsIgnoreCase("Do you want a drink?")) {
				sendMessage(channel, asker + ": Do you want me to die?");
				questionMode = false;
			}
		}
	}
	
	@Override
	public boolean cleanUp() {
		return true;
	}
	
}
