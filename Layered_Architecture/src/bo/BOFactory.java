package bo;

import bo.custom.Impl.CustomerBOImpl;
import bo.custom.Impl.ItemBOImpl;
import bo.custom.Impl.PurcharsOrderBOImpl;
import bo.custom.PurcharsOrderBO;
import model.CustomerDTO;
import model.ItemDTO;
import model.OrderDetailDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BOFactory{
    private static BOFactory boFactory;

    private BOFactory(){

    }
    public static BOFactory getBoFactory(){
        if (boFactory==null){
            boFactory=new BOFactory();
        }
        return boFactory;
    }
    public enum BOTypes{
        CUSTOMER, ITEM, PURCHARS_ORDER
    }
    public SuperBO getBO(BOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case PURCHARS_ORDER:
                return new PurcharsOrderBOImpl();
            default:
                     return null;
                    }


        }
    }

