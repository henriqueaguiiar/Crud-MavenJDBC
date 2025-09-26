package dao;

import model.Departament;

import java.util.List;

/**
 * @author Henrique Aguiar Pacheco
 * interface DAO para implementação de operações CRUD
 */
public interface DepartamentDao {

    void create(Departament departament);

    void update(Departament departament);

    void deleteById(Integer id);

    Departament findById(Integer id);

    List<Departament> findAll();

}
