<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <script src="https://cdn.iamport.kr/v1/iamport.js"></script> -->
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
</head>
<body>
<svg id="wish" xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                            viewBox="0 0 24 24" fill="none" stroke="black"
                            stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                            class="feather feather-heart">
                            <path
                                d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z">
                            </path>
                        </svg>
<!-- <button onclick="requestPay()">결제하기</button> -->

<script>
	const h=document.getElementById("wish");
	//console.log(h);
	h.addEventListener("click",e=>{
		let flag=false;
		if(!flag){
			$(e.target).css("fill","black");
			flag=true;
		}else{
			flag=false;
		}
	})
	//document.getElementById("wish").click(e=>{
		//document.getElementById("wish");
		//$(e.target).css("fill","black");
	//})
/* const userCode = "";
IMP.init(userCode);

function requestPay() {
  IMP.request_pay({
    pg: "",
    pay_method: "",
    merchant_uid: "test_lise0e3y",
    name: "",
    amount: 0,
    buyer_name: "",
    buyer_tel: "",
    buyer_email: "",
  });
} */
</script>
</body>
</html>