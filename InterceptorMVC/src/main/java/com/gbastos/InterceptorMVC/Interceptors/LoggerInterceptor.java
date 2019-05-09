package com.gbastos.InterceptorMVC.Interceptors;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

/**
 * The Class LoggerInterceptor is a simple sample of how to intercept HTTP Servlet Request,
 * interrogating it to extract info in order to save it into the log.
 *
 * @author Guilherme Borges Bastos <guilhermeborgesbastos@gmail.com>
 */
public class LoggerInterceptor extends HandlerInterceptorAdapter {

  private static Logger LOG = LoggerFactory.getLogger(LoggerInterceptor.class);
  
  private static List<String> PASS_ARGS =  Arrays.asList("password", "answer", "pass", "pwd");

  /**
   * Gets the parameters from the HTTP Servlet Request.
   * 
   * In case we run into a password here, we’ll need to make sure we don’t log that of course. A
   * simple option is to replace passwords, and any other sensitive type of data, with stars.
   *
   * @param request
   * @return the parameters request
   */
  private String getParameters(final HttpServletRequest request) {
    
    final StringBuffer posted = new StringBuffer();
    final Enumeration<?> e = request.getParameterNames();
    
    if (e != null) {
      posted.append("?");
    }
    
    while (e != null && e.hasMoreElements()) {
      
      if (posted.length() > 1) {
        posted.append("&");        
      }
      
      final String curr = (String) e.nextElement();
      posted.append(curr).append("=");
      
      if (PASS_ARGS.contains(curr)) {
        posted.append("*****");
      } else {
        posted.append(request.getParameter(curr));
      }
      
    }
  
    final String ip = request.getHeader("X-FORWARDED-FOR");
    final String ipAddr = (ip == null) ? getRemoteAddr(request) : ip;
    
    if (!Strings.isNullOrEmpty(ipAddr)) {
      posted.append("&_psip=" + ipAddr);      
    }
    
    return posted.toString();
  }

  /**
   * Gets the remote address from the HTTP Servlet Request.
   *
   * @param request
   * @return the remote address - IP
   */
  private String getRemoteAddr(final HttpServletRequest request) {
    
    final String ipFromHeader = request.getHeader("X-FORWARDED-FOR");
    
    if (ipFromHeader != null && ipFromHeader.length() > 0) {
      
      LOG.debug("IP from proxy - X-FORWARDED-FOR : " + ipFromHeader);
      return ipFromHeader;
    }
    
    return request.getRemoteAddr();
  }

  /**
   * This method is called before handling a request, logging some properties from the HTTP Servlet
   * Request; it returns true, to allow the framework to send the request further to the handler
   * method (or to the next Interceptor). If the method returns false, Spring assumes that request
   * has been handled and no further processing is needed.
   *
   * @param request
   * @param response
   * @param handler
   * @return true, to send the request further
   * @throws Exception
   */
  @Override
  public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
      throws Exception {
    LOG.info("[preHandle][" + request + "]" + "[" + request.getMethod() + "]" + request.getRequestURI() + getParameters(request));
    return true;
  }

  /**
   * This hook runs when the HandlerAdapter is invoked the handler but DispatcherServlet is yet to
   * render the view. We can use this method to add additional attributes to the ModelAndView.
   *
   * @param request
   * @param response
   * @param handler
   * @param modelAndView
   * @throws Exception
   */
  @Override
  public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final ModelAndView modelAndView)
      throws Exception {
    LOG.info("[postHandle][" + request + "]");
  }

  /**
   * Executed after the complete request is finished. In case of an exception occurred the method
   * will save it into the Log.
   *
   * @param request
   * @param response
   * @param handler
   * @param ex the exception
   * @throws Exception
   */
  @Override
  public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
      final Object handler, final Exception ex) throws Exception {
    
    if (ex != null) {
      ex.printStackTrace();      
    }
    
    LOG.info("[afterCompletion][" + request + "][Exception: " + ex + "]");
  }
  
}
