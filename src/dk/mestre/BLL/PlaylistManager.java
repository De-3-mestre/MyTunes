package dk.mestre.BLL;

import dk.mestre.DAL.Database;
import dk.mestre.models.Playlist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

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

}
