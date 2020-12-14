package dk.mestre.gui;

import dk.mestre.BLL.MusicPlayer;
import dk.mestre.BLL.PlaylistManager;
import dk.mestre.BLL.SongManager;
import dk.mestre.models.Playlist;
import dk.mestre.models.Song;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableNumberValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    private MusicPlayer musicPlayer;

    private SongManager songManager;
    private PlaylistManager playlistManager;

    private ObservableList<Song> songs;
    private ObservableList<Playlist> playlists;

    @FXML
    private TableView<Song> allSongsTable;
    @FXML
    private TableColumn<Song, String> songName;
    @FXML
    private TableColumn<Song, String> songArtist;
    @FXML
    private TableColumn<Song, String> songCategory;
    @FXML
    private TableColumn<Song, String> songTime;

    @FXML
    private TableView<Playlist> playlistTable;
    @FXML
    private TableColumn<Playlist, String> playlistName;
    @FXML
    private TableColumn<Playlist, Number> playlistSongs;
    @FXML
    private TableColumn<Playlist, String> playlistTime;

    @FXML
    private TableView<Song> songTable;
    @FXML
    private TableColumn<Song, String> selectedPlaylistSong;

    @FXML
    private Label currentlyPlaying;

    @FXML
    private Button playSong;

    @FXML
    private Slider volumeSlider;

    private Song selectedSong = null;
    private Song playingSong = null;

    private Playlist selectedPlaylist = null;

    public MainViewController() {
        songManager = new SongManager();
        playlistManager = new PlaylistManager();
        musicPlayer = new MusicPlayer();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initAllSongsTable();
        initPlaylistTable();

        volumeSlider.setValue(50);
        volumeSlider.valueProperty().addListener((observableValue, oldVal, newVal) -> {
            double val = newVal.doubleValue() / 100;
            musicPlayer.setVolume(val);
        });

    }

    private void initPlaylistTable() {
        playlists = playlistManager.getPlaylists();
        if (playlists == null)
            throw new RuntimeException("Error retrieving playlists from database");

        playlistName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        playlistSongs.setCellValueFactory(cellData -> {
            ObservableNumberValue num = new SimpleIntegerProperty(cellData.getValue().getSongs().size());
            return num;
        });
        playlistTime.setCellValueFactory(cellData -> {

            double sum = 0;
            for (Song song : cellData.getValue().getSongs()) {
                sum += song.getTimeInMilis();
            }

            long minutes = (long) ((sum / 1000) / 60);
            long seconds = (long) ((sum / 1000) % 60);

            StringProperty ret;

            if (seconds < 10)
                ret = new SimpleStringProperty(minutes + ":0" + seconds);
            else
                ret = new SimpleStringProperty(minutes + ":" + seconds);

            return ret;
        });

        playlistTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
            selectedPlaylist = newVal;
            updateSelectedPlaylistSongs();
        });

        playlistTable.setItems(playlists);
    }

    private void initAllSongsTable() {
        songs = songManager.getSongs();
        if (songs == null)
            throw new RuntimeException("Error retrieving songs from database");

        songName.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        songArtist.setCellValueFactory(cellData -> cellData.getValue().artistProperty());
        songCategory.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        songTime.setCellValueFactory(cellData -> cellData.getValue().durationStringProperty());

        allSongsTable.setItems(songs);

        allSongsTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal != null)
                selectedSong = newVal;
            if (selectedSong != playingSong) {
                playSong.setText("⯈");
            } else {
                playSong.setText("⏸");
            }
        });
    }

    private void updateSelectedPlaylistSongs() {
        selectedPlaylistSong.setCellValueFactory(celldata -> celldata.getValue().titleProperty());
        songTable.setItems(selectedPlaylist.getSongs());

        songTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
            if(newVal != null)
                selectedSong = newVal;
            if (selectedSong != playingSong) {
                playSong.setText("⯈");
            } else {
                playSong.setText("⏸");
            }
        });
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
        if(selectedSong != null) {
            if (selectedSong != playingSong) {
                musicPlayer.playSong(selectedSong);
                currentlyPlaying.setText(selectedSong.getTitle() + " - " + selectedSong.getArtist());
                playingSong = selectedSong;
                playSong.setText("⏸");
            } else {
                if (musicPlayer.getIsPaused()) {
                    musicPlayer.unpauseSong();
                    playSong.setText("⏸");
                } else {
                    musicPlayer.pauseSong();
                    playSong.setText("⯈");
                }
            }
        }
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