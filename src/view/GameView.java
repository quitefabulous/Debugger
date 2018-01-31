package view;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.*;
import javafx.scene.transform.*;
import javafx.stage.Stage;
import model.Car;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;

/**
 * Contains every GUI element
 */
public class GameView {

	Rectangle car = new Rectangle();
    //The scene where all is stacked up
    private Scene scene;

    //Stackpane, where all dialogs are stacked
    private StackPane rootPane;
    private Pane gamePane;
    private GridPane startScreenDialog;
    private GridPane pausedDialog;
    private VBox gameOverDialog;
    
    private static final double TEXT_COLUMN_WIDTH_DEFAULT = 250;
    public Scene getScene() {
        return scene;
    }

    /**
     * GameView object for setting up the GUI
     *
     * @param stage the primary stage
     */
    public GameView(Stage stage) {

        stage.setTitle("Rennspiel");
        stage.setResizable(false);
        stage.sizeToScene();

        rootPane = new StackPane();
        BorderPane menuPane = new BorderPane();

        
        // Create MenuBar
        MenuBar menuBar = new MenuBar();
        
        // Create menus
        Menu restartMenu = new Menu("Restart(R)");
        Menu pauseMenu = new Menu("Pause(P)");
        Menu optionsMenu = new Menu("Options(O)");
        Menu helpMenu = new Menu ("Help(H)");
        MenuItem exitItem = new MenuItem ("Exit" + "   ");
      
        
        
        // Set Accelerator for the whole Menubar
        
        restartMenu.setAccelerator(KeyCombination.keyCombination("R"));
        pauseMenu.setAccelerator(KeyCombination.keyCombination("P"));
        optionsMenu.setAccelerator(KeyCombination.keyCombination("O"));
        helpMenu.setAccelerator(KeyCombination.keyCombination("H"));
        exitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
 
        // When user click on the Exit item.
        exitItem.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        	
        
        // Add Menus to the MenuBar
        menuBar.getMenus().addAll(restartMenu, pauseMenu, optionsMenu, helpMenu);
        optionsMenu.getItems().addAll(exitItem);

        menuPane.setTop(menuBar);
        scene = new Scene(rootPane, 1300, 800);
 

        setUpGameWindow();
        rootPane.getChildren().add(menuPane);
        stage.setTitle("Racecar Simulator 3000");
        stage.setScene(scene);
        stage.show();
        
        

        stage.setScene(scene);
    }
    /**
     * Sets up the main game window with the course as panebackground,
     * the car in the initial Position
     */
    private void setUpGameWindow() {
    	
        gamePane = new BorderPane();

        
        //Ellipse aussen: x=550, y = 350
        //Ellipse innen: x = 450 , y = 250
        //draw elipse
        Ellipse outerEllipse = new Ellipse();
        outerEllipse.setCenterX(650.0f);
        outerEllipse.setCenterY(400.0f);
        outerEllipse.setRadiusX(550.0f);
        outerEllipse.setRadiusY(350.0f);
        outerEllipse.setFill(Color.AZURE);
        
        Ellipse innerEllipse = new Ellipse();
        innerEllipse.setCenterX(650.0f);
        innerEllipse.setCenterY(400.0f);
        innerEllipse.setRadiusX(450.0f);
        innerEllipse.setRadiusY(250.0f);
        innerEllipse.setFill(Color.WHITE);
        

    	
;

        rootPane.getChildren().add(gamePane);
        gamePane.getChildren().add(outerEllipse);
        gamePane.getChildren().add(innerEllipse);
        gamePane.getChildren().add(car);
        
        
    }
    
    public void drawCar(Car newCar) {
    	Point2D posCar = newCar.getPosCar();
    	double rotation = newCar.getRotation();
    	car.setX(posCar.getX());
    	car.setY(posCar.getY());
    	car.setWidth(42);
    	car.setHeight(20);
    	car.setFill(Color.BLACK);
    	
//    	Rotate r = new Rotate(rotation, posCar.getX(), posCar.getY());
    	car.setRotate(rotation);
    	
        
    }
    
    // Dialogues from here on
    private GridPane buildPausedDialog() {
        GridPane pausedDialog = new GridPane();
        pausedDialog.setVgap(10);
        pausedDialog.setHgap(10);
        pausedDialog.setBackground(new Background(new BackgroundFill(Color.gray(1, 0.5), null, null)));
        pausedDialog.setPadding(new Insets(10));

        String arrowKeysURL = this.getClass().getResource("/resources/arrow-keys.png").toString();
        ImageView arrowKeysImg = new ImageView(arrowKeysURL);
        arrowKeysImg.setFitWidth(300);
        arrowKeysImg.setPreserveRatio(true);

        String spaceBarURL = this.getClass().getResource("/resources/space-bar.png").toString();
        ImageView spaceBarImg = new ImageView(spaceBarURL);
        spaceBarImg.setFitWidth(480);
        spaceBarImg.setPreserveRatio(true);

        Text startScreenBody1 = new Text("Versuche viele Punkte zu erreichen, indem du "
            + "m�glichst weit entfernt vom Startpunkt (range) und "
            + "mit m�glichst niedriger Geschwindigkeit landest");
        startScreenBody1.setFont(Font.font(18));
        startScreenBody1.setWrappingWidth(TEXT_COLUMN_WIDTH_DEFAULT);

        Text startScreenBody2 = new Text("Shortcuts:\n" + "Enter - Spiel starten\n"
            + "P - Spiel pausieren\n" + "R - Spiel zur�cksetzen");
        startScreenBody2.setFont(Font.font(18));
        startScreenBody2.setWrappingWidth(180);

        Text startScreenBody3 = new Text("Sources & Attribution:\n"
            + "arrow keys image (adapted): By vecteezy,\nhttp://www.freepik.com/free-vector/\narrow-keys-vectors_558586.htm\n");
        startScreenBody3.setFont(Font.font(12));
        startScreenBody3.setWrappingWidth(TEXT_COLUMN_WIDTH_DEFAULT);

        pausedDialog.add(startScreenBody1, 0, 0);
        pausedDialog.add(startScreenBody2, 1, 0);
        pausedDialog.add(startScreenBody3, 0, 1);
        pausedDialog.add(arrowKeysImg, 2, 0);
        pausedDialog.add(spaceBarImg, 1, 1, 2, 1);

        return pausedDialog;
      }
    
    private GridPane buildStartScreenDialog() {
        GridPane startScreenDialog = buildPausedDialog();

        Button startGameButton = new Button("Start\nGame");
        startGameButton.setFont(Font.font(20));
        startGameButton.setPrefSize(100, 100);
        startGameButton.setId("startButton");
        startGameButton.setFocusTraversable(false);

        startScreenDialog.add(startGameButton, 3, 0, 1, 2);

        return startScreenDialog;
      }
    private VBox buildGameOverDialog() {
        Text gameOverTextHeader = new Text("Game Over");
        gameOverTextHeader.setFont(Font.font(60));

        Text gameOverTextBody =
            new Text("Your car crashed... and is on fire... RUN!"
            		+ "/nPress R to restart the course.");
        gameOverTextBody.setFont(Font.font(18));

        VBox gameOverDialog = new VBox(10, gameOverTextHeader, gameOverTextBody);

        gameOverDialog
            .setBackground(new Background(new BackgroundFill(Color.gray(1, 0.5), null, null)));
        gameOverDialog.setPadding(new Insets(10));
        gameOverDialog.setAlignment(Pos.CENTER);

        return gameOverDialog;
      }
}