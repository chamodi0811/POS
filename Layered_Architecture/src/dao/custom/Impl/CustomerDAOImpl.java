package dao.custom.Impl;

import dao.SQLUtil;
import dao.custom.CustomerDAO;
import model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author : Sanu Vithanage
 * @since : 0.1.0
 **/

public class CustomerDAOImpl implements CustomerDAO {



    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Customer");
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        while (rst.next()) {
            allCustomers.add(new CustomerDTO(rst.getString(1), rst.getString(2), rst.getString(3)));
        }
        return allCustomers;
    }

    @Override
    public boolean save(CustomerDTO dto) throws SQLException, ClassNotFoundException {
       return SQLUtil.executeUpdate("INSERT INTO Customer (id,name, address) VALUES (?,?,?)", dto.getId(), dto.getName(), dto.getAddress());
    }


    @Override
    public boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException {
       return SQLUtil.executeUpdate("UPDATE Customer SET name=?, address=? WHERE id=?", dto.getName(), dto.getAddress(), dto.getId());
    }

    @Override
    public CustomerDTO Search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Customer WHERE id=?", id);
        if (rst.next()) {
            return new CustomerDTO(rst.getString(1), rst.getString(2), rst.getString(3));
        }
        return null;
    }


    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("SELECT id FROM Customer WHERE id=?", id).next();
    }


    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM Customer WHERE id=?", id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
           String id = rst.getString("id");
           int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
           return "C00-001";
        }
   }

}

 /*   @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
       // Connection connection = DBConnection.getDbConnection().getConnection();
       // Statement stm = connection.createStatement();
       // ResultSet rst = stm.executeQuery("SELECT * FROM Customer");
        ResultSet rst= SQLUtil.executeQuery("SELECT * FROM Customer");
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        while (rst.next()) {
           // String id = rst.getString(1);
           // String name = rst.getString(2);
            //String address = rst.getString(3);
            allCustomers.add(new CustomerDTO(rst.getString(1),  rst.getString(2), rst.getString(3)));
        }
        return allCustomers;

    }

    //save Customer
    @Override
    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
       /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer (id,name, address) VALUES (?,?,?)");
        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAddress());
        return pstm.executeUpdate() > 0;*/
    //return SQLUtil.executeUpdate("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",dto.getId(),dto.getName(),dto.getAddress());
    //  }


    //update customer
   /* @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
       /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getId());
        return pstm.executeUpdate() > 0;*/
    // return SQLUtil.executeUpdate("UPDATE Customer SET name=?, address=? WHERE id=?",dto.getName(),dto.getAddress(),dto.getId());
    // }


  /*  @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
       /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT id FROM Customer WHERE id=?");
        pstm.setString(1, id);
        return pstm.executeQuery().next();*/
    //  ResultSet rst=SQLUtil.executeQuery("SELECT id FROM Customer WHERE id=?",id);


    //  return rst.next();

    //  }


   /* @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE id=?");
        pstm.setString(1, id);
        return pstm.executeUpdate() > 0;*/
    //return SQLUtil.executeUpdate("DELETE FROM Customer WHERE id=?",id);
    // }

  /*  @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
       // Connection connection = DBConnection.getDbConnection().getConnection();
       // ResultSet rst = connection.createStatement().executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        ResultSet rst = SQLUtil.executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }/*
    }

   */



