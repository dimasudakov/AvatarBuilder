package app.Dao;

import app.Exceptions.AvatarNotFoundException;
import app.Entities.Avatar;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class AvatarJDBC {
    private final Connection connection;

    public AvatarJDBC(String url, String user, String password) throws SQLException {

        connection = DriverManager.getConnection(url, user, password);

    }

    public AvatarJDBC() throws IOException {
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

    public ArrayList<String> getAvatarNamesByClientID(int ID) throws SQLException {
        String sql = "SELECT name FROM avatars " +
                "where client_id=? order by id";
        PreparedStatement ps = this.connection.prepareStatement(sql);
        ps.setInt(1, ID);

        ResultSet rs = ps.executeQuery();
        ArrayList<String> result = new ArrayList<>();

        while(rs.next()) {
            result.add(rs.getString(1));
        }

        return result;
    }

    public void addAvatar(Avatar avatar) throws SQLException {
        String sql = "insert into avatars(name, hair, eye, mouth, client_id)"
                + "values (?,?,?,?,?)";

        PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, avatar.getName());
        ps.setInt(2, avatar.getHair_id());
        ps.setInt(3, avatar.getEye_id());
        ps.setInt(4, avatar.getMouth_id());
        ps.setInt(5, avatar.getClient_id());

        ps.executeUpdate();

        ResultSet generatedKeys =  ps.getGeneratedKeys();
        if(generatedKeys.next()) {
            avatar.setId(generatedKeys.getInt(1));
        }
    }

    public Avatar getAvatarByIndex(int index, int client_id) throws SQLException, AvatarNotFoundException {
        String sql = "SELECT * FROM avatars " +
                "WHERE id = (" +
                    "SELECT id " +
                    "FROM avatars " +
                    "WHERE client_id=?" +
                    "ORDER BY id " +
                    "LIMIT 1 OFFSET " + (index - 1) +
                ");";

        PreparedStatement ps = this.connection.prepareStatement(sql);
        ps.setInt(1, client_id);

        ResultSet rs = ps.executeQuery();
        Avatar result = new Avatar();
        if(rs.next()) {
            result.setId(rs.getInt("id"));
            result.setName(rs.getString("name"));
            result.setHair_id(rs.getInt("hair"));
            result.setEye_id(rs.getInt("eye"));
            result.setMouth_id(rs.getInt("mouth"));
        } else {
            throw new AvatarNotFoundException("???? ?????????????? ?????????? ?????????????????? ??????????????");
        }

        return result;
    }

    public void deleteAvatar(int id) throws SQLException, IOException {
        GalleryJDBC galleryJDBC = new GalleryJDBC();
        if(galleryJDBC.contains(id)) {
            galleryJDBC.deleteAvatar(id);
        }
        String sql = "DELETE FROM avatars " +
                    "WHERE id = ?";

        PreparedStatement ps = this.connection.prepareStatement(sql);
        ps.setInt(1, id);

        ps.executeUpdate();
    }

    public void updateAvatar(Avatar avatar) throws SQLException {
        PreparedStatement ps = this.connection.prepareStatement(
                "UPDATE avatars SET name = ?, hair = ?, eye = ?, mouth = ?" +
                        " WHERE id = ?");
        ps.setString(1, avatar.getName());
        ps.setInt(2, avatar.getHair_id());
        ps.setInt(3, avatar.getEye_id());
        ps.setInt(4, avatar.getMouth_id());
        ps.setInt(5, (int) avatar.getId());

        ps.executeUpdate();
    }

    public int getIDbyIndex(int index, int client_id) throws SQLException, AvatarNotFoundException {
        PreparedStatement ps = this.connection.prepareStatement(
                "Select id from avatars " +
                        "where client_id = ? " +
                        "order by id " +
                        "limit 1 offset " + (index - 1)
        );
        ps.setInt(1, client_id);
        ResultSet rs = ps.executeQuery();

        if(rs.next()) {
            return rs.getInt(1);
        } else {
            throw new AvatarNotFoundException("???? ?????????????? ?????????? ?????????????????? ??????????????");
        }
    }
}
