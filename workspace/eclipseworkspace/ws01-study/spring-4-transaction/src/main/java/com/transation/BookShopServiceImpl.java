package com.transation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transation.dao.BookShopDao;
import com.transation.service.BookShopService;
@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService{

	@Autowired
	private BookShopDao bookShopDao;
	/**
	 * 
	 * 异常分析:
	 * 当余额不足时,先更新了库存 ,没有异常,库存减少.
	 * 而更新余额时发生了异常 余额没有减少,但是库存却少了
	 */
	@Transactional
	public void purchase(String username, String isbn) {
		//获取单价
		int price = bookShopDao.findBookPriceByIsbn(isbn);
		System.out.println(price);
		//更新库存
		Book book =new Book();
		book.setIsbn(isbn);
		bookShopDao.updateBookDtock(book);
		System.out.println("库存－1");
		//更新余额
		bookShopDao.updateUserAccount(username, price);
		System.out.println("余额减少");
	}
	


}
