package com.StarGazer1258.StarBot;
import javax.sound.midi.*;
import javax.sound.sampled.*;
import javax.sound.sampled.AudioSystem;
import java.io.*;
import java.net.URL;
import java.lang.String;

public class SoundHandle {
	
	private static Sequence sequence;
	private static Sequencer loop;
	
	public static void loopMusic(URL url) throws Exception {

        sequence = MidiSystem.getSequence(url);
        loop = MidiSystem.getSequencer();

        loop.open();
        loop.setSequence(sequence);
        //sequencer.setLoopStartPoint(2000);
        //sequencer.setLoopEndPoint(4000);
        loop.setLoopCount(256*256);

        loop.start();
	}
	
	public static void endMusicLoop() {
		loop.close();
	}
	
	public static synchronized void playSound(final String url) {
  		new Thread(new Runnable() {
  		// The wrapper thread is unnecessary, unless it blocks on the
  		// Clip finishing; see comments.
    	public void run() {
      		try {
        		Clip clip = AudioSystem.getClip();
        		AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("res/snd/" + url));
        		clip.open(inputStream);
        		clip.start(); 
      		} catch (Exception e) {
        		System.err.println(e.getMessage());
      		}
    		}
  		}).start();
	}
}