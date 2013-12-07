package com.StarGazer1258.StarBot;

import java.io.*;
import java.util.Scanner;

public class ReadWithScanner {
	
	public static int lineNum;
	public static String[] lines;

	public static void main(String[] args) {
		Count.count(StarBot.log);
	}
	
	public static void loadFile(File loadPath) {
		int nextOp = 0;
		try {
			Scanner loadFile = new Scanner(loadPath);
			
			while(loadFile.hasNextLine()) {
				StarBot.ops[nextOp] = loadFile.nextLine();
				nextOp +=1;
			}
			
			nextOp = 0;
			loadFile.close();
		} catch (Exception e) {	}
	}
	
	public static void recallText(Log log, String text) {
		Count.countRecall(log, text);
		
		lineNum = 0;
		lines = new String[Count.recallCount];
		
		try {
		Scanner logScanner = new Scanner(new File(log.file));
		
		while(logScanner.hasNextLine()) {
			String line = logScanner.nextLine();
			if(line.contains(text)) {
				lines[lineNum++] = line;
			}
		}
		} catch(Exception e) {e.printStackTrace();}
	}
}

class Count {
	public static int count = 0;
	public static int recallCount = 0;

	public static void count(Log log) {
		File file = new File(log.file);
	    
	    try {
	    	
	        Scanner scanner = new Scanner(file);
	        
	        while (scanner.hasNextLine()) {
	            count++;
	            scanner.nextLine();
	        }
	        
	        scanner.close();
	        
		} catch (FileNotFoundException e) {e.printStackTrace();}
	}
	
	public static void countRecall(Log log, String text) {
		File file = new File(log.file);
	    
	    try {
	    	recallCount = 0;
	    	
	        Scanner scanner = new Scanner(file);
	        
	        while (scanner.hasNextLine()) {
	        	String line = scanner.nextLine();
	        	if(line.contains(text)) recallCount++;
	            scanner.nextLine();
	        }
	        
	        scanner.close();
	        
		} catch (FileNotFoundException e) {e.printStackTrace();}
	}
}