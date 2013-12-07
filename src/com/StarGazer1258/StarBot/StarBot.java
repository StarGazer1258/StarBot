package com.StarGazer1258.StarBot;

import java.io.*;

import com.StarGazer1258.StarBot.ReadWithScanner;
import com.StarGazer1258.StarBot.Plugins.HBombPlugin;
import com.StarGazer1258.StarBot.Plugins.QuestionModePlugin;

import org.jibble.pircbot.*;

public class StarBot extends PircBot {
	
	public QuestionModePlugin question;
	public HBombPlugin hbomb;
	
	public User getUser(String channel, String nick) {
		for(User user : getUsers(StarBotMain.room)) {
			if(user.equals(nick)) { return user; }
		}
		return null;
	}
	
	public final void deHalfOp(String channel, String nick) {
		setMode(channel, "-h " + nick);
	}
	
	public final void halfOp(String channel, String nick) {
		setMode(channel, "+h " + nick);
	}
	
	String topic;
	String[] text = { "T", "e", "s", "t", "!" };
	String[] colors = { Colors.RED, Colors.OLIVE, Colors.YELLOW, Colors.GREEN, Colors.BLUE, Colors.CYAN, Colors.MAGENTA, Colors.PURPLE };
	String[] afk = new String[getUsers(StarBotMain.room).length];
	String[] rules = { "Thanks for taking a moment to read the rules!", "1. Please, No Spamming/Excessive Asking etc." };
	String[] help = { "!botlock, !games, !help, !sleep, !time, !topic" };
	String[] games = { "!numbergame" };
	static String[] ops = new String[6];
	static String sayMessage;
	
	int numberRaw = 0;
	
	String finalText = "";
	public static boolean sound = true;
	
	static boolean catJoin = false;
	static boolean gameActive = false;
	static boolean botlock = false;
	static boolean sleeping = false;
	static boolean hbombActive = false;
	static boolean numberGameActive = false;
	
	static Log log = new Log("res/log.sbf");
	
	public StarBot() {
		setName("StarCat_");
		setMessageDelay(0);
		ReadWithScanner.loadFile(new File("res/ops.sbf"));
		question = new QuestionModePlugin();
		hbomb = new HBombPlugin();
	}
	
	public void onPrivateMessage(String sender, String login, String hostname, String message) {
		if(message.contains("!say")) {
			sendMessage(StarBotMain.room, message.replace("!say ", ""));
		}
	}
	
	public void onTopic(String channel, String topic) {
		this.topic = topic;
	}
	
	public void onMessage(String channel, String sender, String login, String hostname, String message) {
		String time = new java.util.Date().toString();
		
		log.printToLog(time + " " + sender + ": " + message);
		
		question.onMessage(channel, sender, login, hostname, message);
		hbomb.onMessage(channel, sender, login, hostname, message);
		
		StarBotMain.application.requestUserAttention(true);
		if(sound) SoundHandle.playSound("ding.wav");
		
		if(!botlock) {
			if(message.equalsIgnoreCase("!time")) {
				sendMessage(channel, sender + ": The time is now " + time);
			}
			
			if(message.equals("!death")) {
				kick(channel, sender, "You're Dead!");
			}
			
			if(message.contains("!log")) {
				ReadWithScanner.recallText(log, message.replace("!log ", ""));
				for(int i = 0; i < ReadWithScanner.lines.length; i++) {
					sendMessage(channel, ReadWithScanner.lines[i]);
				}
			}
			
			if(message.equalsIgnoreCase("!halt") || message.equalsIgnoreCase("!stop")) {
				sendMessage(channel, "Bai!");
				try {
					Thread.sleep(5000);
				} catch(Exception e) {
					Thread.currentThread().interrupt();
				}
				sendMessage(channel, "Stopping!");
				try {
					Thread.sleep(500);
				} catch(Exception e) {
					Thread.currentThread().interrupt();
				}
				disconnect();
				System.exit(0);
			}
			
			if(message.equalsIgnoreCase("!games")) {
				for(int i = 0; i < games.length; i++) {
					sendMessage(channel, games[i]);
				}
			}
			
			if(message.equalsIgnoreCase("!test")) {
				for(int i = 0; i < 5; i++) {
					text[i] = colors[i] + text[i];
				}
				finalText = "";
				for(int i = 0; i < 5; i++) {
					finalText += text[i];
				}
				sendMessage(channel, finalText);
			}
			
//			if(message.startsWith("!afk")) {
//				sendMessage(channel, message.replace("!afk ", "") + " is now afk!");
//			}
//			
//			if(message.equalsIgnoreCase("!hbomb")) {
//				sendMessage(channel, sender + " has activated the timer! Type !pass <user> to pass the bomb or type !defuse <color> to try to defuse it!");
//				
//				try {
//					Thread.sleep(5000);
//				} catch(Exception e) {
//					Thread.currentThread().interrupt();
//				}
//				sendMessage(channel, "jk, just testing ;)");
//			}
			
			if(gameActive) {
				if(message.equalsIgnoreCase("!endGame")) {
					gameActive = false;
					sendMessage(channel, "Ending game!");
				}
			} else if(message.equalsIgnoreCase("!endGame")) {
				sendMessage(channel, "No game currently running!");
			}
			
			if(numberGameActive && gameActive) {
				try {
					int num = Integer.parseInt(message);
					String number = Integer.toString(numberRaw);
					if(message.contains(number)) {
						sendMessage(channel, Colors.GREEN + sender + " guessed the number!");
						sendMessage(channel, Colors.BLUE + "The number was " + number + ".");
						numberGameActive = false;
					}
					if(num < numberRaw) {
						sendMessage(channel, Colors.RED + sender + ": too low!");
					}
					
					if(num > numberRaw) {
						sendMessage(channel, Colors.RED + sender + ": too high!");
					}
				} catch(Exception e) {
					System.err.println(e.getMessage());
				}
			} else {
				numberGameActive = false;
			}
			
			if(message.equalsIgnoreCase("!numberGame")) {
				numberGameActive = true;
				gameActive = true;
				sendMessage(channel, "Guess the Number!");
				int max = 100;
				int min = 1;
				numberRaw = (int) (Math.random() * (max - min) + min);
			}
			
			/*
			 * if (message.equalsIgnoreCase("!rules")) { for (int
			 * i=0;i<rules.length;i++) { sendNotice(sender, rules[i]); } }
			 */
			
			if(message.equalsIgnoreCase("!help")) {
				for(int i = 0; i < help.length; i++) {
					sendMessage(channel, help[i]);
				}
			}
			
			if(message.equalsIgnoreCase("!ping")) {
				sendMessage(channel, "Pong!");
			}
			
			if(message.equalsIgnoreCase("!sleep")) {
				if(!sleeping) {
					sendMessage(channel, "Sleeping...");
					sleeping = true;
				} else if(sleeping) {
					sleeping = false;
					sendMessage(channel, "Zzz... Huh? What?! I wasn't sleeping!");
				}
			}
			
			if(message.startsWith("!op ")) {
				for(int i = 0; i < ops.length; i++) {
					if(sender.equals(ops[i])) {
						op(StarBotMain.room, message.replace("!op ", ""));
						sendMessage(channel, "Opped " + message.replace("!op ", "") + "!");
						break;
					}
				}
			}
			
			if(message.startsWith("!kick ")) {
				for(int i = 0; i < ops.length; i++) {
					if(sender.equals(ops[i])) {
						kick(StarBotMain.room, message.replace("!kick ", ""));
						sendMessage(channel, "Kicked " + message.replace("!kick ", "") + "!");
						break;
					}
				}
			}
			
			if(message.contains("bored")) {
				sendMessage(channel, "Bored? Try !games to see my selection of hand-made games!");
			}
			
			if(message.equals("!topic")) {
				sendMessage(channel, "Current topic is: " + topic);
			}
			
			if(message.equalsIgnoreCase("!botlock")) {
				for(int i = 0; i < ops.length; i++) {
					if(sender.equals(ops[i])) {
						botlock = true;
						sendMessage(channel, "!BOT LOCK IS NOW ON!");
						break;
					} else if(i >= ops.length - 1) {
						sendMessage(channel, sender + ": Only Star_ is allowed to engage botlock!");
					}
				}
			}
			
			if(message.equalsIgnoreCase("!trustedlist") || message.equalsIgnoreCase("!trusted")) {
				sendMessage(channel, sender + ": The following people are on my trusted list:");
				for(int i = 0; i < ops.length; i++) {
					sendMessage(channel, ops[i]);
				}
			}
		} else {
			if(message.equalsIgnoreCase("!botlock")) {
				for(int i = 0; i < ops.length; i++) {
					if(sender.equals(ops[i])) {
						botlock = false;
						sendMessage(channel, "!BOT LOCK IS NOW OFF!");
						break;
					} else if(i >= ops.length - 1) {
						sendMessage(channel, sender + ": Only Star_ is allowed to disengage botlock!");
					}
				}
			}
		}
	}
	
	public void onJoin(String channel, String sender, String login, String hostname) {
		if(catJoin) {
			sendMessage(channel, "Hello " + sender + "!");
		} else {
			catJoin = true;
		}
	}
	
}