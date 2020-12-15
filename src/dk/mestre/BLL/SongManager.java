package dk.mestre.BLL;

import dk.mestre.DAL.Database;
import dk.mestre.models.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class SongManager {

    private Database db;

    public SongManager(){
        db = new Database();
    }

    public ObservableList<Song> getSongs(){
        try {
            return FXCollections.observableArrayList(db.getAllSongs());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addSong(Song song){
        try {
            db.insertSong(song);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}