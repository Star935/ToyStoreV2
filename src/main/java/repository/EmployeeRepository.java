package repository;

import model.Employee;

import java.net.ConnectException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import config.DatabaseConnection;

import static java.sql.DriverManager.getConnection;

public class EmployeeRepository implements Repository<Employee>{
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    @Override
    public List<Employee> list() {
        List<Employee> employeeList = new ArrayList<>();
        try(Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    """
                        SELECT 
                        """
            )
        ) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void save(Employee employee) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                      INSERT INTO employees(first_name, last_name, id_rol, birthday_date) VALUES (?,?,?,?)
                                      """)
        ){
            preparedStatement.setString(1, employee.getFirst_name());
            preparedStatement.setString(2, employee.getLast_name());
            preparedStatement.setInt(3, employee.getRol().getId());
            preparedStatement.setDate(4, Date.valueOf(employee.getBirthday_date()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
