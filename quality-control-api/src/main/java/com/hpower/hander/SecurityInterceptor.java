package com.hpower.hander;

import com.alibaba.fastjson.JSON;
import com.hpower.errorcode.ApiErrorCode;
import com.hpower.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Classname 请求拦截器
 * @Description TODO
 * @Date 2020/3/24 12:42 下午
 * @Created yangyang.jiang
 */
@Slf4j
public class SecurityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取当前session
        HttpSession session = request.getSession();
        //根据sessionid获取当前登录人的用户信息
        if (session.getAttribute(session.getId()) != null) {
            return true;
        } else {
            log.warn("用户未授权：sessionId = {}", session.getId());
            returnJSON(response, JSON.toJSONString(R.failed(ApiErrorCode.FORBIDDEN.getCode(),ApiErrorCode.FORBIDDEN.getMsg())));
            return false;
        }
    }

    private void returnJSON(HttpServletResponse response, String jsonString) {
        PrintWriter printWriter = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        try {
            printWriter = response.getWriter();
            printWriter.print(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("拦截器鉴权返回错误信息异常:" + e.getMessage());
        }
    }
}
