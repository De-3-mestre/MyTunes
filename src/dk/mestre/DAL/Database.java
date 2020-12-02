package dk.mestre.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dk.mestre.models.Playlist;
import dk.mestre.models.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database extends Configuration {

    public Database() {
        super();
    }

    public List<Song> getAllSongs() throws SQLException {

        Connection connection = getConnection();

        List<Song> songs = new ArrayList<>();

        String query = "SELECT * FROM Song";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(query);

        while (res.next()) {
            songs.add(new Song(
                    res.getString("songName"),
                    res.getString("songPath"),
                    res.getString("songCategory"),
                    res.getString("songArtist")
            ));
        }

        connection.close();
        stmt.close();
        res.close();

        return songs;
    }

    public void insertSong(Song song) throws SQLException {

        Connection connection = getConnection();

        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Song (songName, songPath, songCategory, songArtist) VALUES (?, ?, ?, ?)");
        pstmt.setString(1, song.getTitle());
        pstmt.setString(2, song.getPath());
        pstmt.setString(3, song.getCategory());
        pstmt.setString(4, song.getArtist());

        pstmt.execute();

        connection.close();
        pstmt.close();
    }

    public List<Playlist> getAllPlaylists() throws SQLException{

        List<Playlist> playlists = new ArrayList<>();

        Connection connection = getConnection();

        String query = "SELECT * FROM Playlist";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(query);

        while(res.next()){
            playlists.add(new Playlist(
                    res.getString("playlistName")
            ));
        }

        connection.close();
        stmt.close();
        res.close();

        return playlists;

    }

    public void insertPlaylist(Playlist playlist) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Playlist (playlistName) VALUES (?)");
        pstmt.setString(1, playlist.getName());

        pstmt.execute();

        connection.close();
        pstmt.close();
    }

    private Connection getConnection() {
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setDatabaseName(configValues.get("DATABASE_NAME"));
        dataSource.setUser(configValues.get("DATABASE_USER"));
        dataSource.setPassword(configValues.get("DATABASE_PASS"));
        dataSource.setServerName(configValues.get("DATABASE_SERVER"));

        try {
            return dataSource.getConnection();
        } catch (SQLServerException e) {
            e.printStackTrace();
            return null;
        }
    }

}