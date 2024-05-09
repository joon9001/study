package pack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Net4TestServer {

	public static void main(String[] args) {
		// 단순 서버
		ServerSocket ss = null;
		
		//내 컴퓨터가 사용 중인 port number 확인
//		for (int i = 0; i < 65536; i++) {
//			try {
//				ss = new ServerSocket(i);
//				ss.close();
//			} catch (Exception e) {
//				System.out.println(i + "번 port는 사용중");
//			}
//			
//		}
//		System.out.println("확인 종료");
		
		Socket socket = null; // TCP/IP 기반의 통신용 클래스 파일
		try {
			ss = new ServerSocket(9999); 
			// 서버 소켓, 9999는 현재 내컴퓨터가 안쓰는 포트번호
			System.out.println("server start...");
			socket = ss.accept(); 
			//서버는 대기중이다가 클라이언트가 접속하면 new socket이 생성되어 socket에 저장됨
			//서버 소켓으로부터 클라이언트 컴과 통신하기 위한 개별 소켓 생성
			
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			String data = reader.readLine();
			System.out.println("receive : " + data);
			
			reader.close();
			socket.close();
			ss.close();
		} catch (Exception e) {
			System.out.println("server err: " + e);
		}
	}
	

}
