package com.hippalus.exchangeapi.infra.adapters.conversion.rest.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConditionalOnProperty(name = "adapters.conversion.enabled", havingValue = "true")
@ConfigurationProperties(prefix = "adapters.conversion")
public class ConversionApiProperties {

  private String accessKey;
  private String baseUrl;
  private String path;
}
