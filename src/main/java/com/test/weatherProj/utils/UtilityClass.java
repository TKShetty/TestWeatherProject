package com.test.weatherProj.utils;

public class UtilityClass {


	
	public static double getMean(float[] data) {
		double mean = 0.0;
		for (int i = 0; i < data.length; i++) {
			mean += data[i];
		}
		return mean /= data.length;
	}
	  
	public static double getVariance(float[] data) {
		double mean = getMean(data);
		double variance = 0;
		for (int i = 0; i < data.length; i++) {
			variance += Math.pow(data[i] - mean, 2);
		}
		variance=variance /= data.length;
		return variance;
	}
}
