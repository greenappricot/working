package com.bs.helloboot.websocket;

import java.util.HashMap;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ChattingServer extends TextWebSocketHandler{
	// pom.xml에서 websocket 등록
	// TextWebSocketHandler를 extends 한 chattingServcer 클래스 생성
	// MyWebMvcConfiguration 클래스에서 websocket 연결
	
	
	private Map<String, WebSocketSession> clients= new HashMap<String, WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.debug("접속완료");
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// TODO Auto-generated method stub
		super.handleTextMessage(session, message);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// TODO Auto-generated method stub
		super.handleMessage(session, message);
	}

	
	
}
