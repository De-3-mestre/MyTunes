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
            List<Playlist> playlists = db.getAllPlaylists();
            for(Playlist p : playlists){
                p.setSongs(FXCollections.observableArrayList(getSongsInList(p.getId())));
            }
            return FXCollections.observableArrayList(playlists);
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

    public void addSong(Playlist selectedPlaylist, Song selectedSong) {
        selectedPlaylist.addSong(selectedSong);

        try {
            System.out.println("Playlist: " + selectedPlaylist.getName() + " " + selectedPlaylist.getId() +
                               " | Song: " + selectedSong.getTitle() + " " + selectedSong.getId());
            db.insertSongToPlaylist(selectedPlaylist, selectedSong);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred when trying to add song to playlist...");
        }
    }

    public void removeSong(Playlist selectedPlaylist, Song selectedSong) {
        selectedPlaylist.removeSong(selectedSong);

        try {
            db.removeSongFromPlaylist(selectedPlaylist, selectedSong);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
