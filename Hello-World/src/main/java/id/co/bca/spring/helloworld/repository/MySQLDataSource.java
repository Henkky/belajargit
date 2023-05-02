package id.co.bca.spring.helloworld.repository;

import id.co.bca.spring.helloworld.model.EmployeeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySQLDataSource {
    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLDataSource.class.getPackageName());

    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/employee_directory";
    public static final String JDBC_USERNAME = "root";
    public static final String JDBC_PASSWORD = "";

    public static final String STM_CREATE = "INSERT INTO employee (first_name, last_name, email) VALUES (?,?,?)";
    public static final String STM_RETRIEVE_ALL = "SELECT * FROM employee";
    public static final String STM_RETRIEVE_BY_ID = "SELECT * FROM employee WHERE id = ?";
    public static final String STM_UPDATE= "UPDATE employee SET fist_name = ?, last_name = ?, email = ? WHERE id = >";
    public static final String STM_DELETE = "DELETE FROM employee WHERE id = ?";

    public MySQLDataSource(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            LOGGER.info("connection successful");
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.info("connection unsuccessful");
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.info("close connection unsuccessful");
            }
        }
    }

    public void insertEmployee(EmployeeModel employee){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet set = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            ps = connection.prepareStatement(STM_CREATE, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, employee.getFirstname());
            ps.setString(2, employee.getLastname());
            ps.setString(3, employee.getEmail());
            ps.execute();
            set = ps.getGeneratedKeys();
            if(set.next()){
                int id = set.getInt(1);
                LOGGER.info("Successful insert employee data with id " + String.valueOf(id));
            }
        } catch (SQLException e) {
            LOGGER.info("Unsuccessful insert employee data");
            e.printStackTrace();
        } finally {
            try {
                if (set != null){
                    set.close();
                }
            } catch (Exception e){};
            try {
                if (ps != null){
                    ps.close();
                }
            } catch (Exception e){};
            try {
                if (connection != null){
                    connection.close();
                }
            } catch (Exception e){};
        }
    }

    public void updateEmployee(EmployeeModel employee){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            ps = connection.prepareStatement(STM_UPDATE);
            ps.setString(1, employee.getFirstname());
            ps.setString(2, employee.getLastname());
            ps.setString(3, employee.getEmail());
            ps.setInt(4, employee.getId());
            ps.execute();
            LOGGER.info("Successful update employee data");
        } catch (SQLException e){
            LOGGER.info("Unsuccessful update employee data");
            e.printStackTrace();
        } finally {
            try {
                if (ps != null){
                    ps.close();
                }
            } catch (Exception e){};
            try {
                if (connection != null){
                    connection.close();
                }
            } catch (Exception e){};
        }
    }

    public void deleteEmployee(EmployeeModel employee){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            ps = connection.prepareStatement(STM_DELETE);
            ps.setInt(1, employee.getId());
            ps.execute();
            LOGGER.info("Successful delete employee data");
        } catch (SQLException e){
            LOGGER.info("Unsuccessful delete employee data");
            e.printStackTrace();
        } finally {
            try {
                if (ps != null){
                    ps.close();
                }
            } catch (Exception e){};
            try {
                if (connection != null){
                    connection.close();
                }
            } catch (Exception e){};
        }
    }

    public List<EmployeeModel> getAllEmployee(){
        List<EmployeeModel> employees = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            statement = connection.createStatement();
            /* set itu kayak cursor semisal return banyak data jadi kudu dicek 1 per 1 dengan set.next() */
            set = statement.executeQuery(STM_RETRIEVE_ALL);
            while(set.next()){
                EmployeeModel employee = new EmployeeModel();
                employee.setId(set.getInt("id"));
                employee.setFirstname(set.getString("first_name"));
                employee.setLastname(set.getString("last_name"));
                employee.setEmail(set.getString("email"));
                employees.add(employee);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if (set != null){
                    set.close();
                }
            } catch (Exception e){};
            try {
                if (statement != null){
                    statement.close();
                }
            } catch (Exception e){};
            try {
                if (connection != null){
                    connection.close();
                }
            } catch (Exception e){};
        }
        return employees;
    }

    public EmployeeModel getEmployeeWithId(EmployeeModel empinp){
        EmployeeModel employee = new EmployeeModel();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet set = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            ps = connection.prepareStatement(STM_RETRIEVE_BY_ID);
            ps.setInt(1, empinp.getId());
            /*metode 1 execute dlu baru getResultSet*/
            ps.execute();
            set = ps.getResultSet();
            /*metode 2 langsung execute tapi pakai executeQuery*/
            /*set = ps.executeQuery();*/
            if (set.next()){
                employee.setId(set.getInt("id"));
                employee.setFirstname(set.getString("first_name"));
                employee.setLastname(set.getString("last_name"));
                employee.setEmail(set.getString("email"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if (set != null){
                    set.close();
                }
            } catch (Exception e){};
            try {
                if (ps != null){
                    ps.close();
                }
            } catch (Exception e){};
            try {
                if (connection != null){
                    connection.close();
                }
            } catch (Exception e){};
        }
        return employee;
    }

}
