package dao;

import model.Departament;
import model.Employee;

public interface DepartamentDao {

    Departament findById(Integer id);

    void create(Departament departament);
}
