package app.Dao;

import app.Entities.Avatar;
import app.Exceptions.AvatarNotFoundException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class GalleryJDBC {

    private final Connection connection;

    public GalleryJDBC(String url, String user, String password) throws SQLException {

        connection = DriverManager.getConnection(url, user, password);

    }

    public GalleryJDBC() throws IOException {
        Properties property = new Properties();

        try (FileInputStream fis = new FileInputStream("C:\\Users\\user\\Desktop\\jdbctest\\db.properties")){
            property.load(fis);

            String host = property.getProperty("db.host");
            String login = property.getProperty("db.login");
            String password = property.getProperty("db.password");

            connection = DriverManager.getConnection(host, login, password);

        } catch (IOException | SQLException e) {
            System.err.println("DB configurations error");
            throw new IOException();
        }
    }

    public ArrayList<String> getAvatarNames() throws SQLException {
        String sql = "SELECT name FROM avatars " +
                "join(SELECT distinct avatar_id from \"generalGallery\") gG " +
                "on avatars.id = gG.avatar_id";
        PreparedStatement ps = this.connection.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        ArrayList<String> result = new ArrayList<>();

        while(rs.next()) {
            result.add(rs.getString(1));
        }

        return result;
    }

    public Avatar getAvatarByIndex(int index) throws SQLException, AvatarNotFoundException {
        String sql = "SELECT * FROM avatars " +
                "join(SELECT distinct avatar_id from \"generalGallery\") gG " +
                "on avatars.id = gG.avatar_id " +
                "LIMIT 1 OFFSET " + (index - 1);
        PreparedStatement ps = this.connection.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        Avatar result = new Avatar();
        if(rs.next()) {
            result.setId(rs.getInt("id"));
            result.setName(rs.getString("name"));
            result.setHair_id(rs.getInt("hair"));
            result.setEye_id(rs.getInt("eye"));
            result.setMouth_id(rs.getInt("mouth"));
        } else {
            throw new AvatarNotFoundException("Не удалось найти выбранный элемент");
        }

        return result;
    }

    public boolean contains(int avatar_id) throws SQLException {
        PreparedStatement ps = this.connection.prepareStatement(
                "SELECT EXISTS(SELECT 1 FROM \"generalGallery\" WHERE avatar_id = ?);"
        );
        ps.setInt(1, avatar_id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            return rs.getBoolean(1);
        }
        return false;
    }

    public void addAvatar(int avatar_id) throws SQLException {
        PreparedStatement ps = this.connection.prepareStatement(
                "insert into \"generalGallery\"(id, avatar_id) values(default, ?)"
        );
        ps.setInt(1, avatar_id);
        ps.executeUpdate();
    }

    public void deleteAvatar(int avatar_id) throws SQLException {
        PreparedStatement ps = this.connection.prepareStatement(
                "delete from \"generalGallery\" where avatar_id = ?"
        );
        ps.setInt(1, avatar_id);
        ps.executeUpdate();
    }
}
