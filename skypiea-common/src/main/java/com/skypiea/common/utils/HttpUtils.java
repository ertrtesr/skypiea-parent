package com.skypiea.common.utils;

import com.skypiea.common.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-22 14:45
 */
public class HttpUtils {

    public static String doGet(String urlPath, Map<String, String> params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";

        CloseableHttpResponse response = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(urlPath);
            if (params != null) {
                for (String key : params.keySet()) {
                    uriBuilder.addParameter(key, params.get(key));
                }
            }
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(urlPath);
            // 执行请求
            response = httpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == HttpStatus.OK) {
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            ExceptionUtils.getStackTrace(e);
        } finally {
            close(httpClient, response);
        }
        return result;
    }

    public static String doGet(String urlPath) {
        return doGet(urlPath, null);
    }

    public static String doPost(String urlPath, Map<String, String> params) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = "";

        try {
            //创建http Post请求
            HttpPost httpPost = new HttpPost(urlPath);
            //创建参数列表
            if (params != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : params.keySet()) {
                    paramList.add(new BasicNameValuePair(key, params.get(key)));
                }
                //模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }
            //执行请求
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(httpClient, response);
        }
        return result;
    }

    public static String doPost(String urlPath) {
        return doPost(urlPath, null);
    }

    public static String doPostJson(String urlPath, String json) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = "";

        try {
            HttpPost httpPost = new HttpPost(urlPath);
            StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(httpClient, response);
        }
        return result;
    }

    private static void close(CloseableHttpClient httpClient, CloseableHttpResponse response) {
        try {
            if (response != null) {
                response.close();
            }
            httpClient.close();
        } catch (IOException e) {
            ExceptionUtils.getStackTrace(e);
        }
    }
}
