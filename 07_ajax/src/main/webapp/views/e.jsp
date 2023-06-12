<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
</head>
<body>

<button onclick="requestPay()">결제하기</button>

<script>
const userCode = "";
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
}
</script>
</body>
</html>