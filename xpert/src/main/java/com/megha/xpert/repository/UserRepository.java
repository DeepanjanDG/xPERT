package com.megha.xpert.repository;

import com.megha.xpert.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String applicationType = resultSet.getString("applicationType");
        String name = resultSet.getString("name");
        String prefix = resultSet.getString("prefix");
        String firstName = resultSet.getString("firstName");
        String middleName = resultSet.getString("middleName");
        String lastName = resultSet.getString("lastName");
        // Extract other fields similarly
        return new User(id, applicationType, name, prefix, firstName, middleName, lastName,
                resultSet.getDate("birthDate").toLocalDate(),
                resultSet.getString("district"),
                resultSet.getString("city"),
                resultSet.getString("address"),
                resultSet.getString("pincode"),
                resultSet.getString("mobile"),
                resultSet.getString("email"),
                resultSet.getString("password")
        );
    }

    public void save(User user) {
        String sql = "INSERT INTO users (applicationType, name, prefix, firstName, middleName, lastName, birthDate, district, city, address, pincode, mobile, email, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getApplicationType(), user.getName(), user.getPrefix(), user.getFirstName(), user.getMiddleName(), user.getLastName(), user.getBirthDate(), user.getDistrict(), user.getCity(), user.getAddress(), user.getPincode(), user.getMobile(), user.getEmail(), user.getPassword());

//        public List<User> getAllUsers() {
//        List<User> users = new ArrayList<>();
//        try {
//            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
//            ResultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                User user = extractUserFromResultSet(resultSet);
//                users.add(user);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return users;
//    }
        // Other methods for CRUD operations can be added here
    }
    public boolean validateUser (User user) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ? AND password = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{user.getEmail(), user.getPassword()}, Integer.class);
        return count != null && count > 0;
    }

    public List<User> getAllUsers() {
        return List.of();
    }
}

