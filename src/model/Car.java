package model;

import controller.GameController;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import javafx.scene.canvas.*;
import java.util.*;
/**
 * Class car represents the race-car in the race game.
 */
public class Car {
	final double CAR_LENGTH = 42.55;
	final double CAR_WIDTH = 20.27;
	final double CAR_WEIGHT = 1615;
	double rotation = 90;
	double rotationSteps = 3.5;
	double speed = 0;
	boolean totalLoss;
	double airDensity;
	double accelerate = 111;
	long timeGone;
	private Rectangle carCollissionMask = new Rectangle(0,0, 42.55, 20.27);
	Point2D posCar = new Point2D (200,300);

	public double getSpeed() {
		return speed;
	}
	
	public double getRotation() {
		return rotation;
	}
	
	public Point2D getPosCar() {
		return posCar;
	}
// Ab hier folgen Methoden die zur Berechnung der Bewegung benutzt werden

//	
	public void straightAcc(double time) {
		posCar = posCar.add(speed * Math.cos(Math.toRadians(rotation))*time, speed * Math.sin(Math.toRadians(rotation))*time);
	}
	
	public void leftAcc() {
		rotation = rotation - rotationSteps;
	}
	
	public void rightAcc() { 
		rotation = rotation + rotationSteps;
	}
	
	public void backAcc(double time) {
		posCar = posCar.add(speed * Math.cos(Math.toRadians(rotation))*time, speed * Math.sin(Math.toRadians(rotation)*time)).multiply(-1);
		
	}
	
	public void roll(double time) { {
		double accelerateRoll = -calcFriction() - calcAirDensity(speed);
		speed = speed + accelerateRoll* time;
		posCar = posCar.add(speed * Math.cos(Math.toRadians(rotation)), speed * Math.sin(Math.toRadians(rotation)));
		}
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
	
	
	public Rectangle getCollisionMask() {
		return carCollissionMask;
	}
	
	
}
