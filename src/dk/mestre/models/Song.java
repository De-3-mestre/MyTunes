package dk.mestre.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

public class Song {

    private String title;
    private StringProperty durationString;
    private Media media;

    /**
     * Creates a new instance of `Song` based on the file given
     *
     * @param title The song title
     * @param path The absolute path to the song
     * @see File
     **/
    public Song(String title, String path) {
        this.title = title;
        this.media = new Media(path);
        this.durationString = new SimpleStringProperty("");

        MediaPlayer mdp = new MediaPlayer(media);

        mdp.setOnReady(() -> {
            double millis = getMedia().getDuration().toMillis();
            long minutes = (long) ((millis / 1000) / 60);
            long seconds = (long) ((millis / 1000) % 60);
            setDurationString(minutes + ":" + seconds);
        });
    }

    public String getTitle() {
        return title;
    }

    public StringProperty durationStringProperty() {
        return durationString;
    }

    public void setDurationString(String durationString) {
        this.durationString.set(durationString);
    }

    public Media getMedia() {
        return media;
    }
}
