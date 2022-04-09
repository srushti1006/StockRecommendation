<html>
<body bgcolor="wheat">
<h1>Login page!!!
${SPRING_SECURITY_LAST_EXCEPTION.message}<!--To print credential message-->

<form action="login" method="post">
Username<input type="text" name="username"><br>
Password<input type="password" name="password"><br>
<input type="submit" value="submit">

</body>
</html>
