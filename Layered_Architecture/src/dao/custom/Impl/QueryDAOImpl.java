package dao.custom.Impl;

import dao.SQLUtil;
import dao.custom.QueryDAO;
import model.customDTO;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public  ArrayList<customDTO> searchOrderByOrderID(String Id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("select Orders.oid,Orders.date,Orders.customerID,OrderDetails.itemCode,OrderDetails.qty,OrderDetails.unitPrice from Orders inner join OrderDetails on Orders.oid=OrderDetails.oid where Orders.oid=?;", Id);
        ArrayList<customDTO> orderRecords=new ArrayList<>();
        while (rst.next()) {
           orderRecords.add(new customDTO(rst.getString(1),LocalDate.parse(rst.getString(2)) , rst.getString(3), rst.getString(4), rst.getInt(5), rst.getBigDecimal(6)));
        }

        return orderRecords;

    }
}
