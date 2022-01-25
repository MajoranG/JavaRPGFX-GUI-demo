package gui2.game;

import java.net.URL;
import java.util.ResourceBundle;

import gui2.GUILaunch;
import gui2.model.View;
import gui2.util.GameViewEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.fxml.Initializable;

public class GameController extends GameViewEvent implements Initializable{

    GUILaunch app = GUILaunch.getSingleton();
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label TestField;

    @FXML
    private Label nameTestLabel;

    private Stage stage;

    @FXML
    public void initialize(URL url,ResourceBundle resourceBundle) {
        assert TestField != null : "fx:id=\"TestField\" was not injected: check your FXML file 'game.fxml'.";
        
    }

    @FXML
    @Override public void keyPressed(KeyEvent e){

        switch(e.getCode()){
            case Z:  //決定ボタン
            System.out.println("z");
            TestField.setText("けってい");
            break;
            case UP:
            TestField.setText("UP");
            break;
            case DOWN:
            TestField.setText("DOWN");
            break;
            case RIGHT:
            TestField.setText("RIGHT");
            break;
            case LEFT:
            TestField.setText("LEFT");
            break;
            case X:  //戻る
            Window window = TestField.getScene().getWindow();
            stage = (Stage) window;
            app.activate(View.TITLE,stage);
            break;
            case C:
            break;
            default: 
            break;
        }
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void displayName(String name){
        nameTestLabel.setText(nameTestLabel.getText()+name);
    }
    public GameController(){}
}
