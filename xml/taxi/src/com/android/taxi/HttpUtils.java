package com.android.taxi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;

@SuppressWarnings("unused")
public class HttpUtils {
    ///< 请求服务URL
        private final static String PATH = "http://10.0.0.123:8080/TaxiServlet/login";
        private static URL url; 
        
        public HttpUtils() {
            // TOiDO Auto-generated constructor stub
        }
        
        /**
         * 向服务端提交数据
         * @param params    url参数
         * @param encode    字节编码
         * @return
         */
        @SuppressWarnings("deprecation")
        public static int sendMessage(Map<String, String> params, String encode){
            ///< 初始化URL
            StringBuffer buffer = new StringBuffer();
//            StringBuffer buffer = new StringBuffer();
            
            if (null != params && !params.isEmpty()){
                for (Map.Entry<String, String> entry : params.entrySet()){
                    buffer.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue())).append("&");
                }
                ///< 删除多余的&
                buffer.deleteCharAt(buffer.length() - 1);
            }
            
            ///< show url
            //System.out.println(buffer.toString());
            
            try {
                url = new URL(PATH);
                if (null != url)
                {
                    HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                    if (null == urlConnection)
                    {
                        return -1;
                    }
                    urlConnection.setConnectTimeout(3000);
                    urlConnection.setRequestMethod("POST");    ///< 设置请求方式
                    urlConnection.setDoInput(true);            ///< 表示从服务器获取数据
                    urlConnection.setDoOutput(true);        ///< 表示向服务器发送数据

                    byte[] data = buffer.toString().getBytes();
                    ///< 设置请求体的是文本类型
                    urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    urlConnection.setRequestProperty("Content-Length", String.valueOf(data.length));
                    ///< 获得输出流
                    OutputStream outputStream = urlConnection.getOutputStream();
                    outputStream.write(data);
                    outputStream.close();
                    ///< 获得服务器的响应结果和状态码
                    int responseCode = urlConnection.getResponseCode();
                    //System.out.println("" + responseCode);
                    if (200 == responseCode)
                    {
                        return changeInputStream(urlConnection.getInputStream(), encode);
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            return -1;
        }
        
        /**
         * 获得网络返回值【0 - 正确    1 - 用户名错误    2 - 密码错误】
         * @param inputStream
         * @param encode
         * @return
         */
        private static int changeInputStream(InputStream inputStream, String encode) {
            // TODO Auto-generated method stub
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int len = 0;
            String result = "";
            if (null != inputStream)
            {
                try {
                    while ((len = inputStream.read(data)) != -1)
                    {
                        outputStream.write(data, 0, len);
                    }
                    result = new String(outputStream.toByteArray(), encode);
                    //System.out.println(result);
                    
                    len = Integer.parseInt(result.substring(0, 1));
                    
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return len;
        }
}