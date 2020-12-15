package dk.mestre.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dk.mestre.models.Playlist;
import dk.mestre.models.Song;
import dk.mestre.models.SongPlaylistPair;

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
                    res.getInt("id"),
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

    public List<Playlist> getAllPlaylists() throws SQLException {

        List<Playlist> playlists = new ArrayList<>();

        Connection connection = getConnection();

        String query = "SELECT * FROM Playlist";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(query);

        while (res.next()) {
            playlists.add(new Playlist(
                    res.getInt("id"),
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

    public List<SongPlaylistPair> getPairs() throws SQLException {
        Connection connection = getConnection();

        List<SongPlaylistPair> pairs = new ArrayList<>();

        String query = "SELECT * FROM SongsInList";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(query);

        while (res.next()) {
            pairs.add(
                    new SongPlaylistPair(
                            res.getInt("songId"),
                            res.getInt("playlistId")
                    )
            );
        }

        connection.close();
        stmt.close();
        res.close();

        return pairs;
    }

    public void insertSongToPlaylist(Playlist playlist, Song song) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO SongsInList (songId, playlistId) VALUES (?, ?)");
        pstmt.setInt(1, song.getId());
        pstmt.setInt(2, playlist.getId());

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

    public Song getSong(int songId) throws SQLException {

        Connection connection = getConnection();

        String query = "SELECT * FROM Song WHERE id=?";

        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, songId);
        ResultSet res = pstmt.executeQuery();

        Song song = null;

        while (res.next()) {
            song = new Song(
                    res.getInt("id"),
                    res.getString("songName"),
                    res.getString("songPath"),
                    res.getString("songCategory"),
                    res.getString("songArtist")
            );
        }

        connection.close();
        pstmt.close();
        res.close();

        return song;

    }

    public void removeSongFromPlaylist(Playlist selectedPlaylist, Song selectedSong) throws SQLException {
        Connection connection = getConnection();

        String query = "DELETE FROM SongsInList WHERE songId=? AND playlistId=?";

        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, selectedSong.getId());
        pstmt.setInt(2, selectedPlaylist.getId());

        pstmt.execute();

        connection.close();
        pstmt.close();
    }
}