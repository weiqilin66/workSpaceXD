import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.transation.dao.BookShopDao;
import com.transation.service.BookShopService;

public class TestService {

	private ApplicationContext ctx =null;
	private BookShopService bookShopService;
	
	{
		
		ctx= new ClassPathXmlApplicationContext("applicationContext.xml");
		bookShopService=(BookShopService) ctx.getBean("bookShopService");
	}

	@Test
	public void test1() {
		
		bookShopService.purchase("jack", "1002");
	}
}
