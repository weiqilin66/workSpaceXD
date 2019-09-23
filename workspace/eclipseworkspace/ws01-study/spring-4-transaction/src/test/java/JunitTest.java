import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.transation.Book;
import com.transation.dao.BookShopDao;

public class JunitTest {
	private ApplicationContext ctx =null;
	private BookShopDao bookShopDao;
	
	{
		
		ctx= new ClassPathXmlApplicationContext("applicationContext.xml");
		bookShopDao=(BookShopDao) ctx.getBean("bookShopDao");
	}
	@Test
	public void testBookDaoImp1() {
		
		System.out.println(bookShopDao.findBookPriceByIsbn("1001"));
		
	}
	@Test
	public void testBookDaoImpl2() {
		Book book = new Book();
		book.setIsbn("1001");
		bookShopDao.updateBookDtock(book);
		
	}
	@Test
	public void testBookDaoImpl3() {
		
		bookShopDao.updateUserAccount("jack", 1000);
		
	}

}
