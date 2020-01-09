package lwq.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lwq.constant.DataSourceConfig;
import org.springframework.jdbc.core.JdbcTemplate;

import java.beans.PropertyVetoException;

/**
 * @Description: 无参构造方法 new 时候链接
 * @author: LinWeiQi
 */
public class JdbcTemplateQ  extends JdbcTemplate {
    public JdbcTemplateQ(){

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
    public void excuteOneSql(String sql){

        super.execute(sql);
    }
}