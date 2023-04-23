package dao;

import java.sql.SQLException;

//Animal Data Access Object : clasa pentru a accesa date din "animals"
//manipilare structura baza de date(creare si stergere tabel)
//manipulare date - CRUD
public interface AnimalDao {

    //create table
    void createTable() throws SQLException;

    //adaugare date     CREATE animals
    void addData() throws SQLException;

    //gasire date       READ animals
    void findData() throws SQLException;

    //modificare date   UPDATEA animals
    void update() throws SQLException;

    //stergere date     DELETE animals
    void deleteData() throws SQLException;

    //sters tabel
    void dropTable() throws SQLException;
}
