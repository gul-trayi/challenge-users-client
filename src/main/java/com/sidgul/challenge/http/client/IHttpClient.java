package com.sidgul.challenge.http.client;

import java.util.Map;

public interface IHttpClient {
    /***
     * Quick and dirty stand-in contract for the HTTP GET method
     * @param url URL to get
     * @param headers HTTP headers
     * @return response body as String
     * @throws HttpException wrapped with inner exception (if any) in case of any errors performing HTTP GET to the url
     */
    public String GET(String url, Map<String,String> headers) throws HttpException ;
}
