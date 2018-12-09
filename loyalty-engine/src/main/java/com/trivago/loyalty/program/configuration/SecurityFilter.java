package com.trivago.loyalty.program.configuration;

import com.trivago.loyalty.program.gateway.ClientInterface;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

@Component
public class SecurityFilter extends AbstractPreAuthenticatedProcessingFilter {

  private final ClientInterface clientInterface;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    SecurityContextHolder.getContext().setAuthentication(null);
    try {
      super.doFilter(request, response, chain);
    } catch (Exception e) {

      HttpServletResponse res = (HttpServletResponse) response;
      java.io.PrintWriter wr = res.getWriter();
      res.setStatus(HttpStatus.BAD_GATEWAY.value());
      wr.print(res);
      wr.flush();
      wr.close();
    }

  }

  @Override
  protected boolean principalChanged(HttpServletRequest request,
      Authentication currentAuthentication) {
    return true;
  }

  public SecurityFilter(ClientInterface clientInterface) {
    this.clientInterface = clientInterface;
    setAuthenticationManager(authentication -> {
      final Object policy = authentication.getPrincipal();
      authentication.setAuthenticated(true);
      //BOTH PRIVATE AND PUBLIC API KEY IS ALLOWED
      return authentication;
    });
  }

  @Override
  public void destroy() {

  }

  @Override
  protected Object getPreAuthenticatedPrincipal(HttpServletRequest httpServletRequest) {
    return clientInterface
        .retrievePolicy(httpServletRequest.getHeader("AUTHORIZATION"));
  }

  @Override
  protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
    return "N/A";
  }

}
