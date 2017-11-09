package mainGame;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound implements Runnable {
	private Clip clip;
	private File file;
	private AudioInputStream audioIn;

	@Override
	public void run() {
		if (Thread.currentThread().getName() == "music") {
			file = new File("Sound/neonDrive.wav");
		} else
		if (Thread.currentThread().getName() == "damage") {
			file = new File("Sound/firedTrump.wav");
		}

		audioIn = null;
		try {
			audioIn = AudioSystem.getAudioInputStream(file);
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clip = null;
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clip.open(audioIn);
		} catch (LineUnavailableException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clip.start();
		if (Thread.currentThread().getName() == "music") {
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		try {
			System.out.println(Thread.currentThread().getName());
			Thread.sleep(clip.getMicrosecondLength());
			System.out.println(Thread.currentThread().isAlive());

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clip.stop();
		try {
			audioIn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}


