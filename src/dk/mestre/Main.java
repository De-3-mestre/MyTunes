package dk.mestre;

import dk.mestre.DAL.Database;
import dk.mestre.models.Song;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("gui/MainView.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        /*Song song = new Song("aiwfc","C:\\Users\\skupi\\Desktop\\song.mp3","dak","mester dak");
        Database db = new Database();
        db.insertSong(song);*/
    }
}
