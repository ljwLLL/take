package com.hpower.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class CORSFilter implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        //允许携带token---是否允许后续请求携带认证信息
        res.addHeader("Access-Control-Allow-Credentials", "true");
        //指定允许其他域名访问 (*/指定域名/动态域名) 指定域名多指定于Nginx服务器的域名
        res.addHeader("Access-Control-Allow-Origin", "*");
        //指定的访问方法
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        //请求首部,与前端同步设置的首部信息
        res.addHeader("Access-Control-Allow-Headers", "Content-Type,X-CAF-Authorization-Token,sessionToken,Token");
        //同意将前端的信息放进来---在涉及跨域请求时,response中大部分header需要源服务端同意才能拿到,所以需要在response中增加一个如下header:
        res.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        //是否有options
        if (((HttpServletRequest) request).getMethod().equals("OPTIONS")) {
            response.getWriter().println("ok");
            return;
        }
        chain.doFilter(request, response);
    }
    @Override
    public void destroy() {
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}
