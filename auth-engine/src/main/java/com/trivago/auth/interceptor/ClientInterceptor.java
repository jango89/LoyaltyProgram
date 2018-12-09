package com.trivago.auth.interceptor;

import com.trivago.auth.controller.AuthController;
import com.trivago.auth.domain.PolicyName;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class ClientInterceptor extends HandlerInterceptorAdapter {

  private final AuthController authController;

  public ClientInterceptor(AuthController authController) {
    this.authController = authController;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    final String policyName = authController.getPolicyName(request);
    response.addHeader("X-AUTH-POLICY", policyName);
    return super.preHandle(request, response, handler);
  }
}
