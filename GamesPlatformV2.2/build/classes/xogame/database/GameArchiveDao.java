/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogame.database;

import xogame.database.model.GameArchive;
import xogame.database.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class GameArchiveDao {

    public void selectGameArchive(ArrayList<GameArchive> gameArchives) throws SQLException, ClassNotFoundException {

        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        String query = "select * from gamearchive;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Timestamp timestamp = resultSet.getTimestamp("gameDateTime");
                String dateToString = dateFormat.format(timestamp);//convert date to string
                gameArchives.add(new GameArchive(resultSet.getInt("id"), dateToString));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionManager.closeConnection(preparedStatement, resultSet, connection);
        }
    }

    public int insertGameArchive() throws SQLException, ClassNotFoundException {

        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        String query = "insert into gamearchive (gameDateTime) values(current_timestamp)";//current_timestamp (retun actual data time) work in database
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();
            ResultSet ResultSet = preparedStatement.getGeneratedKeys();

            if (ResultSet.next()) {
                return (ResultSet.getInt(1));
            }

        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        } finally {
            connectionManager.closeConnection(preparedStatement, resultSet, connection);
        }
        return 0;
    }

}
