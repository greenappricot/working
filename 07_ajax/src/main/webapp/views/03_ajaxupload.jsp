<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax 파일 업로드 시키기</title>
<script src="http://code.jquery.com/jquery-3.7.0.min.js"></script>
</head>
<body>
	<h2>ajax를 이용해서 파일 업로드하기</h2>
	<input type="file" multiple id="upFile">
	<button id="uploadBtn">!UPLOAD!</button>
	<script>
		$("#uploadBtn").click(e=>{
			// js가 제공하는 FormData클래스 이용
			// FormData 생성 (form 태그와 유사)
			const form=new FormData();
			const fileInput=$("#upFile");

			// input type file로 파일을 업로드하면 files라는 속성으로 file이 배열 방식으로 업로드된다.
			console.log(fileInput);
			 // fileInput의 배열에 각각 파일을 저장한다.
			$.each(fileInput[0].files,(i,f)=>{
				// append로 서버에 전송할 데이터를 넣을 수 있다. (key:Value) 형식으로 저장
				form.append("upfile"+i,f);
			});
			form.append("name","유병승");
			$.ajax({
				url:"<%=request.getContextPath()%>/fileUpload",
				data:form,
				type:"post",
				//file을 전송할 때는 multipart/form으로 전송되어야하기 때문에 processData, contentType에 대한 설정이 필요하다
				processData:false,
				contentType:false,
				success:data=>{
					alert("success upload");
				},
				error:(r,m)=>{
					alert("failed upload"+m);
				},
				complete:()=>{
					$("#upFile").val(''); // file 업로드 후 파일 삭제
				}
			});
		});
	</script>
	
	<h2>업로드 이미지 미리보기 기능</h2>
	<img src="https://i.stack.imgur.com/l60Hf.png" width="100" height="100" id="profile">
	<input type="file" style="display:none" id="profileInput" accept="image/*">
	
	<input type="file" id="upfiles" multiple accept="image/*">
	<div id="uploadPreview"></div>
	
	<script>
		// 이미지 업로드하면 div에 출력하기
		$("#upfiles").change(e=>{
			
			const files=e.target.files;
			$.each(files,(i,f)=>{
				const reader=new FileReader();
				reader.onload=e=>{
					const img=$("<img>").attr({
						src:e.target.result,
						"width":"100",
						"height":"100"
					});
					//const img=$("<img>");
					//img.css("width","100");
					//console.log(e);
					//console.log(e.target);
					//console.log(e.target.result);
					//img.attr("src",e.target.result);
					//console.log(img);
				$("#uploadPreview").append(img);
				}
					reader.readAsDataURL(f); 								
			});
		});
	
		// 프로필이미지를 선택했을 때 이미지를 변경할 수 있는 파일을 선택하는 이벤트
		$("#profile").click(e=>{
			$("#profileInput").click();
		});
		
		// input의 이미지가 변경되었을 때 발생하는 이벤트
		$("#profileInput").change(e=>{
			const reader= new FileReader();
			// input 속성의 accept를 이용해서 원하는 형식의 파일만 받을 수 있다 -> 분기처리 필수 
			reader.onload=e=>{
				// e.target.result 속성에 변경된 파일이 나온다.
				$("#profile").attr("src",e.target.result);
			}
			reader.readAsDataURL(e.target.files[0]); // 읽은 data URL로 바꿔주는 함수
		});
	</script>
	<style>
		#profile{
			border-radius:100px;
			border: 3px solid gray;
			object-fit:cover;
		}
	</style>
	
	
</body>
</html>