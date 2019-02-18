package com.ruzhen.service.util;

        import org.apache.http.HttpEntity;
        import org.apache.http.HttpHost;
        import org.apache.http.HttpResponse;
        import org.apache.http.NameValuePair;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.config.RequestConfig;
        import org.apache.http.client.methods.CloseableHttpResponse;
        import org.apache.http.client.methods.HttpPost;
        import org.apache.http.client.methods.HttpUriRequest;
        import org.apache.http.client.methods.RequestBuilder;
        import org.apache.http.conn.routing.HttpRoute;
        import org.apache.http.entity.ContentType;
        import org.apache.http.entity.mime.MultipartEntityBuilder;
        import org.apache.http.entity.mime.content.FileBody;
        import org.apache.http.entity.mime.content.StringBody;
        import org.apache.http.impl.client.CloseableHttpClient;
        import org.apache.http.impl.client.HttpClientBuilder;
        import org.apache.http.impl.client.HttpClients;
        import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
        import org.apache.http.message.BasicNameValuePair;
        import org.apache.http.util.EntityUtils;

        import java.io.File;
        import java.io.IOException;
        import java.util.*;

public class HttpClientUtils {
    private static PoolingHttpClientConnectionManager clientConnectionManager = null;
    private static HttpClientBuilder httpClientBuilder = null;
    private static RequestConfig requestConfig = null;

    private static int MAX_CONNECTION = 10;
    private static int DEFAULT_MAX_CONNECTION = 5;
    private static String IP = "cnivi.com.cn";
    private static int PORT = 80;
    static{
        //设置http的状态参数
        requestConfig = RequestConfig.custom().setConnectionRequestTimeout(5000)
                                              .setSocketTimeout(5000)
                                              .setConnectTimeout(5000)
                                              .build();
        HttpHost target = new HttpHost(IP, PORT);
        clientConnectionManager = new PoolingHttpClientConnectionManager();
        clientConnectionManager.setMaxTotal(MAX_CONNECTION);  //客户端总并行链接最大数
        clientConnectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_CONNECTION); //每个主机的最大并行链接数
        clientConnectionManager.setMaxPerRoute(new HttpRoute(target), 20);
        httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setConnectionManager(clientConnectionManager);
    }

    public static CloseableHttpClient getConnection(){
        CloseableHttpClient httpClient = httpClientBuilder.build();
        return httpClient;
    }

    public static HttpUriRequest getRequestMethod(Map<String, String> map, String url, String method){
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        Set<Map.Entry<String,String>> entrySet = map.entrySet();
        for(Map.Entry<String, String> e:entrySet){
            String name = e.getKey();
            String value = e.getValue();
            NameValuePair pair = new BasicNameValuePair(name, value);
            params.add(pair);
        }
        HttpUriRequest reqMethod = null;
        if("post".equals(method)){
            reqMethod = RequestBuilder.post().setUri(url).addParameters(params.toArray(new BasicNameValuePair[params.size()]))
                    .setConfig(requestConfig).build();
        }else{
            reqMethod = RequestBuilder.get().setUri(url).addParameters(params.toArray(new BasicNameValuePair[params.size()]))
                    .setConfig(requestConfig).build();
        }
        return reqMethod;
    }

    //文件上传
    public void upload(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try{

            HttpPost httpPost = new HttpPost("http://localhost:8080/myDemo/Ajax/serivceFile");
            FileBody bin = new FileBody(new File(""));
            StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);
            HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("bin", bin).addPart("comment", comment).build();
            httpPost.setEntity(reqEntity);
            System.out.println("executing request " + httpPost.getRequestLine());
            try (
                    CloseableHttpResponse response = httpClient.execute(httpPost);
            ) {

                System.out.println("------------------------");
                System.out.println(response.getStatusLine());
                try {
                    HttpEntity resEntity = response.getEntity();
                    if (resEntity != null) {
                        System.out.println("Response content length: " + resEntity.getContentLength());
                    }
                    EntityUtils.consume(resEntity);
                } finally {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }finally {
            try{
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("account", "");
        map.put("password", "");

        HttpClient client = getConnection();
        HttpUriRequest post = getRequestMethod(map, "http://cnivi.com.cn/login", "post");
        HttpResponse response = client.execute(post);
        if(response.getStatusLine().getStatusCode() == 200){
            HttpEntity resEntity = response.getEntity();
            String message = EntityUtils.toString(resEntity);
            System.out.println(message);
        }else{
            System.out.println("请求失败 "+response.getStatusLine().getStatusCode() );
        }
    }

}
