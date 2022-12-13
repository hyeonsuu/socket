package com.test.api;
import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ServerSocketTest {
	
			//클라이언트 연결 받는 소켓.
			ServerSocket serverSocket;
			
			// 실제 통신 소켓
			Socket server;
			
			// 클라이언트 -> 서버로 온 데이터 읽기
			BufferedReader br;
			
			// @EventListener : 프로젝트가 실행 됨과 동시에 serverSocket 포트를 오픈해준다.
			@EventListener(ApplicationReadyEvent.class)
		    public void init() {
		    	
		    	System.out.println("1. 서버 소켓 시작.");
		    	
		    	try {
		    		// 연결하고 싶은 포트 번호를 입력해준다.
		    		serverSocket = new ServerSocket(9090);

		    		System.out.println("2. 서버 소켓 생성 완료.");
		    		
		    		// 클라이언트와 연결
		    		server = serverSocket.accept();
		    		System.out.println("3. 클라이언트가 연결되었습니다. " + server.getPort());
		    		
		    		try {
		    			br = new BufferedReader(new InputStreamReader(server.getInputStream()));
		        		System.out.println("4. 서버 버퍼 연결 완료.");
		        		
		        			// 클라이언트로부터 메시지를 읽는다
		        			String msg = br.readLine();
		        			if (msg != null) {
		        				System.out.println("5. 클라이언트로부터 받은 메세지 출력 : " + msg);
		        			} else {
		        				System.out.println("클라이언트로부터 들어온 데이터가 없습니다.");
		        			}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if(serverSocket != null) { serverSocket.close(); System.out.println("serverSocket close.");}
						if(server != null) { server.close();  System.out.println("server close.");}
					}
		    	} catch (Exception e) {
					System.out.println("서버 소켓 에러 발생 : " + e.getMessage());
				} 
		    }
			
}
