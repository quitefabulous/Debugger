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
    public boolean doAccelerate = false;
    
    
    public GameController(GameModel gameModel, GameView gameView) {
        this.gameView = gameView;
        this.gameModel = gameModel;
        this.scene = gameView.getScene();
        //Set up keylistener
        setUpInputHandler();
    }
    
    public boolean getDoAccelerate() {
    	return doAccelerate;
    }

    public void updateContinuously(double timeDifferenceInSeconds) {
    	Car newCar = gameModel.getCar();
    	newCar.calcAirDensity(timeDifferenceInSeconds);
    	newCar.calcFriction();
    	newCar.calcSpeed(timeDifferenceInSeconds);
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
					doAccelerate = false;
					break;
				case "LEFT":
					doAccelerate = true;
					newCar.leftAcc();
					break;
				case "RIGHT":
					doAccelerate = true;
					newCar.rightAcc();
				case "DOWN" :
					doAccelerate = true;
					newCar.backAcc();
				default:
					break;
				}
			}
		}
    });
}
}