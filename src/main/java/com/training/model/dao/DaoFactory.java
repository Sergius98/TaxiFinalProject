package com.training.model.dao;

import com.training.model.dao.impl.JDBCDaoFactory;
import com.training.model.dao.interfaces.*;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract CarDao createCarDao();
    public abstract DiscountDao createDiscountDao();
    public abstract LoyaltyThresholdDao createLoyaltyThresholdDao();
    public abstract StreetDao createStreetDao();
    public abstract TaxiDao createTaxiDao();
    public abstract UserDao createUserDao();

    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    DaoFactory temp = new JDBCDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}
