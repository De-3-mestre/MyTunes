package dk.mestre.models;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private String name;
    private List<Song> songs;

    public Playlist(String name){
        this(name, null);
    }

    public Playlist(String name, List<Song> songs){
        this.name = name;
        this.songs = (songs == null) ? new ArrayList<>() : songs;
    }

    public void addSong(Song song){
        this.songs.add(song);
    }

    public void addSongs(List<Song> songs){
        this.songs.addAll(songs);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongs() {
        return songs;
    }
}
