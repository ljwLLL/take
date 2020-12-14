package com.hpower.resolver;

import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HttpSessionIdResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;


/**
 * 配置支持cookie和header
 *
 * @author yangyang.jiang
 * @date 2020.03.23
 * @since 1.0
 */
public class CookieHeaderHttpSessionIdResolver implements HttpSessionIdResolver {

    private static final String HEADER_TOKEN = "token";

    private static final String HEADER_X_AUTH_TOKEN = "X-Auth-Token";

    private static final String HEADER_AUTHENTICATION_INFO = "Authentication-Info";

    private final String headerName;

    private static final String WRITTEN_SESSION_ID_ATTR = CookieHttpSessionIdResolver.class
            .getName().concat(".WRITTEN_SESSION_ID_ATTR");

    private CookieSerializer cookieSerializer = new DefaultCookieSerializer();


    /**
     * Convenience factory to create {@link CookieHeaderHttpSessionIdResolver} that uses
     * "X-Auth-Token" header.
     *
     * @return the instance configured to use "X-Auth-Token" header
     */
    public static CookieHeaderHttpSessionIdResolver token() {
        return new CookieHeaderHttpSessionIdResolver(HEADER_TOKEN);
    }

    /**
     * Convenience factory to create {@link CookieHeaderHttpSessionIdResolver} that uses
     * "X-Auth-Token" header.
     *
     * @return the instance configured to use "X-Auth-Token" header
     */
    public static CookieHeaderHttpSessionIdResolver xAuthToken() {
        return new CookieHeaderHttpSessionIdResolver(HEADER_X_AUTH_TOKEN);
    }

    /**
     * Convenience factory to create {@link CookieHeaderHttpSessionIdResolver} that uses
     * "Authentication-Info" header.
     *
     * @return the instance configured to use "Authentication-Info" header
     */
    public static CookieHeaderHttpSessionIdResolver authenticationInfo() {
        return new CookieHeaderHttpSessionIdResolver(HEADER_AUTHENTICATION_INFO);
    }

    /**
     * The name of the header to obtain the session id from.
     *
     * @param headerName the name of the header to obtain the session id from.
     */
    public CookieHeaderHttpSessionIdResolver(String headerName) {
        if (headerName == null) {
            throw new IllegalArgumentException("headerName cannot be null");
        }
        this.headerName = headerName;
    }

    @Override
    public List<String> resolveSessionIds(HttpServletRequest request) {
        //确定header是什么类型的，token/X-Auth-Token/Authentication-Info
        String headerValue = request.getHeader(this.headerName);

        return headerValue != null ? Collections.singletonList(headerValue)
                : this.cookieSerializer.readCookieValues(request);
    }

    @Override
    public void setSessionId(HttpServletRequest request, HttpServletResponse response,
                             String sessionId) {
        response.setHeader(this.headerName, sessionId);
        //cookie
        if (sessionId.equals(request.getAttribute(WRITTEN_SESSION_ID_ATTR))) {
            return;
        }
        request.setAttribute(WRITTEN_SESSION_ID_ATTR, sessionId);
        this.cookieSerializer
                .writeCookieValue(new CookieSerializer.CookieValue(request, response, sessionId));
    }

    @Override
    public void expireSession(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader(this.headerName, "");
        //cookie
        this.cookieSerializer.writeCookieValue(new CookieSerializer.CookieValue(request, response, ""));
    }


    /**
     * Sets the {@link CookieSerializer} to be used.
     *
     * @param cookieSerializer the cookieSerializer to set. Cannot be null.
     */
    public void setCookieSerializer(CookieSerializer cookieSerializer) {
        if (cookieSerializer == null) {
            throw new IllegalArgumentException("cookieSerializer cannot be null");
        }
        this.cookieSerializer = cookieSerializer;
    }

}
