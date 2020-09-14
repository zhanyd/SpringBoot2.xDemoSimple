package com.zhanyd.app.common.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpService {

	private static int readTimeout=25000;

	private static int connectTimeout=25000;

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpService.class);

	/**
	 * POST方法
	 * @param sendUrl
	 * @param sendParam
	 * @return
	 */
	public static String post(String sendUrl, String sendParam) {

		StringBuffer receive = new StringBuffer();
		BufferedWriter dos = null;
		BufferedReader rd = null;
		HttpURLConnection URLConn = null;
		LOGGER.info("sendUrl = " + sendUrl + " sendParam = " + sendParam);
		
		try {

			URL url = new URL(sendUrl);
			URLConn = (HttpURLConnection) url.openConnection();
			URLConn.setReadTimeout(readTimeout);
			URLConn.setConnectTimeout(connectTimeout);
			URLConn.setDoOutput(true);
			URLConn.setDoInput(true);
			URLConn.setRequestMethod("POST");
			URLConn.setUseCaches(false);
			URLConn.setAllowUserInteraction(true);
			URLConn.setInstanceFollowRedirects(true);

			URLConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

			if (sendParam != null && sendParam.length() > 0) {
				URLConn.setRequestProperty("Content-Length", String.valueOf(sendParam.getBytes().length));
				dos = new BufferedWriter(new OutputStreamWriter(URLConn.getOutputStream(), "UTF-8"));
				dos.write(sendParam);
				dos.flush();
			}

			rd = new BufferedReader(new InputStreamReader(URLConn.getInputStream(), "UTF-8"));
			String line;
			while ((line = rd.readLine()) != null) {
				receive.append(line);
			}

		} catch (java.io.IOException e) {
			receive.append("访问产生了异常-->").append(e.getMessage());
			e.printStackTrace();
		} finally {
			if (dos != null) {
				try {
					dos.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			
			if (rd != null) {
				try {
					rd.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			
			URLConn.disconnect();
		}

		String content = receive.toString();
		LOGGER.info("receiveContent = " + content);
		return content;
	}

	/**
	 * GET法
	 * @param sendUrl
	 * @return
	 */
	public static String get(String sendUrl) {

		StringBuffer receive = new StringBuffer();
		HttpURLConnection URLConn = null;
		BufferedReader in = null;
		try {
			URL url = new URL(sendUrl);
			URLConn = (HttpURLConnection) url.openConnection();

			URLConn.setDoInput(true);
			URLConn.connect();

			in = new BufferedReader(new InputStreamReader(URLConn.getInputStream(), "UTF-8"));

			String line;
			while ((line = in.readLine()) != null) {
				receive.append(line);
			}

		} catch (IOException e) {
			receive.append("访问产生了异常-->").append(e.getMessage());
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (java.io.IOException ex) {
					ex.printStackTrace();
				}
				in = null;
			}
			
			URLConn.disconnect();

		}

		String content = receive.toString();
		LOGGER.info("receiveContent = " + content);
		return content;
	}

	public static String post(String sendUrl) {
		return post(sendUrl, null);
	}
}
