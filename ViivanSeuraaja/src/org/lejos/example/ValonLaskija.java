package org.lejos.example;

public class ValonLaskija {
	private static final int valonaytteetLkm = 20;
	
	public ValonLaskija() {
		
	}
	
	public static int getValonKeskiarvo() {
		int summa = 0;
		for (int i = 0; i < valonaytteetLkm; i++) {
			summa += Robotti.lightSensor.getLightValue();
		}
		return summa / valonaytteetLkm;
	}
}
