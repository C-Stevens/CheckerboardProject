/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkerboardproject;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Colin
 */
public class Checkerboard {
    private int numRows;
    private int numCols;
    private double boardWidth;
    private double boardHeight;
    private double rectangleWidth;
    private double rectangleHeight;
    private Color lightColor;
    private Color darkColor;
    private static Color[] defaultColorScheme = {Color.RED, Color.BLACK};
    private AnchorPane anchorPane;
        
    public Checkerboard(int numRows, int numCols, double boardWidth, double boardHeight) {
        // Call other method with default colors
        this(numRows, numCols, boardWidth, boardHeight, defaultColorScheme[0], defaultColorScheme[1]);
     
    }
    public Checkerboard(int numRows, int numCols, double boardWidth, double boardHeight, Color lightColor, Color darkColor) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.lightColor = lightColor;
        this.darkColor = darkColor;

        anchorPane = new AnchorPane();
    }
    
    public AnchorPane build(double boardWidth, double boardHeight) {
        clear();
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        anchorPane.setPrefWidth(boardWidth);
        anchorPane.setPrefHeight(boardHeight);
        
        rectangleWidth = Math.ceil(boardWidth / (double)numCols);
        rectangleHeight = Math.ceil(boardHeight / (double)numRows);
        for(int row=0; row < numRows; row++) {
            for(int col=0; col < numCols; col++) {
                Rectangle rect = new Rectangle((rectangleWidth * col), (rectangleHeight * row), rectangleWidth, rectangleHeight);
                if(((col+row) % 2) == 0) { // If the column is even, set to the light color
                    rect.setFill(lightColor);
                } else {
                    rect.setFill(darkColor);
                }
                anchorPane.getChildren().add(rect);
            }
        }
        return anchorPane;
    }
    
    public void clear() {
        anchorPane.getChildren().clear();
    }
    
    // Setters
    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }
    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }
    public void setLightColor(Color color) {
        this.lightColor = color;
    }
    public void setDarkColor(Color color) {
        this.darkColor = color;
    }
    
    // Getters
    public AnchorPane getBoard() {
        return anchorPane;
    }
    public int getNumRows() {
        return numRows;
    }
    public int getNumCols() {
        return numCols;
    }
    public double getWidth() {
        return boardWidth;
    }
    public double getHeight() {
        return boardHeight;
    }
    public Color getLightColor() {
        return lightColor;
    }
    public Color getDarkColor() {
        return darkColor;
    }
    public double getRectanleWidth() {
        return rectangleWidth;
    }
    public double getRectangleHeight() {
        return rectangleHeight;
    }
}
