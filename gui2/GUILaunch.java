package gui2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.HashMap;

import gui2.model.View;
import gui2.util.GameViewEvent;
import gui2.game.GameController;

public class GUILaunch extends Application{

    //for set event
    private static GUILaunch singleton;
    private Method eventMethod;

    public GUILaunch(){
        singleton = this;
    }

    public static GUILaunch getSingleton(){
        return singleton;
    }

    private Stage stage;

    private static Map<View, Scene> scenesMap = new HashMap<>();

    @Override public void start(Stage primaryStage){
        stage = primaryStage;
        stage.setTitle("JavaRPGFX");
        stage.setResizable(false);
        add(View.TITLE);
        add(View.TITLESCENE2);
        stage.setScene(scenesMap.get(View.TITLE));
        stage.show();
    }

    public void add(View view){
        var loader = new FXMLLoader(getClass().getResource(view.fxmlLocation));

        try {
            Parent root = loader.load();
            GameViewEvent controller = loader.getController();

            Scene scene = new Scene(root);
            scene.setOnKeyPressed(event -> controller.keyPressed(event));
            scenesMap.put(view,scene);
        } catch (IOException e){
            e.printStackTrace();
            Platform.exit();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public void addAndSendToGame(View view,String value) {
        var loader = new FXMLLoader(getClass().getResource(view.fxmlLocation));

        try {
            Parent root = loader.load();
            GameViewEvent controller = loader.getController();
            ((GameController) controller).displayName(value);
            Scene scene = new Scene(root);
            scene.setOnKeyPressed(event -> controller.keyPressed(event));
            scenesMap.put(view,scene);
        } catch (IOException e){
            e.printStackTrace();
            Platform.exit();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public void remove(View view){
        scenesMap.remove(view);
    }

    public void activate(View view,Stage previousStage){
        previousStage.setScene(scenesMap.get(view));
    }

    public void reActivate(View view,Stage previousStage){
        scenesMap.clear();
        add(view);
        activate(view,previousStage);
    }
    public static void main(String[] args) throws Exception{
        launch(args);
    }
}
