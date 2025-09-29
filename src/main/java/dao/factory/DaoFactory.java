package dao.factory;

import dao.DepartamentDao;
import dao.EmployeeDao;
import dao.impl.DepartamentDaoImplJDBC;
import dao.impl.EmployeeDaoImplJDBC;
import util.DB;


/**
 * @author Henrique Aguiar Pacheco
 * classe responsavel por instanciar as implementações DAO das entidades. Usando modelo Factory
 */

public class DaoFactory {
    public static EmployeeDao criarEmployeeDaoImpl(){
        return new EmployeeDaoImplJDBC(DB.getConnection());
    }

    public static DepartamentDao criarDepartamentDaoImpl(){
        return  new DepartamentDaoImplJDBC(DB.getConnection());
    }

}
