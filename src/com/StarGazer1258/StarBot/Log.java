package com.StarGazer1258.StarBot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Log {
	static File log;
	String file;
	
	public Log(String file) {
		this.file = file;
		log = new File(file);
	}
	
	public void printToLog(String text) {
		try {
			PrintWriter out = new PrintWriter(new FileWriter(log, true));
			out.append(text + "\n");
			StarBotFrame.logField.append(text + "\n");
			out.close();
		} catch(IOException e) {System.err.println("Could not find log!");}
	}
	
	public void clearLog() {
		try {
			PrintWriter out = new PrintWriter(new FileWriter(log));
			out.append("");
			out.close();
		} catch(IOException e) {System.err.println("Could not find log!");}
	}
}
