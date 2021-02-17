package CSE464.Program9;

import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.awt.Color;
import java.io.File;

import edu.princeton.cs.algs4.*;

public class Util {
	public static double getAverage(double[] nums) {
		double sum = 0;
		for (double d : nums) {
			sum += d;
		}
		return sum/nums.length;
	}
	
    public static double getVariance(double[] nums) {
        double mean = getAverage(nums);
        double temp = 0;
        for(double d : nums)
            temp += (d-mean)*(d-mean);
        return temp/(nums.length-1);
    }

    public static double getStdDev(double[] nums) {
        return Math.sqrt(getVariance(nums));
    }
    
	public static int[] generateArray(int n, int max) {
		if (n < 0 || max < 0)
			throw new IllegalArgumentException();
		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = StdRandom.uniform(max);
		}
		return array;
	}
	
	public static int getRandomInt(int min, int max) {
		Random rnd = new Random();
		return rnd.nextInt(max-min)+min;
	}
	

	public static Color getRandomColor() {
		Random rnd = new Random();
		float r = (float) (rnd.nextFloat());
		float g = (float) (rnd.nextFloat());
		float b = (float) (rnd.nextFloat());
		return new Color(r, g, b);
	}
	
	public static void playPing() {
		try {
			File soundFile = new File("clink.wav"); //you could also get the sound file with an URL
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);    
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
