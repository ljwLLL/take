package com.hpower.support;

import com.alibaba.fastjson.JSON;
import com.hpower.dto.LoginUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 接口支持基类
 *
 * @author yangyang.jiang
 * @date 2020/03/24
 * @since 1.0
 */
public abstract class BaseSupport {

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    protected LoginUser getLoginUser() {
        //获取session中的用户信息
        HttpSession session = getHttpServletRequest().getSession();
        String string = (String)session.getAttribute(session.getId());
        return JSON.parseObject(string, LoginUser.class);
    }

    /**
     * 获取当前http请求对象
     *
     * @return
     */
    protected HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取当前http响应对象
     *
     * @return
     */
    protected HttpServletResponse getHttpServletResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }
}
