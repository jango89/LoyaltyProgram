package com.trivago.auth.controller;

import com.trivago.auth.domain.PolicyName;
import com.trivago.auth.usecase.FindByApiKey;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authorize")
public class AuthController {

  private final FindByApiKey findByApiKey;

  public AuthController(FindByApiKey findByApiKey) {
    this.findByApiKey = findByApiKey;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public String getPolicyName(HttpServletRequest httpServletRequest) {
    return findByApiKey.execute(httpServletRequest.getHeader("AUTHORIZATION"))
        .toString();
  }
}
