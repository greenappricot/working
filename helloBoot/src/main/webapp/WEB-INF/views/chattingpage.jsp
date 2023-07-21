<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>chatting page</title>
</head>
<body>
	<script>
		const websocket = new WebSocket("ws://localhost:9090/chatting");
		websocket.onopen=data=>{
			console.log(data);
		}
	</script>
</body>
</html>