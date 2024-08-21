package model.dao;

import db.DB;
import model.dao.impl.SellerDaoJDBC;

// Classe para instanciar os daos
public class DaoFactory {

    public static SellerDao createSellerDao() {
        return new SellerDaoJDBC(DB.getConnection());
    }
}
