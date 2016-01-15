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
    ///< �������URL
        private final static String PATH = "http://10.0.0.123:8080/TaxiServlet/login";
        private static URL url; 
        
        public HttpUtils() {
            // TOiDO Auto-generated constructor stub
        }
        
        /**
         * �������ύ����
         * @param params    url����
         * @param encode    �ֽڱ���
         * @return
         */
        @SuppressWarnings("deprecation")
        public static int sendMessage(Map<String, String> params, String encode){
            ///< ��ʼ��URL
            StringBuffer buffer = new StringBuffer();
//            StringBuffer buffer = new StringBuffer();
            
            if (null != params && !params.isEmpty()){
                for (Map.Entry<String, String> entry : params.entrySet()){
                    buffer.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue())).append("&");
                }
                ///< ɾ�������&
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
                    urlConnection.setRequestMethod("POST");    ///< ��������ʽ
                    urlConnection.setDoInput(true);            ///< ��ʾ�ӷ�������ȡ����
                    urlConnection.setDoOutput(true);        ///< ��ʾ���������������

                    byte[] data = buffer.toString().getBytes();
                    ///< ��������������ı�����
                    urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    urlConnection.setRequestProperty("Content-Length", String.valueOf(data.length));
                    ///< ��������
                    OutputStream outputStream = urlConnection.getOutputStream();
                    outputStream.write(data);
                    outputStream.close();
                    ///< ��÷���������Ӧ�����״̬��
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
         * ������緵��ֵ��0 - ��ȷ    1 - �û�������    2 - �������
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