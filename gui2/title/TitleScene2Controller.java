package gui2.title;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import gui2.GUILaunch;
import gui2.model.View;
import gui2.util.GameViewEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class TitleScene2Controller extends GameViewEvent implements Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView cursor;

    @FXML
    private Label backToButtonLabel;

    @FXML
    private Label nameTextLabel;

    @FXML
    private Label nextToButtonLabel;

    @FXML
    private Pane titleScene2Pane;

    private double positionX;
    private double positionY;
    private Stage stage;
    private final double marginX = 29.0;
    private final double marginY = 33.0;
    private final int width = 13;
    private final int height = 2;
    private static Map<Double, String> textLocationMap1 = new HashMap<>();
    private static Map<Double, String> textLocationMap2 = new HashMap<>();
    private double[] cordinateX = new double[width+1];
    GUILaunch app = GUILaunch.getSingleton();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.positionX = cursor.getX();
        this.positionY = cursor.getY();

        for(int i=0;i<cordinateX.length;i++){
            cordinateX[i] = marginX*i;
        }
        char c = 'A';
        for(int i = 0;i<=('M'-'A');i++){
            textLocationMap1.put(cordinateX[i],String.valueOf(c++));
        }
        char c2 = 'N';
        for(int i = 0;i<=('Z'-'M');i++){
            textLocationMap2.put(cordinateX[i],String.valueOf(c2++));
        }
    }

    @FXML
    @Override public void keyPressed(KeyEvent e){

        switch(e.getCode()){
            case Z:  //決定
            if(cursor.getY() == 180){
                if(cursor.getX() == 340){
                    app.addAndSendToGame(View.GAME,nameTextLabel.getText());
                    Window window = titleScene2Pane.getScene().getWindow();
                    stage = (Stage) window;
                    app.activate(View.GAME,stage);
                }
                else if(cursor.getX() == -15){
                    Window window = titleScene2Pane.getScene().getWindow();
                    stage = (Stage) window;
                    app.activate(View.TITLE,stage);
                }
            }
            else setText1by1();
            break;
            case UP:
            if(cursor.getY()==180 && cursor.getX()==340){
                cursor.setX(marginX*(width-1));
                cursor.setY(marginY*(height-1));
            }
            else if(cursor.getY()==180 && cursor.getX()==-15){
                cursor.setX(0);
                cursor.setY(marginY*(height-1));
            }
            else if(cursor.getY() > positionY){
                cursor.setY(cursor.getY()-marginY);
            }
            break;
            case DOWN:
            if((positionY+marginY*(height-1))>cursor.getY()){
                cursor.setY(cursor.getY()+marginY);
            }
            else if(cursor.getX()>marginX*((width-1)/2)){
                cursor.setY(180);
                cursor.setX(340);
            }
            else if(cursor.getX()<marginX*((width-1)/2)){
                cursor.setY(180);
                cursor.setX(-15);
            }
            break;
            case RIGHT:
            if(cursor.getY()==180 && cursor.getX()==-15){
                cursor.setY(180);
                cursor.setX(340);
            }
            else if(marginX*(width-1)>cursor.getX()&&cursor.getY()!=180){
                cursor.setX(cursor.getX()+marginX);
            }
            break;
            case LEFT:
            if(cursor.getY()==180 && cursor.getX()==340){
                cursor.setY(180);
                cursor.setX(-15);
            }
            else if(positionX<cursor.getX()){
                cursor.setX(cursor.getX()-marginX);
            }
            break;
            case X:  //戻る
            deleteText1by1();
            break;
            case C:
            app.add(View.GAME);
            Window window = titleScene2Pane.getScene().getWindow();
            stage = (Stage) window;
            app.activate(View.GAME,stage);
            break;
            default: 
            break;
        }
    }

    private void setText1by1(){
        String previousText = nameTextLabel.getText();
        if(previousText.length()<8){
            if(cursor.getY()==0.0){
                nameTextLabel.setText(previousText+textLocationMap1.get(cursor.getX()));
            }
            else if(cursor.getY()==marginY){
                nameTextLabel.setText(previousText+textLocationMap2.get(cursor.getX()));
            }
        }
    }

    private void deleteText1by1(){
        String previousText = nameTextLabel.getText();
        if(cursor.getY()==0.0){
            if(previousText != ""){
                nameTextLabel.setText(previousText.substring(0, previousText.length()-1));
            }
        }
        else if(cursor.getY()==marginY){
            if(previousText != ""){
                nameTextLabel.setText(previousText.substring(0, previousText.length()-1));
            }
        }
    }
}
