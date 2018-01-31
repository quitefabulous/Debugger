package application;

import controller.GameController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import model.GameModel;
import view.GameView;

public class Main extends Application {

    private long oldTime;

    @Override
    public void start(Stage stage) throws Exception{

        //Create the instances for the game
        GameView gameView = new GameView(stage);
        GameModel gameModel = new GameModel();
        GameController gameController = new GameController(gameModel, gameView);

        /*
         * Start the gameloop.
         * It is executed every frame, the long now is the current timestamp
         */
        new AnimationTimer() {
            @Override
            public void handle(long now) {

                /*
                  timeDifferenceInSeconds calculates the time between 2 frames.
                  It compares the last time with the current time (now) and
                  is divided by 1000000000.0 to get the time in seconds
                 */
                double timeDifferenceInSeconds = (now - oldTime) / 1000000000.0;

                /*
                  Sets the oldTime to now, so the next loop can take the difference
                 */
                oldTime = now;
 //               gameModel.updateGame(timeDifferenceInSeconds);
        //        gameView.renderGameModel(gameModel);
                /*
                  Use the controller to update all dependencies
                 */
                gameController.updateContinuously(timeDifferenceInSeconds);

            }
        }.start();

        stage.show();
    }


    /**
     * Launches the Application (calls start overriden start method)
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
