package com.hippalus.exchangeapi.infra.common.rest;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public final class HttpHeaderUtil {

  private HttpHeaderUtil() {
  }

  @NotNull
  public static HttpHeaders defaultHttpHeader() {
    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
    return headers;
  }
}
