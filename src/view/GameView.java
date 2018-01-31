package view;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.*;
import javafx.scene.transform.*;
import javafx.stage.Stage;
import model.Car;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;

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
    	car.setFill(Color.BEIGE);
    	
    	Rotate r = new Rotate(rotation, posCar.getX(), posCar.getY());
    	car.graphicsContext.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    	
        
    }
    

}