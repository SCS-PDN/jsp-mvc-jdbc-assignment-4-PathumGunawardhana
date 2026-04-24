package com.PathumGunawardhana.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String uri = request.getRequestURI();

        System.out.println("REQUEST: " + uri);

        if (uri.contains("/login")) {
            System.out.println("Login attempt detected");
        }

        if (uri.contains("/register")) {
            System.out.println("Course registration attempt");
        }

        if (uri.contains("/finish")) {
            System.out.println("Finish registration button clicked");
        }

        if (uri.contains("/logout")) {
            System.out.println("Logout Done");
        }

        return true; 
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {

        if (ex != null) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}