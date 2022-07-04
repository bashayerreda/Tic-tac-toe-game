/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogame.database;

import xogame.database.model.GameMove;
import xogame.database.model.GameRecord;
import xogame.database.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GameMoveDao {

    public void selectGameRecord(GameRecord gameRecord) throws SQLException, ClassNotFoundException {
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        String query = "select * from gamemove where idGame = ? ;";
        PreparedStatement statement = null;
        ResultSet resultset = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, gameRecord.getId());
            resultset = statement.executeQuery();
            while (resultset.next()) {

                gameRecord.addGameMove(
                        new GameMove(
                                resultset.getInt("cellNumber"),
                                resultset.getInt("cellOrder"),
                                resultset.getString("cellType").toCharArray()[0]));
                //convert string to charArray and take first element in array
            }
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        } finally {
            connectionManager.closeConnection(statement, resultset, connection);
        }

    }

    public void insertGameMove(GameRecord gameRecord) throws SQLException, ClassNotFoundException {

        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        connection.setAutoCommit(false);
        String query = "insert into gamemove(idGame,cellOrder,cellNumber,cellType) values(?,?,?,?)";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            
            for (int i = 0; i < gameRecord.getI(); i++) {
                preparedStatement.setInt(1, gameRecord.getId());
                preparedStatement.setInt(2, gameRecord.getGameMove()[i].getCellOrder());
                preparedStatement.setInt(3, gameRecord.getGameMove()[i].getCellNumber());
                preparedStatement.setString(4, String.valueOf(gameRecord.getGameMove()[i].getCellType()));
                preparedStatement.addBatch();
            }
          
           preparedStatement.executeBatch();
           connection.commit();
           
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        } finally {
            connectionManager.closeConnection(preparedStatement, resultSet, connection);
        }
    }

}
