package com.gbastos.Filter.Filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * A Servlet filter to log request and response coming from the Client. The logging implementation
 * is pretty native and for study purpose only.
 * 
 * Visit the Github page for more details:
 * https://github.com/guilhermeborgesbastos/Spring-Boot-Studies/tree/Filter
 * 
 * @author Guilherme Borges Bastos <guilhermeborgesbastos@gmail.com>
 */
@Component
@Order(2)
public class RequestResponseLoggingFilter implements Filter {

  private final static Logger LOG = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

  @Override
  public void init(final FilterConfig filterConfig) throws ServletException {
    LOG.info("Initializing filter :{}", this);
  }

  /**
   * The doFilter method of the Filter is called by the container each time a request/response pair
   * is passed through the chain due to a client request for a resource at the end of the chain. The
   * FilterChain passed in to this method allows the Filter to pass on the request and response to
   * the next entity in the chain.
   *
   * @param request
   * @param response
   * @param chain
   * @throws IOException Signals that an I/O exception has occurred.
   * @throws ServletException the servlet exception
   */
  @Override
  public void doFilter(final ServletRequest request, final ServletResponse response,
      final FilterChain chain) throws IOException, ServletException {
    
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
    
    LOG.info("Logging Request  {} : {}", req.getMethod(), req.getRequestURI());
    chain.doFilter(request, response);
    LOG.info("Logging Response :{}", res.getContentType());
  }

  /**
   * Called by the web container to indicate to a filter that it is being taken out of service. This
   * method is only called once all threads within the filter's doFilter method have exited or after
   * a timeout period has passed. After the web container calls this method, it will not call the
   * doFilter method again on this instance of the filter.
   * 
   * This method gives the filter an opportunity to clean up any resources that are being held (for
   * example, memory, file handles, threads) and make sure that any persistent state is synchronized
   * with the filter's current state in memory. The default implementation is a NO-OP.
   */
  @Override
  public void destroy() {
    LOG.warn("Destructing filter :{}", this);
  }
}
