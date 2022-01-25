package gui2.title;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

import gui2.GUILaunch;
import gui2.model.View;
import gui2.util.GameViewEvent;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class TitleMenuController extends GameViewEvent implements Initializable{
    //カプセル化とは
    GUILaunch app = GUILaunch.getSingleton();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane titleView;

    @FXML
    private Label titleField;

    @FXML
    private Label startLabel;

    @FXML
    private Label exitLabel;

    @FXML
    private ImageView titlePointer;

    private double position;
    private Stage stage;
    private final double interval = 45.0;

    @FXML
    public void initialize(URL url,ResourceBundle resourceBundle) {
        this.position = titlePointer.getY();
        System.out.println(titlePointer + " "+titlePointer.getY()+" initialize()");
    }
    @FXML
    @Override public void keyPressed(KeyEvent e){

        switch(e.getCode()){
            case Z:  //決定
            if((position+interval) == titlePointer.getY()){
                Platform.exit();
            }
            else{
                app.add(View.TITLESCENE2);
                Window window = titleView.getScene().getWindow();
                stage = (Stage) window;
                app.activate(View.TITLESCENE2,stage);
            }
            break;
            case UP:
            if(titlePointer.getY()>position)
                titlePointer.setY(titlePointer.getY()-interval);
            break;
            case DOWN:
            if((position+interval)>titlePointer.getY())
                titlePointer.setY(titlePointer.getY()+interval);
            break;
            case X:  //戻る(タイトルでは区別しない)
            if((position+interval) == titlePointer.getY()){
                Platform.exit();
            }
            else{
                app.add(View.TITLESCENE2);
                Window window = titleView.getScene().getWindow();
                stage = (Stage) window;
                app.activate(View.TITLESCENE2,stage);
            }
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

    public TitleMenuController(){
        System.out.println(titlePointer+" controllerコンストラクタ");
    }
}
