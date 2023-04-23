package org.example.dao;

import org.example.model.Animal;
import org.example.model.Food;

import java.sql.SQLException;
import java.util.List;

public interface FoodDao {
    void createTable() throws SQLException;
    void dropTable() throws SQLException;
    void create(Food food) throws SQLException;
    List<Food> read() throws SQLException;
}
