package model;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.*;

/**
 * The GameModel saves data about the game, including the racecar.
 * It handles most of the calculations for the racegame.
 */
public class GameModel {
    /**
     * The car that is driven on the racetrack
     */
    private Car car;
    private Subsoil subsoil = new Subsoil();
    private GameState gameState;
    public static final int GAME_WIDTH = 1300;
    public static final int GAME_HEIGHT = 800;
    public static final int CRASH_SPEED_LIMIT = 80;
    private Rectangle gameAreaCollissionMask;

    
    public Car getCar() {
    	return car; 
    }
    /**
     * Creates a gameModel, that handles most of the actions
     */
    public GameModel() {
    	gameState = GameState.START_SCREEN;
        //initialize Car, default data in GameView
        car = initializeCar();
        gameState = GameState.RUNNING;
        gameAreaCollissionMask = new Rectangle(0,0,GAME_WIDTH, GAME_HEIGHT);
       
        
    }

    /**
     * Initializes a car with the initial values
     *
     * @return the initialized car
     */
    private Car initializeCar() {
        //initialize a new car and give it the init values set in the static variables
        car = new Car();
        return car;
    }
    
    public GameState getGameState() {
    	return gameState;
    }
       
    
    private void checkCollision() {
		Rectangle carCollisionMask = car.getCollisionMask();
		Rectangle obstacleCollissionMask = subsoil.getObstacleCollissionMask();
		boolean crash = false;
		boolean fatalCrash = false;
		
		if(!carCollisionMask.getBoundsInParent().intersects(gameAreaCollissionMask.getBoundsInLocal())){
			crash = true;
		} else if (carCollisionMask.getBoundsInLocal().intersects(obstacleCollissionMask.getBoundsInLocal())){
			if(car.getSpeed() >= CRASH_SPEED_LIMIT){
				fatalCrash = true;
			} else {
				System.out.println("Finish");
				gameState = GameState.SHOW_SCORE;
			}
		
			if(crash = true){
			gameState = GameState.GAME_OVER;
		}
	}
}
