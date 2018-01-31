package controller;

import application.*;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import model.*;
import view.GameView;
import javafx.scene.input.*;

public class GameController {

    private GameModel gameModel;
    private GameView gameView;
    private Scene scene;
    private boolean doAccelerate = false;
    
    public GameController(GameModel gameModel, GameView gameView) {
        this.gameView = gameView;
        this.gameModel = gameModel;
        this.scene = gameView.getScene();
        //Set up keylistener
        setUpInputHandler();
    }
    


    public void updateContinuously(double timeDifferenceInSeconds) {
    	Car newCar = gameModel.getCar();
    	
    	gameView.drawCar(newCar);
    	if(doAccelerate == true) {
    		newCar.calcAirDensity(timeDifferenceInSeconds);
    		newCar.calcFriction();
    		newCar.calcSpeed(timeDifferenceInSeconds);
    	}
 	}
    
    
    private void setUpInputHandler() {
        /*
         * Useful actions:
         * setOnKeyPressed, setOnKeyReleased
         */
    
    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent event) {
			Car newCar = gameModel.getCar();
			String code = event.getCode().toString();
			
			
			// It only reacts if the game is running.
			if (gameModel.getGameState() == GameState.RUNNING) {
				switch (code) {
				case "UP":
					doAccelerate = true;
					newCar.straightAcc();
					System.out.println("UP");
					break;
				case "LEFT":
					doAccelerate = true;
					newCar.leftAcc();
					System.out.println("Left");
					break;
				case "RIGHT":
					doAccelerate = true;
					newCar.rightAcc();
					System.out.println("Right");
					break;
				case "DOWN" :
					doAccelerate = true;
					newCar.backAcc();
					System.out.println("DOWN");
					break;
				default:
					break;
				}
			}
		}
    });
    
    scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
    	@Override
		public void handle(KeyEvent event) {
			doAccelerate = false;
    	}
    });
}
}