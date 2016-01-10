package org.lejos.example;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;

public class Liikkuja extends Thread {

	NXTMotor mB = new NXTMotor(MotorPort.B);
	NXTMotor mC = new NXTMotor(MotorPort.C);
	int vari;
	int kaanna;
	int bKaanna;
	int cKaanna;
	int power = 22;
	int threshold;
	
	public Liikkuja() {
		
	}
	
	public void run() {
		
		LCD.clear();
		LCD.drawString("Started Liikkuja", 0, 2);
		
		
		while (!Button.ESCAPE.isPressed()) {
			
			threshold = (Robotti.arvo.getMusta() + Robotti.arvo.getValkoinen())/2;
			vari = ValonLaskija.getValonKeskiarvo();
			cKaanna = power - 50 * (threshold-vari)/(Robotti.arvo.getValkoinen() - Robotti.arvo.getMusta());
			mC.setPower(cKaanna);
			mC.forward();
			
			bKaanna = power - 50 * (threshold-vari)/(Robotti.arvo.getValkoinen() - Robotti.arvo.getMusta());
			mB.setPower(bKaanna);
			mC.forward();
		}
	}

}