package cn.liguohao.rss.subscriber.common;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Http请求 简单封装
 *
 * @author <a href="http://liguohao.cn" target="_blank">liguohao</a>
 * @date 2021/10/23
 */
public class HttpUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

    public static String doGet(String uri) {
        URI uriObj = null;
        try {
            uriObj = new URIBuilder(uri).build();
        } catch (URISyntaxException e) {
            LOGGER.error("build URI instance fail, exception: ", e);
        }
        if(uriObj != null) {
            return doGet(uriObj);
        }
        return null;
    }

    public static String doGet(URI uri) {
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        String result = null;

        try {
            httpResponse = httpClient.execute(httpGet);
            switch (httpResponse.getStatusLine().getStatusCode() ) {
                case HttpStatus.SC_OK: {
                    HttpEntity entity = httpResponse.getEntity();
                    result = new String(entity.getContent().readAllBytes());
                    break;
                }
                default: {
                    LOGGER.warn("http request is not normal, httpResponse status code is {}", httpResponse.getStatusLine().getStatusCode());
                }
            }
        } catch (IOException exception) {
            LOGGER.error("http request fail by http client, exception: ", exception);
        } finally {
            try {
                if(httpResponse != null) {
                    httpResponse.close();
                }
            } catch (IOException e) {
                LOGGER.error("close http response fail, exception: ", e);
            }
        }
        return result;
    }

}
