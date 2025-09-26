package application;

import dao.EmployeeDao;
import dao.factory.DaoFactory;
import model.Employee;
import util.DB;

import java.sql.Connection;

public class Program {
    public static void main(String[] args) {

        Connection conn = DB.getConnection();



        EmployeeDao emp = DaoFactory.criarEmployeeDaoImpl();

        Employee emp1 = emp.findById(1);

        System.out.println(emp1);

        DB.closeConnection();

    }
}
