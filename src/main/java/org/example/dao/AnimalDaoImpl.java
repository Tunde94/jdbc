package org.example.dao;


import org.example.model.Animal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;



public class AnimalDaoImpl implements AnimalDao{
    private final Connection connection;

    public AnimalDaoImpl(Connection connection){
        this.connection = connection;
    }
    public void createTable() throws SQLException {
        Statement statement = connection.createStatement();
                statement.execute("create table if not exists animals (" +
                        "id integer auto_increment, " +
                        "name varchar (30), " +
                        "species varchar(50), " +
                        "primary key(id))");
    }

    @Override
    public void dropTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("drop table animals");
    }

    public void create(Animal animal) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(
                "Insert into animals(name, species) values (?,?)"
        );
        preparedStatement.setString(1,animal.getName());
        preparedStatement.setString(2,animal.getSpecies());
        preparedStatement.execute();
    }

    public void findData() throws SQLException{

    }

    public void update() throws SQLException{

    }

    public void deleteData() throws SQLException{

    }


}
