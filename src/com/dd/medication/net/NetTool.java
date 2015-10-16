package com.dd.medication.net;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import android.util.Log;

public class NetTool {

	/**
	 * 获取文本内容
	 * @param inStream 输入流
	 * @param encoding 字符编码
	 * @return
	 * @throws Exception
	 */
	public static String getTextContent(String urlPath){
		byte[] data = null;
		try {
			data = readStream(getContent(urlPath));
			return new String(data, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 获取url路径指定的内容
	 * @param urlpath url路径
	 * @throws Exception
	 */
	public static InputStream getContent(String urlpath){
		try {
			URL url = new URL(urlpath);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("POST");
//			conn.setRequestMethod("GET");
			conn.setConnectTimeout(6*1000);
			if(conn.getResponseCode() == 200){
				Log.d("print","-------"+conn.getInputStream().available());
				return conn.getInputStream();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 读取数据
	 * @param inStream 输入流
	 * @return
	 * @throws Exception
     */
	public static byte[] readStream(InputStream inStream) throws Exception{
		if(inStream ==null){
			return null;
		}
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while( (len = inStream.read(buffer)) != -1){
			outStream.write(buffer, 0, len);
		}
		outStream.close();
		inStream.close();
		return outStream.toByteArray();
	}
	
	/**
	 * 获取url路径指定的网页代码
	 * @param urlpath url路径
	 * @throws Exception
	 */
	public static String getHtml(String urlpath, String encoding){
		try {
			URL url = new URL(urlpath);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(6*1000);
			if(conn.getResponseCode() == 200){
				InputStream inStream = conn.getInputStream();
				byte[] data = readStream(inStream);
				return new String(data, encoding);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取给定Url路径的数据
	 * @param urlpath Url路径
	 * @return
	 * @throws Exception
	 */
	public static byte[] getImage(String urlpath) throws Exception{
		URL url = new URL(urlpath);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(6*1000);
		if(conn.getResponseCode() == 200){
			InputStream inStream = conn.getInputStream();
			return readStream(inStream);
		}
		return null;
	}


	/**
	 * 发送Post请求
	 * @param urlpath 请求路径
	 * @param params 请求参数, key为请求参数名称，value为参数值
	 * @return
	 * @throws Exception
	 */
	public static InputStream sendXmlRequest(String urlpath, byte[] xmldata, String encoding) throws Exception{
		URL url = new URL(urlpath);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setConnectTimeout(6*1000);
		conn.setDoOutput(true);//发送POST请求必须设置允许输出
		conn.setUseCaches(false);//不使用Cache
		conn.setRequestMethod("POST");	        
		conn.setRequestProperty("Connection", "Keep-Alive");//维持长连接
		conn.setRequestProperty("Charset", encoding);
		conn.setRequestProperty("Content-Length", String.valueOf(xmldata.length));
		conn.setRequestProperty("Content-Type","text/xml; charset="+ encoding);
		DataOutputStream dataOutStream = new DataOutputStream(conn.getOutputStream());
		dataOutStream.write(xmldata);
		dataOutStream.flush();
		dataOutStream.close();
		if(conn.getResponseCode() == 200){
			return conn.getInputStream();
		}
		return null;
	}
	/**
	 * 发送Post请求
	 * @param urlpath 请求路径
	 * @param params 请求参数, key为请求参数名称，value为参数值
	 * @return
	 * @throws Exception
	 */
	public static InputStream sendPostRequest(String urlpath, Map<String, String> params, String encoding) throws Exception{
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<String, String> entry : params.entrySet()){
			sb.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), encoding));
			sb.append('&');
		}
		sb.deleteCharAt(sb.length() - 1);
		byte[] data = sb.toString().getBytes();
		URL url = new URL(urlpath);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setConnectTimeout(6*1000);
		conn.setDoInput(true);
		conn.setDoOutput(true);//发送POST请求必须设置允许输出
		conn.setUseCaches(false);//不使用Cache		
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Connection", "Keep-Alive");//维持长连接
		conn.setRequestProperty("Charset", encoding);
		conn.setRequestProperty("Content-Length", String.valueOf(data.length));
		conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		DataOutputStream dataOutStream = new DataOutputStream(conn.getOutputStream());
		dataOutStream.write(data);
		dataOutStream.flush();
		dataOutStream.close();
		if(conn.getResponseCode() == 200){
			return conn.getInputStream();
		}
		return null;
	}	

	/**
	 * 获取给定Url路径的文件数据
	 * @param urlpath Url路径
	 * @return
	 * @throws Exception
	 */
	public static byte[] getFile(String urlpath){
		try {
			URL url = new URL(URLEncoder.encode(urlpath));
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(6*1000);
			if(conn.getResponseCode() == 200){
				InputStream inStream = conn.getInputStream();
				return readStream(inStream);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 发送请求
	 * @param path 请求路径
	 * @param params 请求参数 key为参数名称 value为参数值
	 * @param encode 请求参数的编码
	 */
	public static InputStream post(String path, Map<String, String> params, String encode, int timeout){
		//String params = "method=save&name="+ URLEncoder.encode("老毕", "UTF-8")+ "&age=28&";//需要发送的参数
		try {
			StringBuilder parambuilder = new StringBuilder("");
			if(params!=null && !params.isEmpty()){
				for(Map.Entry<String, String> entry : params.entrySet()){
					parambuilder.append(entry.getKey()).append("=")
						.append(URLEncoder.encode(entry.getValue(), encode)).append("&");
				}
				parambuilder.deleteCharAt(parambuilder.length()-1);
			}
			byte[] data = parambuilder.toString().getBytes();
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setDoOutput(true);//允许对外发送请求参数
			conn.setUseCaches(false);//不进行缓存
			conn.setConnectTimeout(timeout);
			conn.setReadTimeout(timeout);
			conn.setRequestMethod("POST");
			//下面设置http请求头
			conn.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
			conn.setRequestProperty("Accept-Language", "zh-CN");
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", String.valueOf(data.length));
			conn.setRequestProperty("Connection", "Keep-Alive");
			
			//发送参数
			DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
			outStream.write(data);//把参数发送出去
			outStream.flush();
			outStream.close();
			if(conn.getResponseCode()==200){
				return conn.getInputStream();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 发送请求
	 * @param path 请求路径
	 * @param params 请求参数 key为参数名称 value为参数值
	 * @param encode 请求参数的编码
	 */
	public static InputStream postFile(String path, Map<String, String> params, String encode, int timeout, File file){
		
		// TODO 改成POST方式，并且一些HTTP参数未加上
		try {
			StringBuilder parambuilder = new StringBuilder("");
			if(params!=null && !params.isEmpty()){
				for(Map.Entry<String, String> entry : params.entrySet()){
					parambuilder.append(entry.getKey()).append("=")
						.append(URLEncoder.encode(entry.getValue(), encode)).append("&");
				}
				parambuilder.deleteCharAt(parambuilder.length()-1);
			}
			String data = parambuilder.toString();
			URL url = new URL(path + data);
			System.out.println(url.toString());
			//		URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setDoOutput(true);//允许对外发送请求参数
			conn.setUseCaches(false);//不进行缓存
			conn.setConnectTimeout(timeout);
			conn.setReadTimeout(timeout);
			conn.setRequestMethod("POST");

			String twoHyphens = "--";
			String lineEnd = "\r\n";
			String boundary = "-----------------------------7d318fd100112";
			
			//下面设置http请求头
			conn.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
			conn.setRequestProperty("Accept-Language", "zh-CN");
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
			conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
//		conn.setRequestProperty("Content-Length", String.valueOf(data.length));
			conn.setRequestProperty("Connection", "Keep-Alive");

			
			//发送参数
			DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
			
 
			
			if (file.exists()) 
			{
//			outStream.writeBytes("Content-Disposition: form-data; name=\"upload\";"
//					+ " filename=\"" + file.getName() + "\"" + lineEnd);
				FileInputStream fis = new FileInputStream(file);  
				byte[] buffer = new byte[1024];
				while(true){  
					synchronized(buffer){  
						int  amountRead = fis.read(buffer);  
						if(amountRead==-1){
				             break;  
						}
						outStream.write(buffer, 0, amountRead);
					}
				}
				fis.close();
			}
//		outStream.writeBytes(boundary + lineEnd);
			outStream.flush();
			outStream.close();
			if(conn.getResponseCode()==200){
				return conn.getInputStream();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 发送请求
	 * @param path 请求路径
	 * @param params 请求参数 key为参数名称 value为参数值
	 * @param encode 请求参数的编码
	 */
	public static InputStream get(String path, Map<String, String> params, String encode, int timeout){
		//String params = "method=save&name="+ URLEncoder.encode("老毕", "UTF-8")+ "&age=28&";//需要发送的参数
		try {
			StringBuilder parambuilder = new StringBuilder("");
			if(params!=null && !params.isEmpty()){
				for(Map.Entry<String, String> entry : params.entrySet()){
					parambuilder.append(entry.getKey()).append("=")
						.append(URLEncoder.encode(entry.getValue(), encode)).append("&");
				}
				parambuilder.deleteCharAt(parambuilder.length()-1);
			}
			String data = parambuilder.toString();
			URL url = new URL(path + data);
			System.out.println(url.getPath());
			System.out.println(url.toString());
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setDoOutput(true);//允许对外发送请求参数
			conn.setUseCaches(false);//不进行缓存
			conn.setConnectTimeout(timeout);
			conn.setReadTimeout(timeout);
			conn.setRequestMethod("GET");
			//下面设置http请求头
			conn.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
			conn.setRequestProperty("Accept-Language", "zh-CN");
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", String.valueOf(data.getBytes().length));
			conn.setRequestProperty("Connection", "Keep-Alive");
			
			if(conn.getResponseCode()==200){
				return conn.getInputStream();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取文本内容
	 * @param inStream 输入流
	 * @param encoding 字符编码
	 * @return
	 * @throws Exception
	 */
	public static String getTextContent(InputStream inStream, String encoding){
		byte[] data;
		try {
			data = readStream(inStream);
			return new String(data, encoding);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	 * @param urlString
	 * @return
	 */
	public static boolean checkNet(String urlString){
		boolean flag = false;
		URL url = null;
		Log.i("checkNet", urlString);
		try {
			url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(4 * 1000);
			if (connection.getResponseCode() == 200) {
				flag = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
}
