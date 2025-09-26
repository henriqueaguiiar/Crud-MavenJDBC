package dao.factory;

import dao.DepartamentDao;
import dao.EmployeeDao;
import dao.impl.DepartamentDaoJDBC;
import dao.impl.EmployeeDaoImplJDBC;
import util.DB;

import java.sql.Connection;


/**
 * @author Henrique Aguiar Pacheco
 * classe responsavel por instanciar as implementações DAO das entidades. Usando modelo Factory
 */

public class DaoFactory {
    public static EmployeeDao criarEmployeeDaoImpl(){
        return new EmployeeDaoImplJDBC(DB.getConnection());
    }

    public static DepartamentDao criarDepartamentDaoImpl(){
        return new DepartamentDaoJDBC();
    }

}
