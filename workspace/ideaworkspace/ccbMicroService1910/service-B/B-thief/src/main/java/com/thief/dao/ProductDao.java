package com.thief.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.thief.entity.Product;
import lwq.constant.DataSourceConfig;
import org.springframework.jdbc.core.JdbcTemplate;
import java.beans.PropertyVetoException;

/**
 * @Description: 描述
 * @author: LinWeiQi
 */
public class ProductDao extends JdbcTemplate {
    public ProductDao(){

        //定义c3p0连接池
        ComboPooledDataSource ds = new ComboPooledDataSource();
        try {
            ds.setDriverClass(DataSourceConfig.DRIVER);
            ds.setUser(DataSourceConfig.USERNAME);
            ds.setPassword(DataSourceConfig.PASSWORD);
            ds.setJdbcUrl(DataSourceConfig.URL);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        super.setDataSource(ds);
    }
    public void addProduct(Product product){
        super.update("insert into product values (?,?,?,?,?)",
                product.getPid(),product.getTitle(),product.getPname(),product.getBrand(),product.getPrice());

    }
    public void createTable(String str){
        super.execute(str);
    }
}
