package mainGame;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.*;
import javax.sound.sampled.*;


//All sound is played from this class; All music is 100% legally obtained
//and there is no need to investigate any further
public class Sound {

	
   
	//Call this function to play music; second parameter decides amount sound file is played (0 = zero repeats)
	public static void playSound(String file, int loopAmount) throws InterruptedException, UnsupportedAudioFileException, 
    LineUnavailableException, IOException  {
    		
			//Reads file in and then plays it
            File playThis = new File(file);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(playThis);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            
            //This is where loop amount is located
            clip.loop(loopAmount);
            Thread.sleep(clip.getMicrosecondLength());
            
    } //End of playSound
    
   
} //End of sound class


















/*
package mainGame;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.*;
import javax.sound.sampled.*;
public class Sound {

    public static void main(String[] args) throws InterruptedException, UnsupportedAudioFileException, 
    LineUnavailableException, IOException  {
    	
            File file = new File("Sound/pacman_Sound.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            Thread.sleep(clip.getMicrosecondLength());
            
    } //End of main

} //End of sound class



*/






























/*
package mainGame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;


public class Sound {

	
public static synchronized void playSound(final String url) {
	
	
  new Thread(new Runnable() {
  
    public void run() {
      try {
        Clip clip = AudioSystem.getClip();
        System.out.println(url);
        //URL myUrl = getClass().getResource("Sound/" + url);
        System.out.println("DEBUG: URL is " + url);
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
        Game.class.getResourceAsStream(url.toString()));
        clip.open(inputStream);
        clip.start(); 
      } catch (Exception e) {
    	  e.printStackTrace();
      }
    }
  } //End of thread
  
  ).start();

	} //End of playSound

} //End of class

*/
