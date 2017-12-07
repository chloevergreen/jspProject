package com.april.ssl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class DeerClient {

	public static void main(String[] args) {
		try {
			
			//소켓 팩토리 생성
			SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
			
			//만들어둔 server keystore 파일을 임의의 폴더로 옮기고 아래와 같이 설정해준다. (클라이언트에도 key store 파일이 있어야함)
			System.setProperty("javax.net.ssl.keyStore", "\\");
			
			//password는 keystore 만들때 입력한것
			System.setProperty("javax.net.ssl.keyStorePassword", "dkssud123");
			
			//디버깅을 위한 설정
			System.setProperty("javax.net.debug", "ssl");
			
			System.out.println("&&&&& keyStore : " + System.getProperty("javax.net.ssl.keyStore"));
			System.out.println("&&&&& trustStore : " + System.getProperty("javax.net.ssl.trustStore"));
			
			//소켓에 서버의 IP와 포트 번호를 입력한다.
			SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket("192.168.0.20", 1115);
			
			//서버로 보낼 파일의 경로와 이름
			String dir = "/D:/bomyiStudy/JSP/jspProjectServer/src/test";
			String fileName = "test.txt";
			
			InputStream inputstream = new FileInputStream(new File(dir, fileName));
			BufferedReader br = new BufferedReader(new InputStreamReader(inputstream));
			
			OutputStream outputstream = sslsocket.getOutputStream();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputstream));
			
			String str = null;
			
			while((str = br.readLine()) != null) {
				bw.write(str + '\n');
				bw.flush();
			}
		
		} catch (FileNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (UnknownHostException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
