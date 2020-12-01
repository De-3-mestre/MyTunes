package dk.mestre;

import dk.mestre.DAL.Database;
import dk.mestre.models.Song;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    public static void main(String[] args) {
        Database db = new Database();
        List<Song> songs = db.getAllSongs();
        for(Song s : songs){
            System.out.println(s.getTitle());
        }
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("gui/MainView.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}
