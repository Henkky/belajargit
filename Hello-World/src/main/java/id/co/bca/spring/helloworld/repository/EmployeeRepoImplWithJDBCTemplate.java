package id.co.bca.spring.helloworld.repository;

import id.co.bca.spring.helloworld.model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepoImplWithJDBCTemplate implements IEmployeeRepository{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void create(EmployeeModel employee) {
        jdbcTemplate.execute(MySQLDataSource.STM_CREATE, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, employee.getFirstname());
                ps.setString(2, employee.getLastname());
                ps.setString(3, employee.getEmail());
                return ps.execute();
            }
        });
    }

    @Override
    public List<EmployeeModel> retrieveAll() {
        return jdbcTemplate.query(MySQLDataSource.STM_RETRIEVE_ALL, new ResultSetExtractor<List<EmployeeModel>>() {
            @Override
            public List<EmployeeModel> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<EmployeeModel> employees = new ArrayList<>();
                while(rs.next()){
                    EmployeeModel employee = new EmployeeModel();
                    employee.setId(rs.getInt("id"));
                    employee.setFirstname(rs.getString("first_name"));
                    employee.setLastname(rs.getString("last_name"));
                    employee.setEmail(rs.getString("email"));
                    employees.add(employee);
                }
                return employees;
            }
        });
    }

    @Override
    public EmployeeModel retrieveUnique(EmployeeModel employee) {
        return null;
    }

    @Override
    public void update(EmployeeModel employee) {

    }

    @Override
    public void deleteUnique(EmployeeModel employee) {

    }
}
