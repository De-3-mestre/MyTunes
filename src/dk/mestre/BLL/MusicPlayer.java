package dk.mestre.BLL;

import dk.mestre.models.Song;
import javafx.scene.media.MediaPlayer;

public class MusicPlayer {

    private static MediaPlayer mediaPlayer = null;

    public void playSong(Song song){
        stopPlaying();
        mediaPlayer = new MediaPlayer(song.getMedia());
        mediaPlayer.play();
    }

    public void stopPlaying(){
        if(mediaPlayer == null) return;
        mediaPlayer.stop();
    }

    public void setVolume(){}

    public double getPlayTime(){
        return mediaPlayer.getCurrentTime().toSeconds();
    }

}
