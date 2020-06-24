package com.god.beetl.config;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 处理无权限请求
 */
@Slf4j
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * Ajax返回json信息,其余返回403页面
     *
     * @param request
     * @param response
     * @param exception
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException exception) throws IOException, ServletException {
        boolean isAjax = ControllerTools.isAjaxRequest(request);
        log.debug("CustomAccessDeniedHandler handler");
        if (!response.isCommitted()) {
            if (isAjax) {
                String msg = exception.getMessage();
                log.info("accessDeniedException message:{}", msg);
                String accessDenyMsg = "{\"code\":403, \"msg\":\"没有权限\"}";
                ControllerTools.print(response, accessDenyMsg);
            } else {
                request.setAttribute(WebAttributes.ACCESS_DENIED_403, exception);
                response.setStatus(HttpStatus.FORBIDDEN.value());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/403");
                dispatcher.forward(request, response);
            }
        }
    }

    public static class ControllerTools{
        /**
         * 是否是ajax请求
         *
         * @param request httpServletRequest
         * @return
         */
        public static boolean isAjaxRequest(HttpServletRequest request) {
            return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
        }

        /**
         * 输出msg
         *
         * @param response http响应
         * @param msg 输出json的信息
         * @throws IOException
         */
        public static void print(HttpServletResponse response, String msg) throws IOException {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(msg);
            writer.flush();
            writer.close();
        }
    }

}
