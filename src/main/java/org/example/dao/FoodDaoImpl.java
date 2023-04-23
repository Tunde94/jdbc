package org.example.dao;

import org.example.model.Animal;
import org.example.model.Food;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDaoImpl implements FoodDao{
    private final Connection connection;
    public FoodDaoImpl(Connection connection){
        this.connection = connection;
    }
    public void createTable() throws SQLException{
        Statement statement = connection.createStatement();
        statement.execute("create table if not exists food(" +
                "id integer auto_increment, "+
                "name varchar(50), " +
                "description varchar(100), " +
                "calories_per_100 integer," +
                "expiration_date date, " +
                "primary key (id) )");
    }

    @Override
    public void dropTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("drop table food");
    }

    public void create(Food food) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into food ( name, description, calories_per_100, expiration_date) values(?,?,?,?)");
        preparedStatement.setString(1,food.getName());
        preparedStatement.setString(2,food.getDescription());
        preparedStatement.setInt(3,food.getCalories_per_100());
        preparedStatement.setDate(4,food.getExpiration_date());
        preparedStatement.execute();
    }

    @Override
    public List<Food> read() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from food");

        List<Food> foods = new ArrayList<>();
        while(rs.next() == true) {
            Food food = new Food();
            food.setId(rs.getInt(1));
            food.setName(rs.getString(2));
            food.setDescription((rs.getString(3)));
            food.setCalories_per_100(rs.getInt(4));
            food.setExpiration_date(rs.getDate(5));
            foods.add(food);
        }
        return foods;
    }
}
