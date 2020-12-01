package dk.mestre.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dk.mestre.models.Song;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database extends Configuration {

    public Database() {
        super();
    }

    public List<Song> getAllSongs() {
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setDatabaseName(configValues.get("DATABASE_NAME"));
        dataSource.setUser(configValues.get("DATABASE_USER"));
        dataSource.setPassword(configValues.get("DATABASE_PASS"));
        dataSource.setServerName(configValues.get("DATABASE_SERVER"));

        List<Song> songs = new ArrayList<>();

        try {

            Connection connection = dataSource.getConnection();

            String query = "SELECT * FROM Song";

            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {
                songs.add(new Song(
                        res.getString("songName"),
                        res.getString("songPath")
                ));
            }

            connection.close();
            stmt.close();
            res.close();

        } catch (SQLServerException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return songs;
    }

}
