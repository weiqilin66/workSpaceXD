package com.transation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.transation.service.BookShopService;
import com.transation.service.Cashier;
@Service("cashier")
public class CashierImpl implements Cashier {

	@Autowired
	private BookShopService bookShopService;
	/**
	 * !事务的传播行为(propagation)
	 * 一个事务调用另一个事务
	 * 默认传播行为@Transactional 已经有一个的时候 用他,没有的时候创建一个事务, required 
	 * eg:调用checkout方法时候先有了个事务,所以就使用checkout的事务 不使用purchase的事务
	 * 
	 * 当传播行为是@Transactional(propagation = Propagation.REQUIRES_NEW) 使用新的事务 ,
	 * eg:调用checkout 使用purchase的事务,checkout的事务挂起,完成后再使用checkout的事务
	 * 
	 * 其余5个传播行为较少使用
	 * 
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW,
			isolation = Isolation.READ_COMMITTED,
			/*
			 * 使用isolation指定事务的隔离级别 ,最常用的属性READ_COMMITTED
			 * 默认情况下声明式事务对所有运行时异常进行回滚,也可以用READ_COMMITTED对应的属性进行设置
			*/
			//noRollbackFor = {BookStockException.class} 对某个异常不回滚
			readOnly = false,//指定事务是否为只读, 如果只读数据不更新数据指定true可以帮助数据库引擎优化事务
			timeout = 3 //使用timeout 指定强制回滚前事务能占用的时间(3秒)
			)
	public void checkout(String userName, List<String> isbnList) {
		for (String isbn : isbnList) {
			bookShopService.purchase(userName, isbn);
		}

	}
/**
 * 补充说明 
 * 事务的4大特性:
 * 原子性：事务包含的所有操作要么全部成功，要么全部失败回滚
 * 一致性：事务执行前和执行后必须处于一致性状态，
    例：用户A和用户B的前加起来一共是5000; 无论AB用户之间是如何相互转换的，事务结束后两个用户的钱加起来还是5000,这就是事务的一致性
隔离性：当多个用户并发访问数据库时，数据库为每一个用户开启的事务，不被其他事务的操作所干扰，多个并发事务之间要相互隔离
持久性：一个事务一旦被提交了，那么对数据库中的数据的改变就是永久性的，即便在数据库系统遇到故障的情况下也不会丢失事物的操作
 */
	 
	
	
}
