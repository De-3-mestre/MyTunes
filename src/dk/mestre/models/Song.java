package dk.mestre.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Song {

    private int id;
    private StringProperty title;
    private StringProperty durationString;
    private StringProperty path;
    private StringProperty category;
    private StringProperty artist;

    private double timeInMilis = 0;

    private Media media;

    /**
     * Creates a new instance of `Song` based on the file given
     *
     * @param title The song title
     * @param path The absolute path to the song
     * @see File
     **/
    public Song(int id, String title, String path, String category, String artist) {
        this.id = id;
        this.title = new SimpleStringProperty(title);
        this.path = new SimpleStringProperty(path);
        this.category = new SimpleStringProperty(category);
        this.artist = new SimpleStringProperty(artist);
        this.media = new Media(new File(path).toURI().toString());
        this.durationString = new SimpleStringProperty("");

        MediaPlayer mdp = new MediaPlayer(media);

        mdp.setOnReady(() -> {
            timeInMilis = getMedia().getDuration().toMillis();
            long minutes = (long) ((timeInMilis / 1000) / 60);
            long seconds = (long) ((timeInMilis / 1000) % 60);
            if(seconds < 10)
                setDurationString(minutes + ":0" + seconds);
            else
                setDurationString(minutes + ":" + seconds);
        });
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getDurationString() {
        return durationString.get();
    }

    public StringProperty durationStringProperty() {
        return durationString;
    }

    public void setDurationString(String durationString) {
        this.durationString.set(durationString);
    }

    public String getPath() {
        return path.get();
    }

    public StringProperty pathProperty() {
        return path;
    }

    public void setPath(String path) {
        this.path.set(path);
    }

    public String getCategory() {
        return category.get();
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public String getArtist() {
        return artist.get();
    }

    public StringProperty artistProperty() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist.set(artist);
    }

    public double getTimeInMilis(){
        return this.timeInMilis;
    }

    public int getId() {
        return this.id;
    }
}
