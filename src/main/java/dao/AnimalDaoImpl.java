package dao;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
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

    public void addData() throws SQLException{
        Statement statement = connection.createStatement();
        statement.execute("Insert into animals (name, species) values (\"Lucky\", \"Dog\")");


    }

    public Ani findData() throws SQLException{

    }

    public void update() throws SQLException{

    }

    public void deleteData() throws SQLException{

    }


}
