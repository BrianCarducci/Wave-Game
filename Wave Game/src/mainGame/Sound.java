/*
 * By: Rob Laudadio
 * 11/9/17
 * This class takes advantage of multi-threading
 * in Java by having each sound file play on a 
 * different thread game lag is reduced
 */

package mainGame;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound implements Runnable {
	private Clip clip;
	private File file;
	private AudioInputStream audioIn;

	@Override
	public void run() {

		// If loops determine which noise to play
		if (Thread.currentThread().getName() == "music") {
			System.out.println("Background Music Triggered");
			file = new File("Sound/neonDrive.wav");
		} else if (Thread.currentThread().getName() == "PutinHealth") {
			System.out.println("Putin Noise Triggered");
			file = new File("Sound/putinSound.wav");
		} else if (Thread.currentThread().getName() == "EminemDecrease") {
			System.out.println("Eminem Noise Triggered");
			file = new File("Sound/slimShady.wav");
		} else if (Thread.currentThread().getName() == "twitterNoise") {
			System.out.println("Twitter Noise Triggerd");
			file = new File("Sound/fakeNews.wav");
		} else if (Thread.currentThread().getName() == "NFLSound") {
			System.out.println("NFL Noise Triggered");
			file = new File("Sound/nflTheme.wav");
		} else if (Thread.currentThread().getName() == "reload") {
			System.out.println("Reload Triggered");
			file = new File("Sound/reloadNoise.wav");
		} else if (Thread.currentThread().getName() == "death") {
			System.out.println("Death Triggered");
			file = new File("Sound/starwarsScream.wav");
		} else if (Thread.currentThread().getName() == "comradeVictory") {
			System.out.println("Programmers of the world unite!");
			file = new File("Sound/sovUnionAnthem.wav");
		}

		// This is where file is read in
		audioIn = null;
		try {
			audioIn = AudioSystem.getAudioInputStream(file);
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
		clip = null;
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		try {
			clip.open(audioIn);
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}

		// Increases volume of all sound files except background music
		if (Thread.currentThread().getName() != "music") {
			FloatControl gainControl = (FloatControl) clip
					.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(6.0f);
			
			
		}

		clip.start();

		// Changes loop amount to infinite if it is background music
		if (Thread.currentThread().getName() == "music") {
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		try {
			System.out.println("File: " + Thread.currentThread().getName());
			Thread.sleep(clip.getMicrosecondLength());
			System.out.println(Thread.currentThread().isAlive());

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		clip.stop();
		try {
			audioIn.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void playEndMusic() {
		Thread thread = new Thread(new Sound(), "comradeVictory");
		thread.start();
	}

}
