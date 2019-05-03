package com.gbastos.InterceptorMVC.Interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.gbastos.InterceptorMVC.Utils.TimerUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The Class TimerInterceptor is a simple sample of how to intercept HTTP Servlet Request, tracking
 * the time taken by the handler method to process a client’s request, logging it into the Log.
 * 
 * @author Guilherme Borges Bastos <guilhermeborgesbastos@gmail.com>
 */
public class TimerInterceptor extends HandlerInterceptorAdapter {

  private static Logger LOG = LoggerFactory.getLogger(TimerInterceptor.class);
  
  private static String LOG_PREFIX = "[REQUEST TIME] ";

  private static final String STARTED_AT = "startedAt";

  /**
   * This method is called before handling a request embedding the current time ( in Milliseconds )
   * to the request; it returns true, to allow the framework to send the request further to the
   * handler method (or to the next Interceptor). If the method returns false, Spring assumes that
   * request has been handled and no further processing is needed.
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

    final long startedAt = System.currentTimeMillis();
    request.setAttribute(STARTED_AT, startedAt);

    // Used to sleep the Thread for a random time in order to achieve randomicity in the Logging.
    TimerUtils.doThreadSleep();

    return true;
  }

  /**
   * This hook runs when the HandlerAdapter has invoked the handler but DispatcherServlet is yet to
   * render the view. In the implementation below we use it to determine the time taken by the
   * handler method to process a client’s request, logging it into the Log.
   *
   * @param request
   * @param response
   * @param handler
   * @param modelAndView
   * @throws Exception
   */
  @Override
  public void postHandle(final HttpServletRequest request, final HttpServletResponse response,
      final Object handler, final ModelAndView modelAndView) throws Exception {

    final long startedAt = (long) request.getAttribute(STARTED_AT);
    request.removeAttribute(STARTED_AT);

    final long finishedAt = System.currentTimeMillis();
    final long requestTime = finishedAt - startedAt;

    LOG.info( LOG_PREFIX + requestTime + " Milliseconds");
  }

}
