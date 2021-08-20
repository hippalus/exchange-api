package com.hippalus.exchangeapi.infra.common.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class ValidatorConfiguration implements WebMvcConfigurer {

  private final Validator defaultValidator;

  @Override
  public Validator getValidator() {
    return defaultValidator;
  }
}