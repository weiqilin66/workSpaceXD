package com.transation.dao;

import com.transation.Book;

public interface BookShopDao {
	
	public int findBookPriceByIsbn(String isbn);
	
	public void updateBookDtock(Book book );
	
	//更新余额 ,使username的balance -price '事务'
	public void updateUserAccount(String username,int price);
}
