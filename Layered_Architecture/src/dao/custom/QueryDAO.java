package dao.custom;

import dao.SuperDAO;
import model.customDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    ArrayList<customDTO> searchOrderByOrderID(String Id) throws SQLException, ClassNotFoundException;
}
