<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>websocket Test</title>
<script src="http://code.jquery.com/jquery-3.7.0.min.js"></script>
</head>
<body>
	<div id="container" style="margin-top:100px;">
		<input type="text" id="sender" placeholder="보내는 사람">
		 &nbsp;<button id="chatInBtn">채팅 접속</button><br>
		<input type="text" id="receiver" placeholder="받는 사람"><br>
		<input type="text" id="sendMsg" placeholder="내용" size="46"> &nbsp;<button id="sendBtn">전송</button>
	</div>
	<div id="msgContainer"></div>
	
	<script>
		// 웹소켓 서버에 접속하기
		// 1. 웹소켓 클래스 생성하기
		// 1-1. 주소 작성 
		// 		ws:// -> http 프로토콜을 이용할 때 사용한다.(기본)
		//		wss:// -> https 프로토콜 이용할 때 사용한다.
		
		// 접속하기 버튼 만들어서 session에 sender정보 저장하기
		let socket;
		$("#chatInBtn").click(e=>{
			socket=new WebSocket("ws://localhost:9090/<%=request.getContextPath()%>/chatting");
			
			// 2. 접속이 완료되면 실행되는 함수 등록
			socket.onopen=e=>{
				// websocket 서버와 접속이 완료되면 실행되는 함수
				//console.log(e);
				//socket.send("안녕");
				
				// ChattingServer 클래스 참고
				// session에 내 정보를 저장하기 (접속 현황, 접속자 )
				sendMsg(new Message("접속",$("#sender").val(),"","",""));
			};
			
			// 3. 서버에서 전송된 메세지를 처리하는 함수 등록
			socket.onmessage=e=>{
				// 접속된 웹소켓 서버에서 sendText() || sendObject()메소드를 실행 했을 때 실행되는 함수
				// 서버에서 보낸 데이터를 처리하는 함수
				console.log(e);
				
				// 서버에서 보낸 데이터는 매개변수 객체의 data 속성에 저장되어 있다. 
				//$("#msgContainer").append($("<p>").text(e.data));
				
				// 보낸 사용자 내용을 분기처리해서 출력할 수 있다. 
				/* const msg=e.data.split(",");
				const sup=$("<sup>").text(msg[0]); // 보낸 사용자 정보
				const span=$("<span>").text(msg[1]).css("fontSize","20px"); // 보낸 메세지
				const textContainer=$("<div>").append(sup).append(span);
				$("#msgContainer").append(textContainer); */
				
				// 클래스로 객체를 만들어서 저장했기 때문에 split으로 출력할 수 없다. 
				const sendData=JSON.parse(e.data); // -> json 방식으로 객체로 변환된다.
				console.log(sendData);
				// type에 따라 분기처리한다. 
				switch(sendData.type){
					case "채팅" : printData(sendData); break;
					case "알림" : printAlarm(sendData);break;
				}
				
			}
		})
		
		// 알림 메세지 출력되는 로직
		const printAlarm=(sendData)=>{
			const $h5=$("<h5>").text(sendData.msg).css("color","green");
			const $container=$("<div>").css({"display":"flex","justifyContent":"center"});
			$container.append($h5);
			$("#msgContainer").append($container);
		};
		
		// 채팅 메세지 출력되는 로직
		const printData=(sendData)=>{
			const $sup=$("<sup>").text(sendData.sender).css("marginRight","2%");
			const $text=$("<span>").text(sendData.msg).css("fontSize","20px");
			const $container=$("<div>").css("width","100%").append($sup).append($text);
			// 내가 보낸 채팅 메세지는 오른쪽, 상대방이 보낸 메세지는 왼쪽에 출력하는 메소드
			if(sendData.sender==$("#sender").val()){
				$container.css({
					"display":"flex",
					"justifyContent":"end",
					"backgroundColor":"gray"
				});
			}else {
				$container.css({
					"display":"flex",
					"justifyContent":"start"
				});
			}
			
			$("#msgContainer").append($container);
		}
		
		// 클릭하면 메세지 전송되는 이벤트 발생
		$("#sendBtn").click(e=>{
			const msg=$("#sendMsg").val();
			//sendMsg(msg);
			
			// 보낸 사용자 정보 보내기
			let sender= $("#sender").val(); // login한 session에서 가져와서 사용해도 된다
			//sendMsg(sender+","+msg);
			
			// 받는 사람 정보 보내기
			const receiver=$("#receiver").val();
			
			const sendData=new Message("채팅",sender,receiver,msg,"");
			sendMsg(sendData);
		});
		
		/* function sendMsg(msg){
			if(msg.length>0) socket.send(msg);
			else throw new Error("메세지가 비어있습니다");
		} */
		
		// 문자열로 변환해서 json패턴으로 데이터 전달하는 전역 메소드
		function sendMsg(msg){
			socket.send(JSON.stringify(msg));
		}
		
		// msg배열보다 k:v 형식으로 된 js 객체로 저장하는 편이 좋다 
		// => java에서 toJson() json으로, js에서 fromJson()으로 객체 전환해서 사용할 수 있다
		// type : 채팅, 사용 현황, 알림 
		// sender : 보낸 사람
		// receiver: 받는 사람
		// msg : 전송되는 데이터 
		// room : 채팅방에 대한 정보
		
		// 클래스 생성
		class Message{
			constructor(type, sender,receiver,msg,room){
				this.type=type;
				this.sender=sender;
				this.receiver=receiver;
				this.msg=msg;
				this.room=room;				
			}
		}
		
		
		
	</script>
</body>
</html>








