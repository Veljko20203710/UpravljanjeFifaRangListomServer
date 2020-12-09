package database;

import domain.DomainObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.apache.log4j.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Veljko
 */
public class DBBroker {

    private static final Logger LOGGER = Logger.getLogger(DBBroker.class);
    private final Connection connection;

    public DBBroker(Connection connection) {
        this.connection = connection;
    }

    public boolean insertRecord(DomainObject domainObject) {
        String upit = "INSERT INTO " + domainObject.getTableName() + "(" + domainObject.getAttributeNamesForInsert() + ") VALUES(" + domainObject.getAttributeValuesForInsert() + ")";
        try {
            Statement statement = connection.createStatement();
            statement.execute(upit);
            LOGGER.info("Query:" + upit);
            return true;
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage());
            return false;
        }
    }

    public boolean deleteRecord(DomainObject domainObject) {
        String upit = "DELETE FROM " + domainObject.getTableName() + " " + domainObject.getWhereConditionById();
        try (Statement statement = connection.createStatement();) {
            LOGGER.info("Query:" + upit);
            statement.execute(upit);
            return true;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            return false;
        }
    }

    public boolean updateRecord(DomainObject domainObject) {
        try (Statement statement = connection.createStatement();) {
            String upit = "UPDATE " + domainObject.getTableName() + " SET " + domainObject.getAtributeValuesForUpdate() + " " + domainObject.getWhereConditionById();
            statement.execute(upit);
            LOGGER.info("Query:" + upit);
            return true;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return false;
        }
    }

    public List<? extends DomainObject> selectRecord(DomainObject domainObject) throws SQLException {
        String upit = "SELECT " + domainObject.getAttributeNamesForInsert() + " FROM " + domainObject.getTableName();
        try (Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(upit);
            List<DomainObject> domainObjects = (List<DomainObject>) domainObject.RSToTable(resultSet);
            LOGGER.info("Query:" + upit);
            return domainObjects;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new SQLException();
        }
    }

    public List<? extends DomainObject> selectRecordWithCondition(DomainObject domainObject) throws SQLException {
        String upit = "SELECT " + domainObject.getAttributeNamesForInsert() + " FROM " + domainObject.getTableName() + " " + domainObject.getWhereConditionByAttribute();
        try (Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(upit);
            List<DomainObject> domainObjects = (List<DomainObject>) domainObject.RSToTable(resultSet);
            LOGGER.info("Query:" + upit);
            return domainObjects;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new SQLException();
        }
    }

    public void commitTransaction() throws SQLException {
        connection.commit();
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public void rollbackTransaction() {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage());
        }
    }
}
