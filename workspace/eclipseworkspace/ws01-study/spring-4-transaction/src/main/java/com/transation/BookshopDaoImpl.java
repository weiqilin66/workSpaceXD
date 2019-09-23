package com.transation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.transation.dao.BookShopDao;
@Repository("bookShopDao")
public class BookshopDaoImpl implements BookShopDao {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int findBookPriceByIsbn(String isbn) {
		
		String sql = "select price from book where isbn = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, isbn);
	
	}

	public void updateBookDtock(Book book) {
		//验证库存是否充足的编程式事务
		String sql0 = "select stock from book_stock where isbn=? ";
		int stock = jdbcTemplate.queryForObject(sql0, Integer.class, book.getIsbn());
		if (stock ==1) {
			throw new BookStockException("库存不足");
			
		}
		String sql = "update book_stock set stock=stock-1 where isbn =:isbn";
		
		SqlParameterSource paramSource =new BeanPropertySqlParameterSource(book);  
		namedParameterJdbcTemplate.update(sql, paramSource);

	}

	public void updateUserAccount(String username, int price) {
		String sql = "update account set balance =balance -? where username =?";
		jdbcTemplate.update(sql,price ,username );
	}

}
