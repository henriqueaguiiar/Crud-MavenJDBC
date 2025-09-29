package dao.impl;

import dao.DepartamentDao;
import exceptions.jdbc.DbException;
import model.Departament;
import model.Employee;
import util.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartamentDaoImplJDBC implements DepartamentDao {
    private Connection conn = DB.getConnection();

    @Override
    public Departament findById(Integer id) {
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT id, nome FROM public.departament WHERE id=?;";
        try {
            pr = conn.prepareStatement(sql);
            pr.setInt(1, id);
            rs = pr.executeQuery();
            if(rs.next()){
                Departament dep = instantiateDepartament(rs);
                return dep;
            }
            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void create(Departament departament) {
        String sql = "INSERT INTO public.departament (nome) VALUES(?);";
        try(PreparedStatement pr = conn.prepareStatement(sql)){
            pr.setString(1, departament.getNameDepartament());
            pr.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

    private Departament instantiateDepartament(ResultSet rs) throws SQLException {
        Departament dep = new  Departament();
        dep.setId(rs.getInt("departament_id"));
        dep.setNameDepartament(rs.getString("departamento_name"));
        return dep;
    }

}
