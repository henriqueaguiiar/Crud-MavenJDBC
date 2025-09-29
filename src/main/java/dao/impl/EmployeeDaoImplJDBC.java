package dao.impl;

import dao.EmployeeDao;
import exceptions.jdbc.DbException;
import model.Departament;
import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDaoImplJDBC implements EmployeeDao {

    private Connection conn;

    public EmployeeDaoImplJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void create(Employee employee) {
        String sql = "INSERT INTO public.employee (nome, email, departament_id) VALUES(?, ?, ?);";
        try(PreparedStatement pr = conn.prepareStatement(sql); ){
            pr.setString(1, employee.getName());
            pr.setString(2, employee.getEmail());
            pr.setInt(3, employee.getDepartament().getId());
            pr.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

    @Override
    public void update(Employee employee) {
        String sql = "UPDATE public.employee SET nome=?, email=?, departament_id=? WHERE id=?;";
        try(PreparedStatement pr = conn.prepareStatement(sql); ){
            pr.setString(1, employee.getName());
            pr.setString(2, employee.getEmail());
            pr.setInt(3, employee.getDepartament().getId());
            pr.setInt(4, employee.getId());
            pr.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM public.employee WHERE id=?;";
        try(PreparedStatement pr = conn.prepareStatement(sql)){
            pr.setInt(1, id);
            pr.executeUpdate();
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
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
                Departament dep = instantiateDepartament(rs);
                Employee emp = instantiateEmployee(rs, dep);
                return emp;
            }
            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }


    @Override
    public List<Employee> findAll() {
        PreparedStatement pr = null;
        ResultSet rs = null;

        String sql = "SELECT e.*, d.nome AS departamento_name FROM public.employee e INNER JOIN departament d ON e.departament_id = d.id  order by e.nome;";
        try {
            pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();

            List<Employee> list = new ArrayList<>();

            Map<Integer, Departament> map = new HashMap<>();

            while(rs.next()){

                Departament dep = map.get(rs.getInt("departament_id"));

                if(dep == null){
                    dep = instantiateDepartament(rs);
                    map.put(rs.getInt("departament_id"), dep);
                }
                Employee emp = instantiateEmployee(rs, dep);
                list.add(emp);
            }
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Employee> findByDepartament(Departament departament) {
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT e.*, d.nome AS departamento_name FROM public.employee e INNER JOIN departament d ON e.departament_id = d.id WHERE e.id = ? order by e.nome;";
        try {
            pr = conn.prepareStatement(sql);
            pr.setInt(1, departament.getId());
            rs = pr.executeQuery();

            List<Employee> list = new ArrayList<>();

            Map<Integer, Departament> map = new HashMap<>();

            while(rs.next()){

                Departament dep = map.get(rs.getInt("departament_id"));

                if(dep == null){
                    dep = instantiateDepartament(rs);
                    map.put(rs.getInt("departament_id"), dep);
                }
                Employee emp = instantiateEmployee(rs, dep);
                list.add(emp);
            }
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    private Employee instantiateEmployee(ResultSet rs, Departament dep) throws SQLException {
        Employee emp = new Employee();
        emp.setId(rs.getInt("id"));
        emp.setName(rs.getString("nome"));
        emp.setEmail(rs.getString("email"));
        emp.setDepartament(dep);
        return emp;
    }

    private Departament instantiateDepartament(ResultSet rs) throws SQLException {
        Departament dep = new  Departament();
        dep.setId(rs.getInt("departament_id"));
        dep.setNameDepartament(rs.getString("departamento_name"));
        return dep;
    }


}
