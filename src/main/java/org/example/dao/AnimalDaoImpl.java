package org.example.dao;


import org.example.model.Animal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


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

    @Override
    public List<Animal> read() throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from animals");

        List<Animal> animals = new ArrayList<>();
        while(rs.next() == true) {
            Animal animal = new Animal();
            animal.setId(rs.getInt(1));
            animal.setName(rs.getString(2));
            animal.setSpecies((rs.getString(3)));
            animals.add(animal);
        }
        return animals;
    }

    public void update(Animal updatedAnimal) throws SQLException{
        PreparedStatement preparedStatement =  connection.prepareStatement("Update animals Set name = ?, species =? where id = ?");
        preparedStatement.setString(1, updatedAnimal.getName());
        preparedStatement.setString(2, updatedAnimal.getSpecies());
        preparedStatement.setInt(3,updatedAnimal.getId());
        preparedStatement.executeUpdate();

    }

    public void delete(Integer animalId) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement
        ("delete from animals where id = ?");
        preparedStatement.setInt(1,animalId);
        preparedStatement.execute();
    }




}
