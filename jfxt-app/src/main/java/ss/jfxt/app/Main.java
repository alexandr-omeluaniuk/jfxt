/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ss.jfxt.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.StageStyle;

/**
 *
 * @author alex
 */
public class Main extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        stage.setMaximized(true);
        stage.initStyle(StageStyle.UNDECORATED);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/primary.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().setAll("style/global.css");
        stage.setScene(scene);
        stage.show();
    }
    
}
