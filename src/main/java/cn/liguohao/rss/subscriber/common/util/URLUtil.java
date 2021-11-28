package cn.liguohao.rss.subscriber.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="http://liguohao.cn" target="_blank">liguohao</a>
 * @date 2021/11/28
 */
public class URLUtil extends StringUtil{
    private final static Logger LOGGER = LoggerFactory.getLogger(URLUtil.class);

    private final static String HTTP = "http";

    public static String parseDomain(String url) {
        String domain;
        isEmpty(url,"url is illegal, url: " + url);
        if(!url.contains(HTTP)) {
            throw new IllegalArgumentException("url is illegal, url: " + url);
        }
        int startIndex = url.indexOf("://");
        String subUrl = url.substring(startIndex + 3);
        domain = subUrl.substring(0, subUrl.indexOf("/"));
        return domain;
    }

}
