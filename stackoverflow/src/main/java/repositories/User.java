package repositories;

import config.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class User {
    public List<models.User> findAll() {
        // language=SQL
        String findAll = "select * from users_table";
        try (java.sql.Connection connection = Connection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(findAll);
            ResultSet resultSet = statement.executeQuery();

            List<models.User> list = new LinkedList<>();

            while (resultSet.next()) {
                models.User user = models.User.builder()
                        .id(resultSet.getLong("id"))
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("password"))
                        .role(resultSet.getString("role"))
                        .info(resultSet.getString("info"))
                        .build();

                list.add(user);
            }

            return list;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void save(models.User user) {
        // language=SQL
        String save = "insert into users_table (login, password) values (?,?)";
        try (java.sql.Connection connection = Connection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(save);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());

            statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void update(models.User user) {
        // language=SQL
        String update = "update users_table set login = ?, info = ? where id = ?";
        try (java.sql.Connection connection = Connection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getInfo());
            statement.setLong(3, user.getId());

            statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
