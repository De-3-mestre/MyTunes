package dk.mestre.BLL;

import dk.mestre.models.Song;
import javafx.scene.media.MediaPlayer;

public class MusicPlayer {

    private static MediaPlayer mediaPlayer = null;

    private double volume = 0.5;

    private boolean isPaused = false;

    public void playSong(Song song) {
        stopPlaying();
        mediaPlayer = new MediaPlayer(song.getMedia());
        mediaPlayer.setVolume(volume);
        mediaPlayer.play();
        isPaused = false;
    }

    public void unpauseSong(){
        if (mediaPlayer == null) return;
        mediaPlayer.play();
        isPaused = false;
    }

    public void pauseSong(){
        if (mediaPlayer == null) return;
        mediaPlayer.pause();
        isPaused = true;
    }

    public void stopPlaying() {
        if (mediaPlayer == null) return;
        mediaPlayer.stop();
    }

    public void setVolume(double volume) {
        this.volume = volume;
        mediaPlayer.setVolume(this.volume);
    }

    public double getPlayTime() {
        return mediaPlayer.getCurrentTime().toSeconds();
    }

    public boolean getIsPaused(){
        return this.isPaused;
    }

}
