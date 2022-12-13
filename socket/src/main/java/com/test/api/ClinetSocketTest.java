package com.test.api;

import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClinetSocketTest {
	
	// 서버로 보낼 데이터
	private static final String jsonData = "{\"name\" : \"hyeon\", \"age\" : \"00\", \"number\" : \"000000000\"}" + "\r\n";

	public static void main(String[] args) {
		
		System.out.println("[client socket] 접속 test ");

		  Socket socket = null;
		  
		try{
			socket = new Socket();

			socket.connect(new InetSocketAddress("localhost", 9090));
			System.out.println("[client] server와 연결 성공");
			
			// 클라이언트로 메시지를 보낸다.
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			pw.println(jsonData);
			pw.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
              try {
                 if(socket != null) { socket.close(); System.out.println("client socket close.");}
              } catch(Exception e) {
                  e.printStackTrace();
              }
          }
		
	}
}
