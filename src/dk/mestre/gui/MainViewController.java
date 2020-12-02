package dk.mestre.gui;

import dk.mestre.BLL.SongManager;
import dk.mestre.models.Playlist;
import dk.mestre.models.Song;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    private SongManager songManager;

    private ObservableList<Song> songs;

    @FXML private TableView<Song> allSongsTable;
    @FXML private TableColumn<Song, String> songName;
    @FXML private TableColumn<Song, String> songArtist;
    @FXML private TableColumn<Song, String> songCategory;
    @FXML private TableColumn<Song, String> songTime;

    public MainViewController(){
        songManager = new SongManager();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        songs = songManager.getSongs();
        if (songs == null)
            throw new RuntimeException("Error retrieving songs from database");
        
        songName.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        songArtist.setCellValueFactory(cellData -> cellData.getValue().artistProperty());
        songCategory.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        songTime.setCellValueFactory(cellData -> cellData.getValue().durationStringProperty());


        allSongsTable.setItems(songs);
    }

    @FXML
    private void handleNewPlaylist() {
    }

    @FXML
    private void handleEditPlaylist() {
    }

    @FXML
    private void handleDeletePlaylist() {
    }

    @FXML
    private void handleMoveSongDown() {
    }

    @FXML
    private void handleMoveSongUp() {
    }

    @FXML
    private void handleDeleteSongPlaylist() {
    }

    @FXML
    private void handleNewSong() {
    }

    @FXML
    private void handleEditSong() {
    }

    @FXML
    private void handleDeleteSong() {
    }

    @FXML
    private void handlePreviousSong() {
    }

    @FXML
    private void handlePlaySong() {
    }

    @FXML
    private void handleNextSong() {
    }

    @FXML
    private void handleFilterSongs() {
    }

    @FXML
    private void handleMoveSongToPlaylist() {
    }

}
