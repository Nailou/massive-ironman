package org.lejos.example;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;

public class Robotti {

	
	static LinjanArvo arvo = new LinjanArvo();
	static LightSensor lightSensor = new LightSensor(SensorPort.S1);
	
	public Robotti() throws InterruptedException {
		odotaKayttajaa("valkoinen");
		arvo.setValkoinen(getThreshold());
		odotaKayttajaa(null);
		
		odotaKayttajaa("musta");
		arvo.setMusta(getThreshold());
		odotaKayttajaa(null);
	}
	
	
	private synchronized void odotaKayttajaa(String viesti) throws InterruptedException {
		if (viesti != null) {
			LCD.drawString(viesti, 0, 2, false);
		}
		Sound.twoBeeps();
		Button.ESCAPE.waitForPressAndRelease();
	}
	
	private int getThreshold() {
		ValonLaskija kalibrointi = new ValonLaskija();
		int arvo = kalibrointi.getValonKeskiarvo();
		LCD.drawInt(arvo, 4, 0, 3);
		return arvo;
	}
	
	private void kaynnistys() {
		Thread liikkuja = new Thread (new Liikkuja());
		liikkuja.start();
	}
	
	public static void main(String[] args) throws InterruptedException {
		Robotti robotti = new Robotti();
		robotti.kaynnistys();

	}

}
