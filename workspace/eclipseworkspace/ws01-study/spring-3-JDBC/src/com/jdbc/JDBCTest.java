package com.jdbc;

import static org.hamcrest.CoreMatchers.nullValue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;


import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.context.TestExecutionListeners;

import com.mysql.cj.x.protobuf.MysqlxCrud.Update;
/**
 * 
 * @author wayne
 * @Description 使用具名参数最优
 * 使用jdbctemplate(rowmapper..) testNamedparmeterJdbcTemplate(map..) 来操作数据库
 * @version
 * @date 190728
 * 
 */
public class JDBCTest {

	
	public JDBCTest() {
		System.out.println("jdbctest实例化...");
	}


	private ApplicationContext ctx = null;
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate =null;

	{
		System.out.println("构造方法之前运行构造块,但是必须有构造方法");
		 ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		 jdbcTemplate=(JdbcTemplate) ctx.getBean("jdbcTmplate");
		 namedParameterJdbcTemplate =ctx.getBean(NamedParameterJdbcTemplate.class);
		 
	}
	/**
	 * 可以为参数取名字,当参数多的时候 更利于维护,
	 * jdbctemplate参数必须和问号顺序对应
	 * 
	 */
	@Test
	public void testNamedparmeterJdbcTemplate() {
		String sql ="insert into user(userid,username) values(:userid,:username)";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userid", "5");
		paramMap.put("username", "oo");
		
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
	/**
	 * 更好的方式,前提类属性名和参数名完全一样--既具名参数
	 */
	@Test
	public void testNamedparmeterJdbcTemplate2() {
		String sql ="insert into user(userid,username) values(:userId,:userName)";
		User user  = new User();
		user.setUserId(6);
		user.setUserName("sss");
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
		
		namedParameterJdbcTemplate.update(sql, paramSource);
		
	}
	
	/**
	 * 执行批量更新
	 */
	@Test
	public void testBachUpdate() {
		System.out.println("批量更新");
		String sql ="insert into user values(?,?)";
		
		List<Object[]> bachObject = new ArrayList<>();
		bachObject.add(new Object[]{3,"gg"});
		bachObject.add(new Object[] {4,"ww"});
		jdbcTemplate.batchUpdate(sql,bachObject);
		
	}
	
	@Test
	public void Update() {
		String sql ="update user set username ='baba' where userid =1";
		jdbcTemplate.execute(sql);
		
	}
	
	/**
	 * 查询 返回对象     用queryForObject(sql, rowMapper);
	 * rowmapper决定如何去印射结果集的行
	 * 既sql的别名和bean的属性名印射
	 */
	@Test
	public void  testSel() {
		
		String sql = "select userId,userName from user where userid = 1";
		
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
		User user = jdbcTemplate.queryForObject(sql, rowMapper);
		
		System.out.println(user);
	}
	
	/**
	 * 查询返回单个的值,或者统计条数 queryForObject(sql, condition);
	 * 只能返回一条
	 */
	@Test
	public void testquery1() {
		
		String sql = "select count(*) from user";
		int count = jdbcTemplate.queryForObject(sql,Integer.class);
		System.out.println(count);
		
		}
	/**
	 * 查询返回list
	 */
	@Test
	public void  testSelList() {
		String sql = "select userId,userName from user where userid>?";
		
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class); 
		List<User> user = jdbcTemplate.query(sql, rowMapper,1);
		System.out.println(user);
		
	}
	@Test
	public void testDataSource() throws SQLException {
		DataSource dataSource = (DataSource) ctx.getBean("dataSource");
		System.out.println(dataSource.getConnection());
		
	}
}
