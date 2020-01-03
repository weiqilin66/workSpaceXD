package com.lwq.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 描述
 * @author: LinWeiQi
 */
@Component
public class Filter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(Filter.class);
    @Override
    public String filterType() {
        return "pre"; // 路由之前过滤
    }

    @Override
    public int filterOrder() {
        return 0; // 过滤顺序
    }

    @Override
    public boolean shouldFilter() { // 这里可以写逻辑判断，是否要过滤，true,永远过滤。
        return false;
        //return true;
    }

    @Override
    public Object run() throws ZuulException { //过滤器的具体逻辑
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("token");
        if(accessToken == null) {
            log.warn("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("token is empty");
            }catch (Exception e){}

            return null;
        }
        log.info("ok");
        return null;
    }
}
