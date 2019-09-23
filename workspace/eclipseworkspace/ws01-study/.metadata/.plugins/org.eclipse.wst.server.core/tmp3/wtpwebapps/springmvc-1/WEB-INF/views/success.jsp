<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>success</title>
</head>
<body>

<h2>success</h2>
${requestScope.time}
<hr>
${requestScope.name}
<hr>
session user:${sessionScope.user }
<hr>
request user: ${requestScope.user }
</body>
</html>