package com.StarGazer1258.StarBot;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.Scanner;

import javax.swing.*;

public class StarBotFrame {
	public static JFrame frame;
	
	public static boolean notify = false;
	
	private static JButton button = new JButton("Manually Enter Command");
	private static JButton say = new JButton("Say");
	private static JButton sound = new JButton("Toggle Sound");
	private static JButton quit = new JButton("Shut Down");

	private static JSeparator[] hSep = new JSeparator[2];

	private static JPanel[] line = new JPanel[8];
	private static JPanel[] innerPanel = new JPanel[3];
	
	private static JPanel panel = new JPanel(new BorderLayout());
	
	private static JTextField text = new JTextField("Type what to say here");
	
	public static JTextArea logField = new JTextArea();
	private static JScrollPane logPane = new JScrollPane (logField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

	public static void init(final JFrame f, JFrame slaveF) {
		for(int i=0;i<line.length;i++) line[i] = new JPanel();
		for(int i=0;i<innerPanel.length;i++) innerPanel[i] = new JPanel(new BorderLayout());
		for(int i=0;i<hSep.length;i++) hSep[i] = new JSeparator(SwingConstants.HORIZONTAL);
		frame = f;
		f.setTitle("StarBot Control Panel");
		f.setResizable(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				System.out.print("Enter Command: ");
				StarBot.sayMessage = new Scanner(System.in).nextLine();
				if(StarBot.sayMessage.startsWith("SAY ")) {
					StarBotMain.bot.sendMessage(StarBotMain.room, StarBot.sayMessage.replace("SAY ", ""));
				}
				if(StarBot.sayMessage.startsWith("KICK ")) {
					StarBotMain.bot.kick(StarBotMain.room, StarBot.sayMessage.replace("KICK ", ""));
				}
				if(StarBot.sayMessage.startsWith("OP ")) {
					StarBotMain.bot.op(StarBotMain.room, StarBot.sayMessage.replace("OP ", ""));
				}
			}
		});
		say.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				try {
					StarBotMain.bot.sendMessage(StarBotMain.room, text.getText());
					text.setText(null);
				} catch(Exception e) { e.printStackTrace(); }
			}
		});
		sound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				try {
					StarBot.sound = !StarBot.sound;
					if(StarBot.sound) {
						StarBotFrame.logField.append("Sound Enabled!" + "\n");
						SoundHandle.playSound("ding.wav");
					} else if(!StarBot.sound) {
						StarBotFrame.logField.append("Sound Disabled!" + "\n");
					}
				} catch(Exception e) { e.printStackTrace(); }
			}
		});
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				try {
					System.exit(0);
				} catch(Exception e) { e.printStackTrace(); }
			}
		});
		text.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				try {
					StarBotMain.bot.sendMessage(StarBotMain.room, text.getText());
					text.setText(null);
				} catch(Exception e) { e.printStackTrace(); }
			}
		});
		
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem showHideItem = new JMenuItem("Show/Hide Control Panel");
		showHideItem.addActionListener(new ActionListener() {
			private boolean shown = true;

			public void actionPerformed(ActionEvent a) {
				try {
					if(shown) {
						shown = false;
						f.setVisible(shown);
					} else if(!shown) {
						shown = true;
						f.setVisible(shown);
					}
				} catch(Exception e) { e.printStackTrace(); }
			}
		});
		
		fileMenu.add(showHideItem);
		menuBar.add(fileMenu);
		f.setJMenuBar(menuBar);
		slaveF.setJMenuBar(menuBar);
		slaveF.setResizable(false);
		slaveF.setVisible(true);
		
		f.setAlwaysOnTop(true);
		line[0].add(new JLabel("Main Controls"));
		line[1].add(button);
		line[1].add(quit);
		line[1].add(sound);
		line[2].add(hSep[0]).setPreferredSize(new Dimension(500, 10));
		line[3].add(new JLabel("Say Controls"));
		line[4].add(text);
		line[4].add(say);
		line[5].add(hSep[1]).setPreferredSize(new Dimension(500, 10));
		line[6].add(new JLabel("Chat Log"));
		logField.setEditable(false);
		line[7].add(logPane).setPreferredSize(new Dimension(500, 300));
		innerPanel[0].add(line[0], BorderLayout.NORTH);
		innerPanel[0].add(line[1], BorderLayout.CENTER);
		innerPanel[0].add(line[2], BorderLayout.SOUTH);
		innerPanel[1].add(line[3], BorderLayout.NORTH);
		innerPanel[1].add(line[4], BorderLayout.CENTER);
		innerPanel[1].add(line[5], BorderLayout.SOUTH);
		innerPanel[2].add(line[6], BorderLayout.NORTH);
		innerPanel[2].add(line[7], BorderLayout.CENTER);
		panel.add(innerPanel[0], BorderLayout.NORTH);
		panel.add(innerPanel[1],BorderLayout.CENTER);
		panel.add(innerPanel[2], BorderLayout.SOUTH);
		f.add(panel);
		f.pack();
		f.setVisible(true);
	}
}