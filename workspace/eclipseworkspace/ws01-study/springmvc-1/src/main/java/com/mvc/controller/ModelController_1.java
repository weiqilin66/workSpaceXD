package com.mvc.controller;



import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 模型处理(数据传到前台)
 * 1.modelAndView 会把model中数据放入request域对象(requestScope)中    
 *  返回值为modelandview,通过构造方法设置了跳转的页面(未拼接)
 *  
 * 2.传参中放入map 或者model   返回值为页面string
 * 以上方法只放在requestScope中
 * @author wayne
 * @date
 * @Description
 *
 */
@Controller
public class ModelController_1 {

	private static final String SUCCESS="success";
	
	//使用modelAndView
	@RequestMapping("model1")
	public ModelAndView name() {
		String viewName = SUCCESS;
		ModelAndView modelAndView = new ModelAndView(viewName);
		modelAndView.addObject("time", new Date());
		return modelAndView;
	}
	
	@RequestMapping("/model2")
	public String testmap( Map<String, Object> map) {
		map.put("name", "joy");
		
		return SUCCESS;
	}
	@RequestMapping("/model3")
	public String testmodel(Model model) {
		model.addAttribute("name", "joy2");
		return SUCCESS;
	}
}
