package dao;

import model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public interface CrudDAO<T,ID> extends SuperDAO {
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    boolean save(T dto) throws SQLException, ClassNotFoundException ;

     boolean update(T dto) throws SQLException, ClassNotFoundException;

     T Search(ID id) throws SQLException,ClassNotFoundException;

     boolean exist(ID id) throws SQLException, ClassNotFoundException;

    boolean delete(ID id) throws SQLException, ClassNotFoundException;

     String generateNewID() throws SQLException, ClassNotFoundException;


}
