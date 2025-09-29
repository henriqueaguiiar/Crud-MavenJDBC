package dao;


import model.Departament;
import model.Employee;

import java.util.List;

/**
 * @author Henrique Aguiar Pacheco
 * interface DAO para implementação de operações CRUD
 */
public interface EmployeeDao {

    void create(Employee employee);

    void update(Employee employee);

    void deleteById(Integer id);

    Employee findById(Integer id);

    List<Employee> findAll();

    List<Employee> findByDepartament(Departament departament);
}
