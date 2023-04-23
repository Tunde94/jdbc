package org.example.dao;

import org.example.model.Animal;

import java.sql.SQLException;
import java.util.List;

//org.example.dao.model.Animal Data Access Object : clasa pentru a accesa date din "animals"
//manipilare structura baza de date(creare si stergere tabel)
//manipulare date - CRUD
public interface AnimalDao {

    //create table
    void createTable() throws SQLException;

    //adaugare date     CREATE animals
    void create(Animal animal) throws SQLException;

    //gasire date       READ animals
    List<Animal> read() throws SQLException;

    //modificare date   UPDATEA animals
    void update(Animal animal) throws SQLException;

    //stergere date     DELETE animals
    void delete(Integer animalId) throws SQLException;

    //sters tabel
    void dropTable() throws SQLException;
}
