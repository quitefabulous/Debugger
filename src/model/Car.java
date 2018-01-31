package model;

import controller.GameController;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

/**
 * Class car represents the race-car in the race game.
 */
public class Car {
	final double CAR_LENGTH = 42.55;
	final double CAR_WIDTH = 20.27;
	final double CAR_WEIGHT = 1615;
	double roation;
	double speed = 0;
	boolean totalLoss;
	double airDensity;
	double accelerate = 111;
	long timeGone;
	private Rectangle collissionMask;
	Point2D posCar = new Point2D (200,300);
	
	public Rectangle getCollissionMask() {
		return collissionMask;
	}
	
	
// Ab hier folgen Methoden die zur Berechnung der Bewegung benutzt werden

//	
	public void straightAcc() {
		posCar.add(speed, 0);
	}
	
	public void leftAcc() {
		posCar.add(speed, -speed);
	}
	
	public void rightAcc() { 
		posCar.add(speed, speed);
	}
	
	public void backAcc() {
		posCar.add(-speed, 0);
		
	}


// Berechnung der Rotation
	public void rotate() {
		
	}
	
	public double calcSpeed(double time) {
		
	
		double acceleration = accelerate - calcFriction() - calcAirDensity(speed);
		speed = speed + acceleration * time;
		return speed;
	}
	

// Berechnung der Rollreibung	
	public double calcFriction() {
		double friction;
		double frictionCoefficient;
		double normalForce = 9.807 ;
		Subsoil isBlack = new Subsoil();
		if (isBlack.color() == true) {
			frictionCoefficient = 0.015;
		} else {
			frictionCoefficient = 0.05;
		}
		// Normalkraft == Erdanziehungskraft in m/s
		friction = normalForce * frictionCoefficient;
		return friction;
	}


//Berechnung des Luftwiderstands
	public double calcAirDensity(double speed) {
		double density;
		double dragCoefficient = 0.28;
		double face = 2.19;
		double air = 1.2041;
		density = dragCoefficient * face * (air/2) * (speed * speed);
		return density;
		
	}
	
}
