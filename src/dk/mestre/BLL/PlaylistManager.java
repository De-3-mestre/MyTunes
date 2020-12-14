package dk.mestre.BLL;

import dk.mestre.DAL.Database;
import dk.mestre.models.Playlist;
import dk.mestre.models.Song;
import dk.mestre.models.SongPlaylistPair;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;
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

    public List<Song> getSongsInList(int playlistId){
        List<Song> songs = new ArrayList<>();
        try {
            List<SongPlaylistPair> pairs = db.getPairs();
            for(SongPlaylistPair p : pairs){
                if(p.getPlaylistId() == playlistId){
                    songs.add(db.getSong(p.getSongId()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }

}
