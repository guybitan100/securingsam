package com.reputation.rest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;
import java.security.cert.X509Certificate;

public class RestClient {
    final static Logger log4j = Logger.getLogger(RestClient.class);
    private String base_url;
    private final RestTemplate rest;
    private final HttpHeaders headers;
    private HttpStatus status;


    public RestClient() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
        headers.add("User-Agent", "PostmanJava");
        headers.add("Connection", "keep-alive");
    }

    public RestClient(String base_url) {
        this();
        this.base_url = base_url;
    }

    public void addHeader(String key, String value) {
        this.headers.add(key, value);
    }

    public  String post(String uri, String json) {
        String retValue = postEntity(uri, json).getBody() + "";
        log4j.debug("uri: " + uri + " response...");
        log4j.debug(retValue);
        return retValue;
    }

    public ResponseEntity postBase() {
        return postEntity("", "");
    }

    public  ResponseEntity postEntity(String uri) {
        return postEntity(uri, "");
    }

    public  ResponseEntity postEntity(String uri, String json) {
        HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
        ResponseEntity<String> responseEntity = rest.exchange(base_url + uri, HttpMethod.POST, requestEntity, String.class);
        status = responseEntity.getStatusCode();
        return responseEntity;
    }

    public synchronized static void disableSslVerification() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            HostnameVerifier allHostsValid = (hostname, session) -> true;
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (Exception e) {
            log4j.debug(e);
        }
    }

    public HttpStatus getStatus() {
        return status;
    }
}