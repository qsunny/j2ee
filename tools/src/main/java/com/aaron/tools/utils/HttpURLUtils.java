package com.aaron.tools.utils;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

public final class HttpURLUtils {
	
	private static final Logger log = LoggerFactory.getLogger(HttpURLUtils.class);
	
    public static String doPost(String reqUrl, Map<String, String> parameters) {
        HttpURLConnection urlConn = null;
        try {
            urlConn = sendPost(reqUrl, parameters);
            String responseContent = getContent(urlConn);
            return responseContent.trim();
        } finally {
            if (urlConn != null) {
                urlConn.disconnect();
                urlConn = null;
            }
        }
    }
    
    public static void doPost(String reqUrl) {
        HttpURLConnection urlConn = null;
        try {
            urlConn = sendPost(reqUrl);
        } finally {
            if (urlConn != null) {
                urlConn.disconnect();
                urlConn = null;
            }
        }
    }

    public static String doUploadFile(String reqUrl, Map<String, String> parameters,
            String fileParamName, String filename, String contentType, byte[] data) {
        HttpURLConnection urlConn = null;
        try {
            urlConn = sendFormdata(reqUrl, parameters, fileParamName, filename, contentType, data);
            String responseContent = new String(getBytes(urlConn));
            return responseContent.trim();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            if (urlConn != null) {
                urlConn.disconnect();
            }
        }
    }

    private static HttpURLConnection sendFormdata(String reqUrl, Map<String, String> parameters,
            String fileParamName, String filename, String contentType, byte[] data) {
        HttpURLConnection urlConn = null;
        try {
            URL url = new URL(reqUrl);
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestMethod("POST");
            urlConn.setConnectTimeout(5000);// 锛堝崟浣嶏細姣锛塲dk
            urlConn.setReadTimeout(5000);// 锛堝崟浣嶏細姣锛塲dk 1.5鎹㈡垚杩欎釜,璇绘搷浣滆秴鏃�
            urlConn.setDoOutput(true);

            urlConn.setRequestProperty("connection", "keep-alive");

            String boundary = "-----------------------------114975832116442893661388290519"; // 鍒嗛殧绗�
            urlConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            boundary = "--" + boundary;
            StringBuffer params = new StringBuffer();
            if (parameters != null) {
                for (Iterator<String> iter = parameters.keySet().iterator(); iter.hasNext();) {
                    String name = iter.next();
                    String value = parameters.get(name);
                    params.append(boundary + "\r\n");
                    params.append("Content-Disposition: form-data; name=\"" + name + "\"\r\n\r\n");
                    // params.append(URLEncoder.encode(value, "UTF-8"));
                    params.append(value);
                    params.append("\r\n");
                }
            }

            StringBuilder sb = new StringBuilder();
            // sb.append("\r\n");
            sb.append(boundary);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data; name=\"" + fileParamName + "\"; filename=\""
                    + filename + "\"\r\n");
            sb.append("Content-Type: " + contentType + "\r\n\r\n");
            byte[] fileDiv = sb.toString().getBytes();
            byte[] endData = ("\r\n" + boundary + "--\r\n").getBytes();
            byte[] ps = params.toString().getBytes();

            OutputStream os = urlConn.getOutputStream();
            os.write(ps);
            os.write(fileDiv);
            os.write(data);
            os.write(endData);

            os.flush();
            os.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return urlConn;
    }

    public static String getContent(HttpURLConnection urlConn) {
        try {
            String responseContent = null;
            InputStream in = urlConn.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String tempLine = rd.readLine();
            StringBuffer tempStr = new StringBuffer();
            String crlf = System.getProperty("line.separator");
            while (tempLine != null) {
                tempStr.append(tempLine);
                tempStr.append(crlf);
                tempLine = rd.readLine();
            }
            responseContent = tempStr.toString();
            rd.close();
            in.close();
            return responseContent;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static byte[] getBytes(String reqUrl, Map<String, String> parameters) {
        HttpURLConnection conn = sendPost(reqUrl, parameters);
        return getBytes(conn);
    }
    
    private static byte[] getBytes(HttpURLConnection urlConn) {
        try {
            InputStream in = urlConn.getInputStream();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int i = 0; (i = in.read(buf)) > 0;)
                os.write(buf, 0, i);
            in.close();
            return os.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static HttpURLConnection sendPost(String reqUrl, Map<String, String> parameters) {
        HttpURLConnection urlConn = null;
        try {
            String params = generatorParamString(parameters);
            URL url = new URL(reqUrl);
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestMethod("POST");
            // urlConn
            // .setRequestProperty(
            // "User-Agent",
            // "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.3) Gecko/20100401 Firefox/3.6.3");
            urlConn.setConnectTimeout(5000);// 锛堝崟浣嶏細姣锛塲dk
            urlConn.setReadTimeout(5000);// 锛堝崟浣嶏細姣锛塲dk 1.5鎹㈡垚杩欎釜,璇绘搷浣滆秴鏃�
            urlConn.setDoOutput(true);
            byte[] b = params.getBytes();
            urlConn.getOutputStream().write(b, 0, b.length);
            urlConn.getOutputStream().flush();
            urlConn.getOutputStream().close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return urlConn;
    }
    
    private static HttpURLConnection sendPost(String reqUrl) {
        HttpURLConnection urlConn = null;
        try {
            URL url = new URL(reqUrl);
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestMethod("GET");
            // urlConn
            // .setRequestProperty(
            // "User-Agent",
            // "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.3) Gecko/20100401 Firefox/3.6.3");
            urlConn.setConnectTimeout(5000);// 锛堝崟浣嶏細姣锛塲dk
            urlConn.setReadTimeout(5000);// 锛堝崟浣嶏細姣锛塲dk 1.5鎹㈡垚杩欎釜,璇绘搷浣滆秴鏃�
            urlConn.setDoOutput(true);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return urlConn;
    }

    /**
     * 灏唒arameters涓暟鎹浆鎹㈡垚鐢�&"閾炬帴鐨刪ttp璇锋眰鍙傛暟褰㈠紡
     * 
     * @param parameters
     * @return
     */
    public static String generatorParamString(Map<String, String> parameters) {
        StringBuffer params = new StringBuffer();
        if (parameters != null) {
            for (Iterator<String> iter = parameters.keySet().iterator(); iter.hasNext();) {
                String name = iter.next();
                String value = parameters.get(name);
                params.append(name + "=");
                try {
                    params.append(URLEncoder.encode(value, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e.getMessage(), e);
                } catch (Exception e) {
                    String message = String.format("'%s'='%s'", name, value);
                    throw new RuntimeException(message, e);
                }
                if (iter.hasNext()) params.append("&");
            }
        }
        return params.toString();
    }

    /**
     * 
     * @param link
     * @param charset
     * @return
     */
    public static String doGet(String link, String charset) {
        try {
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int i = 0; (i = in.read(buf)) > 0;) {
                out.write(buf, 0, i);
            }
            out.flush();
            String s = new String(out.toByteArray(), charset);
            return s;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * UTF-8缂栫爜
     * 
     * @param link
     * @return
     */
    public static String doGet(String link) {
        return doGet(link, "UTF-8");
    }

    public static int getIntResponse(String link) {
        String str = doGet(link);
        return Integer.parseInt(str.trim());
    }
    
	/** 
     * 发起https请求并获取结果 
     *  
     * @param requestUrl 请求地址 
     * @param requestMethod 请求方式（GET、POST） 
     * @param outputStr 提交的数据 
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值) 
     */  
    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {  
        JSONObject jsonObject = null;  
        StringBuffer buffer = new StringBuffer();  
        try {  
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化  
            TrustManager[] tm = { new MyX509TrustManager() };  
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");  
            sslContext.init(null, tm, new java.security.SecureRandom());  
            // 从上述SSLContext对象中得到SSLSocketFactory对象  
            SSLSocketFactory ssf = sslContext.getSocketFactory();  
  
            URL url = new URL(requestUrl);  
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();  
            httpUrlConn.setSSLSocketFactory(ssf);  
  
            httpUrlConn.setDoOutput(true);  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setUseCaches(false);  
            // 设置请求方式（GET/POST）  
            httpUrlConn.setRequestMethod(requestMethod);  
  
            if ("GET".equalsIgnoreCase(requestMethod))  
                httpUrlConn.connect();  
  
            // 当有数据需要提交时  
            if (null != outputStr) {  
                OutputStream outputStream = httpUrlConn.getOutputStream();  
                // 注意编码格式，防止中文乱码  
                outputStream.write(outputStr.getBytes("UTF-8"));  
                outputStream.close();  
            }  
  
            // 将返回的输入流转换成字符串  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
  
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  
//            JSONValue jsonValue = new JSONValue();
            JSONObject jsonValue = new JSONObject();
            jsonValue = jsonValue.fromObject(buffer.toString());
            //jsonObject = (JSONObject)jsonValue.parse(buffer.toString()); 
            log.info(jsonObject.toString());
        } catch (ConnectException ce) {
            //ce.printStackTrace();
            log.error("",ce);

        } catch (Exception e) {
            //e.printStackTrace();
            log.error("https request error:{}", e);
        }  
        return jsonObject;  
    }
    
    /** 
     * 发起http请求并获取结果 
     *  
     * @param requestUrl 请求地址 
     * @param requestMethod 请求方式（GET、POST） 
     * @param outputStr 提交的数据 
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值) 
     */  
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {  
        JSONObject jsonObject = null;  
        StringBuffer buffer = new StringBuffer();  
        try {  
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化  
//            TrustManager[] tm = { new MyX509TrustManager() };  
//            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");  
//            sslContext.init(null, tm, new java.security.SecureRandom());  
//            // 从上述SSLContext对象中得到SSLSocketFactory对象  
//            SSLSocketFactory ssf = sslContext.getSocketFactory();  
  
            URL url = new URL(requestUrl);  
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();  
           // httpUrlConn.setSSLSocketFactory(ssf);  
  
            httpUrlConn.setDoOutput(true);  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setUseCaches(false);  
            // 设置请求方式（GET/POST）  
            httpUrlConn.setRequestMethod(requestMethod);  
  
            if ("GET".equalsIgnoreCase(requestMethod))  
                httpUrlConn.connect();  
  
            // 当有数据需要提交时  
            if (null != outputStr) {  
                OutputStream outputStream = httpUrlConn.getOutputStream();  
                // 注意编码格式，防止中文乱码  
                outputStream.write(outputStr.getBytes("UTF-8"));  
                outputStream.close();  
            }  
  
            // 将返回的输入流转换成字符串  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
  
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  
//            JSONValue jsonValue = new JSONValue();
            JSONObject jsonValue = new JSONObject();
            jsonValue = jsonValue.fromObject(buffer.toString());
            //jsonObject = (JSONObject)jsonValue.parse(buffer.toString()); 
            log.info(jsonObject.toString());
        } catch (ConnectException ce) {  
            log.error("Weixin server connection timed out.");  
        } catch (Exception e) {  
            log.error("https request error:{}", e);  
        }  
        return jsonObject;  
    }
}

