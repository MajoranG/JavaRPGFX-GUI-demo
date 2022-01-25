package gui2.model;
//fxml location
public enum View {
    TITLE("/title/TitleMenu.fxml"),
    TITLESCENE2("/title/titleScene2.fxml"),
    GAME("/game/game.fxml");

    public final String fxmlLocation;

    View(String location){
        this.fxmlLocation = "/gui2" + location;
    }
}



