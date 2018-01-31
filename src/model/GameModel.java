package model;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * The GameModel saves data about the game, including the racecar.
 * It handles most of the calculations for the racegame.
 */
public class GameModel {
    /**
     * The car that is driven on the racetrack
     */
    private Car car;
    private GameState gameState;
    public static final int GAME_WIDTH = 1300;
    public static final int GAME_HEIGHT = 800;
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
    
    public void updateGame() {
    	
    }
    
    public void renderGameModel() {
    	
    }
}
//    private void checkCollision() {
//		Rectangle landerCollisionMask = lander.getCollisionMask();
//		boolean crash = false;
//		
//		if(!landerCollisionMask.getBoundsInParent().intersects(gameAreaCollisionMask.getBoundsInLocal())){
//			crash = true;
//		} else if (landerCollisionMask.getBoundsInLocal().intersects(groundCollisionMask.getBoundsInLocal())){
//			if(lander.getSpeed().magnitude() > CRASH_SPEED_LIMIT || lander.getTiltAngle() > CRASH_TILT_LIMIT){
//				System.out.println("Crash Ground");
//				crash = true;
//			} else {
//				System.out.println("Successful Landing");
//				gameState = GameState.SHOW_SCORE;
//			}
//		}
//		
//		for (Triangle obstacle: obstacles) {
//			if(Shape.intersect((Shape)obstacle.getCollisionMask(), (Shape)landerCollisionMask).getBoundsInLocal().getWidth() != -1.0){
//				System.out.println("Crashed into Obstacle: " + obstacle.getCollisionMask());
//				crash = true;
//			}
//		}	
//		
//		if(crash){
//			lander.setThrustLevel(0);
//			gameState = GameState.GAME_OVER;
//		}
//	}
//}