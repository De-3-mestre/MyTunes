package dk.mestre.BLL;

import dk.mestre.DAL.Database;
import dk.mestre.models.Playlist;
import dk.mestre.models.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class PlaylistManager {

    private Database db;

    public PlaylistManager(){
        this.db = new Database();
    }

    public ObservableList<Playlist> getPlaylists(){
        try {
            return FXCollections.observableArrayList(db.getAllPlaylists());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Song> getSongsInList(int id){
        return null;
    }

}
