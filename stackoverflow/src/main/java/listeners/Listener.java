package listeners;

import config.Connection;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebListener
public class Listener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // language=SQL
        String createUserTable = "create table if not exists users_table (" +
                "id bigserial primary key ," +
                "login varchar(20)," +
                "role varchar(20) default 'user'," +
                "password varchar(100))";

        // language=SQL
        String createPostTable = "create table if not exists posts_table (" +
                "id bigserial primary key," +
                "title varchar(1000)," +
                "text varchar(2000)," +
                "imgName varchar(1000)," +
                "img bytea," +
                "user_id bigint references users_table(id))";

        // language=SQL
        String createCommentTable = "create table if not exists comments_table (" +
                "id bigserial primary key," +
                "text varchar(1000)," +
                "user_id bigint references users_table(id)," +
                "post_id bigint references posts_table(id))";

        // language=SQL
        String alterUserInfo = "alter table users_table add column if not exists info varchar(1000) default ''";

        try (java.sql.Connection connection = Connection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(createUserTable);
            statement.execute();

            statement = connection.prepareStatement(createPostTable);
            statement.execute();

            statement = connection.prepareStatement(createCommentTable);
            statement.execute();

            statement = connection.prepareStatement(alterUserInfo);
            statement.execute();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
