<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int sum = 0;
	
	Cookie[] cookies = request.getCookies();
	
	for(Cookie c : cookies)
	{
		if(c.getName().equals("sum"))
		{
			sum = Integer.parseInt(c.getValue());
			break; // sum인 거만 받고 for문 탈출~(cookies는 여러개의 쿠키를 담기 때문에 내가 원하는 쿠키만 받아야됨)
		}
	}
	
	pageContext.setAttribute("sum",sum);
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		SUM(session) : ${sessionScope.sum}
	</div>
	
	<div>
		SUM(application) : ${applicationScope.sum}
	</div>
	
	<div>
		SUM(cookie) : ${sum}
	</div>
</body>
</html>