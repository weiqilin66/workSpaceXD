package com.crud.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.pagehelper.PageInfo;

/**
 * @author wayne
 * @Description 用Spring测试模块提供的测试请求功能,测试crud请求的正确性
 * @version
 * @date 0806
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:dispatcherServlet.xml" })
public class TestSpringRequest {
	// 传入springmvc的ioc,注入需要使用@WebAppConfiguration注解,否者只能自动注入springapplication中的bean
	@Autowired
	WebApplicationContext context;
	// 虚拟mvc请求,获取处理结果
	MockMvc mockMvc;

	// junit的before ,每次test前都会执行
	@Before
	public void initMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	@Test
	public void testPage() throws Exception {
		//模拟拿到返回值
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "1")).andReturn();
		//请求成功后,请求域中的pageInfo
		MockHttpServletRequest request =result.getRequest();
		PageInfo piInfo = (PageInfo) request.getAttribute("pageInfo");
		//System.out.println(piInfo.getPageNum());
		//System.out.println(piInfo.getPages());
		//System.out.println(piInfo.getTotal());
		int[] arr = piInfo.getNavigatepageNums();
		for (int i : arr) {
			System.out.println(i);
		}
		
	}

}
