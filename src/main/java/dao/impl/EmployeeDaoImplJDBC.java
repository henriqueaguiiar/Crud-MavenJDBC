package dao.impl;

import dao.EmployeeDao;
import exceptions.jdbc.DbExceprion;
import model.Departament;
import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDaoImplJDBC implements EmployeeDao {

    private Connection conn;

    public EmployeeDaoImplJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void create(Employee employee) {

    }

    @Override
    public void update(Employee employee) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Employee findById(Integer id) {
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT e.*, d.nome AS departamento_name FROM public.employee e INNER JOIN departament d ON e.departament_id = d.id WHERE e.id = ?;";
        try {
            pr = conn.prepareStatement(sql);
            pr.setInt(1, id);
            rs = pr.executeQuery();
            if(rs.next()){
                Departament dep = new Departament();
                dep.setId(rs.getInt("departament_id"));
                dep.setNameDepartament(rs.getString("departamento_name"));
                Employee emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setName(rs.getString("nome"));
                emp.setEmail(rs.getString("email"));
                emp.setDepartament(dep);
                return emp;
            }
            return null;

        } catch (SQLException e) {
            throw new DbExceprion(e.getMessage());
        }
    }

    @Override
    public List<Employee> findAll() {
        return List.of();
    }
}
