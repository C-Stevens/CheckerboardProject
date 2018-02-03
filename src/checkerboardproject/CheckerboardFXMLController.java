/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkerboardproject;

import javafx.scene.layout.GridPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Colin
 */
public class CheckerboardFXMLController implements Initializable, Startable {
    private Stage stage;
    private Checkerboard checkerBoard;
    private final Color[] blueColorScheme = {Color.SKYBLUE, Color.DARKBLUE};
    // Initialize variables with default values
    private int numRows = 8;
    private int numCols = 8;
    
    @FXML
    private VBox vBox;
    @FXML
    private Pane boardPane;
    
    @FXML
    private void handle16x16(Event event) {
        checkerBoard.setNumCols(16);
        checkerBoard.setNumRows(16);
        refresh();
    }
    @FXML
    private void handle10x10(Event event) {
        checkerBoard.setNumCols(10);
        checkerBoard.setNumRows(10);
        refresh();
    }
        @FXML
    private void handle8x8(Event event) {
        checkerBoard.setNumCols(8);
        checkerBoard.setNumRows(8);
        refresh();
    }
    @FXML
    private void handle3x3(Event event) {
        checkerBoard.setNumCols(3);
        checkerBoard.setNumRows(3);
        refresh();
    }
    
    @FXML
    private void handleDefaultColor(Event event) {
        // Build a new board (no pass will make default colors)
        checkerBoard = new Checkerboard(checkerBoard.getNumRows(), checkerBoard.getNumCols(), stage.getWidth(), stage.getHeight());
        setCheckerboard(checkerBoard);
        refresh();
    }
    @FXML
    private void handleBlueColor(Event event) {
        // Build a new board based off the current, but pass light and dark colors
        checkerBoard = new Checkerboard(checkerBoard.getNumRows(), checkerBoard.getNumCols(), stage.getWidth(), stage.getHeight(), blueColorScheme[0], blueColorScheme[1]);
        setCheckerboard(checkerBoard);
        refresh();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // For some reason, having this in start() will not set the proper value on run
       vBox.setVgrow(boardPane, Priority.ALWAYS);
    }
    
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        
        // Initialize the board with default values (and default colors)
        checkerBoard = new Checkerboard(numRows, numCols, stage.getWidth(), stage.getHeight());
        setCheckerboard(checkerBoard);
        
        // Set event handlers for height/width change
        ChangeListener<Number> lambdaChangeListener = (ObservableValue<? extends Number> observable, Number oldValue, final Number newValue) -> {
            refresh();
        };
        this.stage.widthProperty().addListener(lambdaChangeListener);
        this.stage.heightProperty().addListener(lambdaChangeListener);
        
        // Initialize board
        refresh();
    }
    
    private void setCheckerboard(Checkerboard cb) {
        boardPane.getChildren().clear();
        boardPane.getChildren().add(cb.getBoard());
    }
    
    private void refresh() {
        checkerBoard.build(boardPane.getWidth(), boardPane.getHeight());
    }
    
}
