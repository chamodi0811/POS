package bo.custom.Impl;

import bo.custom.PurcharsOrderBO;
import dao.CrudDAO;
import dao.DAOFactory;
import dao.custom.*;
import dao.custom.Impl.CustomerDAOImpl;
import dao.custom.Impl.ItemDAOImpl;
import dao.custom.Impl.orderDAOImpl;
import dao.custom.Impl.orderDetailDAOImpl;
import db.DBConnection;
import model.CustomerDTO;
import model.ItemDTO;
import model.OrderDTO;
import model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PurcharsOrderBOImpl implements PurcharsOrderBO {

  //  private final CustomerDAO customerDAO = new CustomerDAOImpl();
  //  private final ItemDAO itemDAO = new ItemDAOImpl();
   // private final OrderDAO orderDAO = new orderDAOImpl();
   // private final OrderDetailsDAO orderDetailsDAO = new orderDetailDAOImpl();
  //Hiding the object creation logic using the Factory pattern
  private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);// hide the object creation logic through the factory
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);

    public boolean purcharsOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
            Connection connection =     DBConnection.getDbConnection().getConnection();
            if ( orderDAO.exist(orderId)) {
            }
            connection.setAutoCommit(false);

            boolean save = orderDAO.save(new OrderDTO(orderId, orderDate, customerId));

            if (!save) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            CrudDAO<OrderDetailDTO,String> orderDetailDAO = new orderDetailDAOImpl();

            for (OrderDetailDTO detail : orderDetails) {

                orderDetailDAO.save(detail);
                if (!save) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }

                //Search & Update Item
                // ItemDTO item = findItem(detail.getItemCode());
                ItemDTO item =searchItem(detail.getItemCode());
                item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());
                boolean update =  itemDAO.update(item);

                if (!update) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }

            connection.commit();
            connection.setAutoCommit(true);
            return true;

       // return false;
    }
    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.Search(id);
    }
    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.Search(code);
    }
    @Override
    public boolean checkItemIsAvailable(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }
    @Override
    public boolean checkCustomerIsAvailable(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }
    @Override
    public String generateNewOrderID() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewID();
    }
    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }
    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }

}
