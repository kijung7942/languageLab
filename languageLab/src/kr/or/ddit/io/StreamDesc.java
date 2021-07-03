package kr.or.ddit.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Stream : 연속성을 가진 일련의 데이터 집합이면서 동시에 전송 통로(단방향)
 *
 * 스트림의 종류
 * 1. 데이터의 전송 데이터 크기(종류)에 따른 분류
 * 	1) byte 단위의 byteStream : XXXInputStream // XXXOutputStream
 * 	2) char 단위의 charStream : XXXReader // XXXWriter
 * 2. 스트림의 생성 방법 및 필터링 여부
 * 	1) 1차 스트림 : 매체를 대상으로 직접적으로 생성하는 Stream.
 * 		- FileInputStream(file), SocketInputStream si = socket.getInputStream()
 * 	2) 2차(연결형) 스트림 : 1차 스트림을 대상으로 생성하는 Stream.
 * 		- BufferedReader, DataInputStream, ObjectInputStream(직렬화가 가능한 객체만 가능)
 * 		- BufferedWriter, DataOutputStream, ObjectOutputStream(직렬화가 가능한 객체만 가능)
 *  
 * 3. 스트림 사용 단계
 * 	1) 매체(media)를 어플리케이션 내에서 제어할 수 있도록 객체화
 * 	2) 매체를 대상으로 1차 스트림 생성.
 * 	3) (optional) 데이터를 필터링 할 수 있는 2차 스트림 생성.
 *  4) 기록이나 읽어들이는 작업 반복(EOF:-1,null 을 만날때까지)
 * 	5) 자원의 해제(finally, try-with-resource)
 *  
 */

public class StreamDesc {
	public static void main(String[] args) throws Exception {
		URL imageURL = new URL("https://www.google.com/logos/doodles/2021/get-vaccinated-wear-a-mask-save-lives-june-22-6753651837109280-law.gif");
		File saveFile = new File("E:/google.gif");
		int pointer = -1;
		byte[] buffer = new byte[1024];
		try(
			InputStream is = imageURL.openStream();
			FileOutputStream fos = new FileOutputStream(saveFile);
		) {
			while((pointer = is.read(buffer)) != -1) {
				fos.write(buffer, 0, pointer);
			}
			fos.flush();
			System.out.println("다운로드 완료");
		}
	}
	
	private static void serialize() throws Exception{
		// 객체의 (역)직렬화
//		TestVO vo = new TestVO(23, "text");
//		vo.setRegno1("2323232");
		File writeFile = new File("E:/test.dat");
//		try(
//				FileOutputStream fos = new FileOutputStream(writeFile);
//				ObjectOutputStream oos = new ObjectOutputStream(fos);
//		){
//			oos.writeObject(vo);
//		}
		try(
				FileInputStream fis = new FileInputStream(writeFile);
				ObjectInputStream ois = new ObjectInputStream(fis);
				){
			// deserialization(역직렬화)
			TestVO readData = (TestVO)ois.readObject();
			System.out.println(readData.toString());
		}
		
	}
	
	private static void readClassPathResource() throws Exception {
		URL url = StreamDesc.class.getResource("오래된 노래.txt");
//		URL url = StreamDesc.class.getResource("오래된 노래_utf8.txt");
		String filePath = URLDecoder.decode(url.getFile(),"utf-8");
//		System.out.println(url.getFile());
		System.out.println(filePath);
		if(url != null) {
			File readFile = new File(filePath);
//			System.out.println(readFile.getAbsolutePath());
			try(
//				FileReader reader = new FileReader(readFile);
					FileInputStream fis = new FileInputStream(readFile);
					//1차 스트림(byte stream)과 3차 스트림(char stream)을 연결해주는 중간 stream에서 charset 설정 가능
					InputStreamReader reader = new InputStreamReader(fis, "ms949");
					BufferedReader br = new BufferedReader(reader);
					){
				String tmp = null;
				while((tmp = br.readLine()) != null) {
					System.out.println(tmp);
				}
			}
		}
		
	}
	
	@SuppressWarnings("unused")
	private static void readFileSystemResource() throws IOException {
		File readFile = new File("E:/contents/another day.txt");
		System.out.println(readFile.length());
		try(
				FileInputStream fis = new FileInputStream(readFile);
				){
			byte[] buffer = new byte[1024];
			int pointer = -1;
			while((pointer = fis.read(buffer)) != -1) {
				System.out.write(buffer, 0, pointer);
			}
		}
	}
}
