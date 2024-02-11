package com.sidgul.challenge.http.client;

import com.sidgul.challenge.users.client.UsersClientError;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class HttpClient implements IHttpClient{
    private static final Logger logger = LoggerFactory.getLogger(HttpClient.class);
    /***
     * A simple implementation for abstracting the HTTP get method
     * @param url URL to get
     * @param headers HTTP headers
     * @return response body as String
     * @throws HttpException wrapped with inner exception (if any) in case of any errors performing HTTP GET to the url
     */
    @Override
    public String GET(String url, Map<String, String> headers) throws HttpException {
        // Validate URL
        try {
            URL apiUrl = new URL(url);
        } catch (MalformedURLException malformedURLException) {
            HttpException invalidUrlError = new HttpException("Error while HTTP GET - Malformed URL " + url, malformedURLException);
            logger.error(invalidUrlError.getMessage(), invalidUrlError);
            throw (invalidUrlError);
        }

        String responseBody = null;
        try {
            logger.debug("HTTP GET to URL " + url);
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet request = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(request);

            if (response.getCode() == HttpStatus.SC_OK) {
                logger.debug("successful HTTP status response from URL " + url);
                responseBody = EntityUtils.toString(response.getEntity());
            } else {
                HttpException httpRequestUnsuccessful = new HttpException(String.format("Failed HTTP GET to URL %s - got HTTP status %d", url, response.getCode()));
                logger.error(httpRequestUnsuccessful.getMessage(), httpRequestUnsuccessful);
                throw (httpRequestUnsuccessful);
            }
        } catch (IOException ioException) {
            throw new HttpException(String.format("IO Error while HTTP GET to  URL %s. See inner error for details.", url), ioException);
        } catch (ParseException parseException) {
            throw new HttpException(String.format("Error parsing while HTTP GET to URL %s. See inner error for details.", url), parseException);
        }
        return responseBody;
    }

}
