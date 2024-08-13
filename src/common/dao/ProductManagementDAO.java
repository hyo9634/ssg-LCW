package common.dao;

import lib.ConnectionPool;

import java.sql.Connection;

public class ProductManagementDAO {

    private ConnectionPool conncp = null;
    private Connection connection = null;

    public ProductManagementDAO(){
        init();
    }
    private void init(){
        conncp = ConnectionPool.getInstance();
    }
}
