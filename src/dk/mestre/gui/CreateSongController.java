package dk.mestre.gui;

import dk.mestre.BLL.SongManager;
import dk.mestre.models.Song;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class CreateSongController implements Initializable {

    @FXML
    private TextField newSongTitle;
    @FXML
    private TextField newSongArtist;
    @FXML
    private ComboBox<String> newSongCategory;
    @FXML
    private TextField newSongFile;
    @FXML
    private Button newSongCancel;
    @FXML
    private Button newSongSave;

    private File selectedFile = null;

    private SongManager sm;

    private String[] categories = {"Rock", "Pop", "Punk", "Techno", "Indie", "Metal"};

    public CreateSongController(){
        this.sm = new SongManager();
    }

    public void handleNewSongTitle(ActionEvent actionEvent) {
    }

    public void handleNewSongArtist(ActionEvent actionEvent) {
    }

    public void handleNewSongCategory(ActionEvent actionEvent) {
    }

    public void handleNewSongMoreCategory(ActionEvent actionEvent) {
    }

    public void handleNewSongFile(ActionEvent actionEvent) {
    }

    public void handleNewSongChooseFile(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Select the song...");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Audio Files", "*.waw", "*.mp3", "*.aac")
        );
        selectedFile = fc.showOpenDialog(newSongFile.getScene().getWindow());

        if (selectedFile != null) {
            newSongFile.setText(selectedFile.getAbsolutePath());
        }
    }

    public void handleNewSongSave(ActionEvent actionEvent) {
        String title = newSongTitle.getText();
        String artist = newSongArtist.getText();
        String category = newSongCategory.getSelectionModel().getSelectedItem();
        String path = (selectedFile != null) ? selectedFile.getAbsolutePath() : null;

        if (title != null && artist != null && category != null && path != null) {
            sm.addSong(new Song(-1, title, path, category, artist));
        }

        Stage stage = (Stage) newSongSave.getScene().getWindow();
        stage.close();
    }

    public void handleNewSongCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) newSongCancel.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> boxItems = new ArrayList<>(Arrays.asList(categories));
        newSongCategory.getItems().addAll(boxItems);
    }
}
