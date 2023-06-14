package com.web.websocket.controller;

import java.io.IOException;
import java.util.Set;

import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.web.websocket.vo.Message;

//@ServerEndpoint("/chatting")
@ServerEndpoint(value="/chatting",
			decoders= {JsonDecoder.class},	// json형식 데이터를 java 클래스로 변경
			encoders = {JsonEncoder.class} // java클래스를 json형식으로 변경
		)
public class ChattingServer {
	
	//private List<Session> clients =new ArrayList<Session>();
	
	@OnOpen
	public void open(Session session, EndpointConfig config){
		// 클라이언트가 접속 요청하면 실행되는 메소드
		System.out.println(session.getId());
		System.out.println("서버 접속");
		// clients.add(session); // 접속한 사용자에게 값을 전달하기 때문에 접속할 때마다 session을 저장한다.
		// 접속되어 있는지 확인해야함 -> 원래는 ,,, 다 따로 저장해서 확인했다 -> 현재는 제공되는 메소드 사용 ->getOpenSessions()
	}
	
	
	
	// js에서 socket.send(data)함수를 실행하면 실행되는 메소드
	// 클라이언트의 정보를 첫 번째 매개변수로 받고 -> session은 send한 사용자의 정보를 저장한다.
	// -> session에 대한 관리를 해야한다.
	// 클라이언트가 보낸 데이터를 send() 함수의 매개변수로, 두 번째 매개변수로 받는다.
	@OnMessage
	public void message(Session session, Message m) { // decoder, encoder class 만든 후 수정한 메소드
		System.out.println(m);
		switch(m.getType()) {
		case "접속" : addClient(session,m);break;
		case "채팅" : sendMessage(session,m);break;
		}
	}
	
//	public void message(Session session, String msg) {
//		
//		System.out.println(msg);
//		// json으로 보낸 객체 데이터 parsing하기 위해서 message vo 객체 생성
//		Message m=new Gson().fromJson(msg, Message.class);
//		System.out.println(m);
//		
//		// 
//		switch(m.getType()) {
//			case "접속" : addClient(session,m);break;
//			case "채팅" : sendMessage(session,m);break;
//		}
//		
////		// 접속한 session 가져올 수 있는 메소드 제공
////		Set<Session> clients= session.getOpenSessions(); // 열려있는 세션 값 다 가져온다.
////		System.out.println(clients.size());
////		
////		// 다른 사용자에게 보내지 않게 분기처리 해야한다. -> session에 구분자 필요
////		// 세션에 대한 구분자 값 저장하기(k:v형식)
////		session.getUserProperties().put("msg", msg); // 접속한 순간부터 계속 저장해야함 / session을 보낼 때마다 보낸 데이터를 저장함 -> parsing 해서 해당 receiver에게 전달한다 
////		
////		try {
////			// 접속한 사용자에게 받은 메세지 전달 -> 전달할 사용자를 지정해야한다.
////			// 보낼 사용자 지정하기 -> set 순회해서 접근하기
////			for(Session client:clients) {
////				client.getBasicRemote().sendText(msg);
////			}
////			// session.getBasicRemote().sendText(msg); (순회 하기 전)
////		}catch(IOException e) {
////			e.printStackTrace();
////		}
//	}
	
	// session을 구분할 수 있는 데이터 저장하는 기능
	// 접속한 사람만 저장
	private void addClient(Session session, Message msg) {
		session.getUserProperties().put("msg", msg); // session property에 접속한 사람 정보 저장
		sendMessage(session,Message.builder().type("알림").msg(msg.getSender()+"님이 접속하셨습니다").build()); // 접속한 사람에 대한 알림 전송
	}
	
	// decoder, encoder class 만든 후 수정한 메소드
	private void sendMessage(Session session, Message msg) {
		Set<Session> clients=session.getOpenSessions();
		try {
			if(msg.getReceiver()==null||msg.getReceiver().isBlank()) { // receiver가 없으면
				// 전체 접속자에게 전송
				for(Session client : clients) {
					client.getBasicRemote().sendObject(msg);
				}
			}else {
				// receiver에게 전송
				for(Session client : clients) {
					Message c=(Message)client.getUserProperties().get("msg");
					if(c.getSender().equals(msg.getReceiver())) {
						client.getBasicRemote().sendObject(msg);
					}
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}catch(EncodeException e) {
			e.printStackTrace();
		}
		
	}//
	
	
	// 접속한 client에게 메세지를 전송해주는 기능 (분기 처리 기준: receiver/ 다른 기준으로 분기처리할 수 있다)
//	private void sendMessage(Session session, Message msg) {
//		Set<Session> clients=session.getOpenSessions();
//		try {
//			if(msg.getReceiver()==null||msg.getReceiver().isBlank()) { // receiver가 없으면
//				// 전체 접속자에게 전송
//				for(Session client : clients) {
//					client.getBasicRemote().sendText(new Gson().toJson(msg));
//				}
//			}else {
//				// receiver에게 전송
//				for(Session client : clients) {
//					Message c=(Message)client.getUserProperties().get("msg");
//					if(c.getSender().equals(msg.getReceiver())) {
//						client.getBasicRemote().sendText(new Gson().toJson(msg));
//					}
//				}
//			}
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
//		
//	}//
	

}
