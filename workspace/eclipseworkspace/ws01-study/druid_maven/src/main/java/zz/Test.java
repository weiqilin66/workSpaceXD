package zz;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.druid.pool.DruidDataSource;

public class Test {
	public static void main(String[] args) {
		ApplicationContext  ctx = new ClassPathXmlApplicationContext("llw/druid.xml");
		
		DataSource dtDataSource = ctx.getBean(DruidDataSource.class);
		try {
			System.out.println(dtDataSource.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
