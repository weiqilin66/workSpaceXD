<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>$Title$</title>
</head>
<body>
<table>
    <tr>
        <td>名字</td>
        <td>邮箱</td>
        <td>年龄</td>
    </tr>
    <#list list as u>
        <tr>
            <td>${u.name}</td>
            <td>${u.email}</td>
            <td>${u.age}</td>
        </tr>
    </#list>
</table>
</body>
</html>