package jdbc;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleJDBCRepository {
    private static final String createUserSQL = "insert into myusers(firstname, lastname, age) values(?,?,?);";
    private static final String updateUserSQL = "update myusers set firstname=?, lastname=?, age=? where id=?;";
    private static final String deleteUser = "delete from myusers where id=?";
    private static final String findUserByIdSQL = "select * from myusers where id=?";
    private static final String findUserByNameSQL = "select * from myusers where firstname=?";
    private static final String findAllUserSQL = "select * from myusers";

    public Long createUser(User user) {
        Long id = null;
        try (Connection connection = CustomDataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(createUserSQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setInt(3, user.getAge());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public User findUserById(Long userId) {
        User user = null;
        try (Connection connection = CustomDataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(findUserByIdSQL)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User findUserByName(String userName) {
        User user = null;
        try (Connection connection = CustomDataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(findUserByNameSQL)) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> findAllUser() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = CustomDataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(findAllUserSQL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = null;
                user.setId(resultSet.getLong(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getInt(4));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public User updateUser(User user) {
        try (Connection connection = CustomDataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(updateUserSQL)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setInt(3, user.getAge());
            statement.setLong(4, user.getId());
            if (statement.executeUpdate() != 0) {
                return findUserById(user.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void deleteUser(Long userId) {
        try (Connection connection = CustomDataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteUser)) {
            statement.setLong(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
