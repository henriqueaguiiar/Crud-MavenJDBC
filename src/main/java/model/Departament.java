package model;

import java.io.Serializable;
import java.util.Objects;

public class Departament implements Serializable {
    private Integer id;
    private String nameDepartament;

    private static final long serialVersionUID = 1L;


    public Departament() {
    }

    public Departament(Integer id, String nameDepartament) {
        this.id = id;
        this.nameDepartament = nameDepartament;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameDepartament() {
        return nameDepartament;
    }

    public void setNameDepartament(String nameDepartament) {
        this.nameDepartament = nameDepartament;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Departament that = (Departament) o;
        return Objects.equals(id, that.id) && Objects.equals(nameDepartament, that.nameDepartament);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameDepartament);
    }

    @Override
    public String toString() {
        return "Departament{" +
                "id=" + id +
                ", nameDepartament='" + nameDepartament + '\'' +
                '}';
    }
}
