package com.lsl.exam.filter;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lsl.exam.entity.backresult.ResultVO;
import com.lsl.exam.utils.ResultVoUtil;
import org.apache.tomcat.util.http.MimeHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * jwt校验过滤器
 */
@Component
@WebFilter(filterName = "jwtFilter",urlPatterns = {"/*"})
public class AuthJwtFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String url = httpServletRequest.getRequestURL().toString();

        //配置不进行jwt校验的请求路径
        List<String> urlList = new ArrayList<>();
        urlList.add("/exam/login");

        boolean flag = false;
        for (String strUrl : urlList){
            if (url.contains(strUrl)){
                flag = true;
            }
        }

        try {
            if (!flag){
                String token = httpServletRequest.getHeader("token");
                //校验token,jwt过期有jwt自行校验,如果超时了,会执行catch里代码
                DecodedJWT decodeJwt = JWT.require(Algorithm.HMAC256("aa082c-66rt89-29sr3t-y9t7b8")).build().verify(token);

                //获取jwt中的业务信息
                String account = decodeJwt.getClaim("account").asString();
                String username = decodeJwt.getClaim("username").asString();
                String rolename = decodeJwt.getClaim("rolename").asString();
                Map<String, String> headerMap = new HashMap<>();
                headerMap.put("account",account);
                headerMap.put("username",username);
                headerMap.put("rolename",rolename);

                //把业务信息添加到request的header
                addHeader(httpServletRequest,headerMap);


//                Class<?> superclass = servletRequest.getClass().getSuperclass().getSuperclass();
//                Field requestField = superclass.getDeclaredField("request");
//                requestField.setAccessible(true);
//                RequestFacade requestFacadeInstance = (RequestFacade) requestField.get(servletRequest);
////                RequestFacade requestFacadeInstance = (RequestFacade)superclass3;
//                Field requestField1 = requestFacadeInstance.getClass().getDeclaredField("request");
//                requestField1.setAccessible(true);
//                Object requestInstance =  requestField1.get(requestFacadeInstance);
//                Field coyoteRequestField = requestInstance.getClass().getDeclaredField("coyoteRequest");
//                coyoteRequestField.setAccessible(true);
//
//                Object coyoRequestInstance =  requestField1.get(requestInstance);
//                Field headersField = coyoRequestInstance.getClass().getDeclaredField("headers");
//                headersField.setAccessible(true);
//
//                MimeHeaders headers = (MimeHeaders) headersField.get(coyoRequestInstance);
//                headers.removeHeader("token");
//                headers.addValue("account").setString(account);
//                headers.addValue("username").setString(username);
//                headers.addValue("roleid").setString(roleid);
//

            }
        } catch (Exception e) {
            //jwt校验失败,返给前端的code=1,前端要重定向到登录页面
            PrintWriter writer = null;
            servletResponse.setCharacterEncoding("UTF-8");
            servletResponse.setContentType("text/html; charset=utf-8");

            try {
                writer = servletResponse.getWriter();
                ResultVO vo = ResultVoUtil.successLogout();
                String msg = JSON.toJSONString(vo);
                writer.println(msg);
            } catch (IOException ex) {

            } finally {
                if (writer != null){
                    writer.close();
                }
                return;
            }

        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    /**
     * 向request的header中放业务信息
     * @param request
     * @param headerMap
     */
    private void addHeader(HttpServletRequest request, Map<String, String> headerMap) {
        if (headerMap==null||headerMap.isEmpty()){
            return;
        }

        Class<? extends HttpServletRequest> c=request.getClass();
        //System.out.println(c.getName());
        System.out.println("request实现类="+c.getName());
        try{
            Field requestField=c.getDeclaredField("request");
            requestField.setAccessible(true);

            Object o=requestField.get(request);
            Field coyoteRequest=o.getClass().getDeclaredField("coyoteRequest");
            coyoteRequest.setAccessible(true);

            Object o2=coyoteRequest.get(o);
            System.out.println("coyoteRequest实现类="+o2.getClass().getName());
            Field headers=o2.getClass().getDeclaredField("headers");
            headers.setAccessible(true);

            MimeHeaders mimeHeaders=(MimeHeaders) headers.get(o2);
            for (Map.Entry<String,String> entry:headerMap.entrySet()){
                mimeHeaders.removeHeader(entry.getKey());
                mimeHeaders.addValue(entry.getKey()).setString(entry.getValue());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }



    @Override
    public void destroy() {

    }
}
