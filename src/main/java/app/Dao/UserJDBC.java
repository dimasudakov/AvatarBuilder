package app.Dao;

import app.Entities.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class UserJDBC implements UserDAO{

    private final Connection connection;

    public UserJDBC(String url, String user, String password) throws ClassNotFoundException, SQLException {

        this.connection = DriverManager.getConnection(url, user, password);

    }

    public UserJDBC() throws IOException {
        Properties property = new Properties();

        try (FileInputStream fis = new FileInputStream("C:\\Users\\user\\Desktop\\инфа\\jdbctest\\db.properties")){
            property.load(fis);

            String host = property.getProperty("db.host");
            String login = property.getProperty("db.login");
            String password = property.getProperty("db.password");

            connection = DriverManager.getConnection(host, login, password);

        } catch (IOException | SQLException e) {
            //TODO
            System.err.println("DB configurations error");
            throw new IOException();
        }
    }

    public void addUser(User user) throws SQLException {
        String sql = "insert into clients(name, password, email)"
                + "values (?,?,?)";

        PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


        ps.setString(1, user.getName());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getEmail());

        ps.executeUpdate();

        ResultSet generatedKeys =  ps.getGeneratedKeys();
        if(generatedKeys.next()) {
            user.setId(generatedKeys.getInt(1));
        }
    }

    public User getUser(String name) throws SQLException {
        PreparedStatement ps = this.connection.prepareStatement("select * from clients where name=?");
        ps.setString(1, name);
        ResultSet result = ps.executeQuery();

        User user = new User();
        if(result.next()) {
            user.setId(result.getInt("id"));
            user.setName(result.getString("name"));
            user.setPassword(result.getString("password"));
            user.setEmail(result.getString("email"));
        }
        return user;
    }

    public boolean UserNameExist(String name) throws SQLException {
        PreparedStatement ps = this.connection.prepareStatement("SELECT name FROM clients WHERE name=?;");
        ps.setString(1, name);
        ResultSet result = ps.executeQuery();

        return result.next();
    }

    public boolean EmailExist(String email) throws SQLException {
        PreparedStatement ps = this.connection.prepareStatement("SELECT email FROM clients WHERE email=?;");
        ps.setString(1, email);
        ResultSet result = ps.executeQuery();

        return result.next();
    }

    public ArrayList<User> getAllUsers() throws SQLException {
        ArrayList<User> array = new ArrayList<>();

        ResultSet result = this.connection.prepareStatement("select * from clients").executeQuery();
        while(result.next()) {
            User user = new User();
            user.setId(result.getInt("id"));
            user.setName(result.getString("name"));
            user.setPassword(result.getString("password"));
            user.setEmail(result.getString("email"));
            array.add(user);
        }
        result.close();
        return array;
    }
}
