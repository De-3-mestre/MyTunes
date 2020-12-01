package dk.mestre.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Song {

    private String title;
    private StringProperty durationString;
    private Media media;

    /**
     * Creates a new instance of `Song` based on the file given
     *
     * @param file the file that contains the song information
     * @see File
     **/
    public Song(File file) {
        this.title = file.getName();
        this.media = new Media(file.toURI().toString());
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
