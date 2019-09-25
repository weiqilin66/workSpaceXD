<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<script src="${ctx}/webjars/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
function requestJson(){

    var jsonData = {  
            "name" : "手机",  
            "price" : "999"  
    };
    $.ajax({
        type:'post',
        url:'${pageContext.request.contextPath }/requestJson.action',
        contentType:'application/json;charset=utf-8',//指定为json类型
        //数据格式是json串，商品信息
        data:JSON.stringify(jsonData),
        success:function(data){//返回json结果
            alert(data.name);
        }       
    }); 
}
</script>
<body>
<h2>HOME</h2>
<br>
<a href="helloworld"> helloworld</a>
<hr>
<hr>
<a href="test/params?username=joy&age=11"> testparams</a>
<hr>
<h4>rest风格</h4>
<hr>
<br>
<form action="rest/1" method="get">
<input type="submit" value="get">
</form>
<form action="rest" method="post">
<input type="submit" value="post">
</form>
<form action="rest/3" method="post">
<input  type="hidden" name="_method" value="put">
<input type="submit" value="put">
</form>
<form action="rest/4" method="post">
<input  type="hidden" name="_method" value="delete">
<input type="submit" value="delete">
</form>
<br>
<form action="param1">
<input type="text" value="wayne" name="username">
<input type="submit" value="param1">
</form>
<a href="param1"> param1.2</a>

<br>
<a href="testRequestHeader"> testRequestHeader</a>
<br>
<hr>
<a href="pojo?username=joy&password=123&address.city=fujian"> testPojo</a>
<br>

<hr>
<a href="servlet"> testservlet</a>

<br>
<hr>
<a href="model1"> testmodel1</a>
<br>
<hr>
<a href="model2"> testmodel2</a>
<br>
<hr>
<a href="model3"> testmodel3</a>
<br>
<a href="sessionAttributes"> testsessionAttributes</a>
<hr>
<br>
<form action="testModelAttributes">
<input type="hidden" name="id" value="1">
用户名:<input name="username" type="text">
<input type="submit" value="submit">
</form>
<hr>
<br>
<hr>
<br>
<hr>
<br>
</body>
</html>