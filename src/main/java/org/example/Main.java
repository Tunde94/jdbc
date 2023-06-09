package org.example;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.example.dao.AnimalDao;
import org.example.dao.AnimalDaoImpl;
import org.example.dao.FoodDao;
import org.example.dao.FoodDaoImpl;
import org.example.model.Animal;
import org.example.model.Food;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        String dbLocation = "localhost:3306";
        String dbName = "jdbc_db";
        String dbUser = "root";
        String dbPassword = "csavas";//parola mea pentru baza de date

        MysqlDataSource dataSource = new MysqlDataSource();
        //formatul pentru url- ul de conectare la baza de date
        //jdbc:mysql://<<locatia server-ului de baze de date>>/<<nume;e bazei de date>>
        //jdbc:mysql://localhost:3306/jdbc_db
        dataSource.setURL("jdbc:mysql://" + dbLocation + "/" + dbName);
        dataSource.setUser(dbUser);
        dataSource.setPassword(dbPassword);

        try {
            LOGGER.log(Level.INFO, "Trying to connect to db");
            Connection connection = dataSource.getConnection();
            LOGGER.log(Level.INFO, "The connection was successful");

            AnimalDao animalDao = new AnimalDaoImpl(connection);
            FoodDao foodDao = new FoodDaoImpl(connection);

            Statement statement = connection.createStatement();

            animalDao.createTable();
            foodDao.createTable();
//            statement.execute("create table if not exists food(" +
//                    "id integer auto_increment, "+
//                    "name varchar(50), " +
//                    "description varchar(100), " +
//                    "calories_per_100 integer," +
//                    "expiration_date date, " +
//                    "primary key (id) )");
            LOGGER.info("Create food table was successful");

            //statement -> used for transferring commands to database
            statement.execute("create table if not exists animals (id integer auto_increment, name varchar (30), species varchar(50), primary key(id))");
            LOGGER.info("Create animal table was successful");

            animalDao.create(new Animal(null, "Lucky", "dog"));
            animalDao.create(new Animal(null, "Rex", "dog"));
            animalDao.create(new Animal(null, "Lulu", "cat"));
            //statement.execute("Insert into animals (name, species) values (\"Lucky\", \"Dog\")");
            //statement.execute("Insert into animals (name, species) values (\"Rex\", \"Dog\")");
            LOGGER.info("Data insertion in animals table was successful");

            animalDao.update(new Animal(2,"Labus","dog"));
            //statement.execute("Update Animals Set name = \"Lulu\" where id = 1 ");
            LOGGER.info("Data updating in animals table was successful");

            Date expirationDate = Date.valueOf("2024-10-12");
            String foodName = "cioco";
            foodDao.create(new Food(
                    null,
                    foodName,
                    "Ciocolata de casa",
                    750,
                    expirationDate));

          /*  PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into food ( name, description, calories_per_100, expiration_date) values(?,?,?,?)");
            Date expirationDate = Date.valueOf("2024-10-12");
            preparedStatement.setString(1,"ciocolata");
            preparedStatement.setString(2,"ciocolata de casa");
            preparedStatement.setInt(3,550);
            preparedStatement.setDate(4,expirationDate);

            preparedStatement.setString(1,"pizza");
            preparedStatement.setString(2,"Hawaii pizza");
            preparedStatement.setInt(3,750);
            preparedStatement.setDate(4,expirationDate);

            //intodeaunea trebuie rulat .execute() daca vrem sa fie executat sql
            preparedStatement.execute();*/

           /* ResultSet rs = statement.executeQuery("select * from animals");
            //rs.next();
            while(rs.next() == true) {
                System.out.println(rs.getInt(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
                rs.next();
            }
*/

            List<Animal> animals = animalDao.read();
            System.out.println("Animals in the database : ");
            for (Animal animal : animals) {
                System.out.println(animal);
            }
            //display all foods :D
            //food:
            //1. ciocolata - ciocolata de casa 550kcal per 100 - expira la 2024-10-12
            /*ResultSet rsFood = statement.executeQuery("select * from food where calories_per_100 < 800");
            while (rsFood.next() == true){
                System.out.println(rsFood.getInt(1) + ". "
                        + rsFood.getString(2) + " - "
                        + rsFood.getString(3) + " kcal per 100 "
                        + rsFood.getInt(4) + " - expira la "
                        + rsFood.getDate(5));

                rsFood.next();
            }*/
            System.out.println("Foods in the food database : ");

            List<Food> foods = foodDao.read();
            for (Food food : foods) {
                System.out.println(food);
            }
            //2. pizza -  hawaii pizza - 750 kcal per 100 expira 2024-10-12

            animalDao.delete(2);
            LOGGER.info("The element was deleted");

            System.out.println("Animals in the database : ");
            animals = animalDao.read();
            for (Animal animal : animals) {
                System.out.println(animal);
            }

            //statement.execute("drop table animals");
            animalDao.dropTable();
            LOGGER.info("Table animals dropped successful");

            //statement.execute("drop table food");
            foodDao.dropTable();
            LOGGER.info("Table food dropped successful");


        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
