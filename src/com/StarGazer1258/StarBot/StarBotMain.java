package com.StarGazer1258.StarBot;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.apple.eawt.*;

public class StarBotMain{
    
    public static final String room = "#StarlitRoom";
    
    // Now start our bot up.
    public static StarBot bot = new StarBot();
    public static Application application;
	public static void main(String[] args) throws Exception {
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "StarBot");
		System.setProperty("com.apple.mrj.application.apple.menu.about.version", "V1.2.3");
		
		application = Application.getApplication();
		Image image = Toolkit.getDefaultToolkit().getImage("res/icon.png");
		application.setDockIconImage(image);
		
		StarBot.log.clearLog();
		
        // Enable debugging output.
        bot.setVerbose(true);
        
        // Connect to the IRC server.
        bot.connect("irc.rizon.net");

        // Join the #StarlitRoom channel.
        bot.joinChannel(room);
        
        StarBotFrame.init(new JFrame(), new JFrame());
    }
    
}