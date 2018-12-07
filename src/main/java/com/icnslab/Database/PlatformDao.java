package com.icnslab.Database;

import com.icnslab.Message.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by alicek106 on 2017-07-17.
 */
@Repository
public class PlatformDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Get hello list, using BeanPropertyRowMapper
     *
     * @return
     */
    public List<User> getUserList() {
        String query = "SELECT * FROM user";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper(User.class));
    }

    public List<User> getUser(String id, String password) {
        String query = String.format("SELECT * FROM user where id = '%s' and password = '%s'", id, password);
        List<User> user = jdbcTemplate.query(query, new BeanPropertyRowMapper(User.class));
        return user;
    }
}
