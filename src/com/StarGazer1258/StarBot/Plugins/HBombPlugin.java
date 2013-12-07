package com.StarGazer1258.StarBot.Plugins;

import com.StarGazer1258.StarBot.StarBotMain;

public class HBombPlugin extends Plugin {
	
	private static String pluginName = "HBomb Plugin";
	private static String shortName = "HBomb";
	private static String pluginAuthor = "StarGazer1258 (Star_)";
	private static String pluginVersion = "V1.0";
	private static String pluginDescription = "HBomb Game";
	private static int id = 1;
	
	public HBombPlugin() {
		super(pluginName, shortName, pluginAuthor, pluginVersion, pluginDescription, id);
	}

	@Override
	protected boolean cleanUp() {
		return true;
	}
	
	public void explode() {
		try {
			sendMessage(StarBotMain.room, "            --_--");
			sendMessage(StarBotMain.room, "         (  -_    _).");
			sendMessage(StarBotMain.room, "       ( ~       )   )");
			sendMessage(StarBotMain.room, "     (( )  (    )  ()  )");
			sendMessage(StarBotMain.room, "      (.   )) (       )");
			sendMessage(StarBotMain.room, "        ``..     ..``");
			sendMessage(StarBotMain.room, "             | |");
			sendMessage(StarBotMain.room, "           (=| |=)");
			sendMessage(StarBotMain.room, "             | |");
			sendMessage(StarBotMain.room, "         (../( )\\.))");
		} catch(Exception e) {e.printStackTrace();}
	}
	
	public void onMessage(String channel, String sender, String login, String hostname, String message) {
		if(message.equalsIgnoreCase("!hbomb")) {explode();}
	}
	
}
