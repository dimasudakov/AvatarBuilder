package app.Dao;
import app.Entities.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO {

    /*
     * add person in database
     */
    public void addUser(User user) throws SQLException;
    /*
     * get one person with similar name.
     */
    public User getUser(String name) throws SQLException;
    /*
     * get all persons
     */
    public ArrayList<User> getAllUsers() throws SQLException;
}
