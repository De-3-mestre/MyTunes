package dk.mestre.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableNumberValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private int id;
    private StringProperty name;
    private ObservableList<Song> songs;

    public Playlist(int id, String name){
        this(id, name, null);
    }

    public Playlist(int id, String name, List<Song> songs){
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.songs = (songs == null) ? FXCollections.emptyObservableList() : FXCollections.observableArrayList(songs);
    }

    public void addSong(Song song){
        this.songs.add(song);
    }

    public void removeSong(Song song){
        if(this.songs.contains(song)){
            this.songs.remove(song);
        }
    }

    public void addSongs(List<Song> songs){
        this.songs.addAll(songs);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public ObservableList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ObservableList<Song> songs) {
        this.songs = songs;
    }

    public int getId() {
        return this.id;
    }
}
