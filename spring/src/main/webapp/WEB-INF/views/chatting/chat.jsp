<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅페이지</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>

<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

<script src="${pageContext.request.contextPath }/resources/js/jquery-3.7.0.min.js"></script>
</head>
<body>
	<div class="container-fluid">
		<h2>Chatting</h2>
		<div id="accessMember"></div>
		<div id="chattingcontainer"></div>
		<div class="row">
			<div class="col-xm-10">
				<input class="form-control" type="text" id="msg">
			</div>
			<div class="col-xm-2">
				<button class="btn btn-outline-danger" id="send">전송</button>
			</div>
		</div>
	</div>
	<script>
		const me = '${loginMember.userId}';
		const server=new WebSocket("ws://localhost:8080/spring/chatting");
		server.onopen=data=>{
			server.send(JSON.stringify(new Message("open", me)));
		}
		server.onmessage=data=>{
			console.log(data);
			const msg=JSON.parse(data.data);
			console.log(msg);
			switch(msg.type){
				case "msg" : printMessage(msg); break; //msg 객체가 전달됨
				case "system" : printSystemMessage(msg.msg); break; // msg의 msg 내용만 전달된다.
			}
		}
		
		$("#send").click(e=>{
			const msg= $("#msg").val();
			server.send(JSON.stringify(new Message("msg", me,"",msg,"","")));
		});
		
		function printSystemMessage(msg){
			$("#accessMember").html(""); // 계속 추가되면 안되니까 비워준다.
			const m = $("<h6>").text("접속한 멤버 " +msg);
			$("#accessMember").append(m);
			/* msg.split(",").forEach(e=>{
				const m = $("<h6>").text(e);
				$("#accessMember").append(m);
			}) */
		}
		
		server.onclose=data=>{
			
		}
		
		function printMessage(msg){
			const msgContainer = $("<div>");
			const sender = $("<h6>").text(msg.sender).css("text-align", msg.sender==me?"right":"left");
			const content = $("<h4>").text(msg.msg).css("text-align", msg.sender==me?"right":"left");
			msgContainer.append(sender).append(content);
			$("#chattingcontainer").append(msgContainer);
		}
		/* const sendTime= new Date().toLocaleDateString("ko-KO");
		console.log(sendTime); */
		class Message{
			constructor(type="",sender="",receiver="",msg="",room=""){
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