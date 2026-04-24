<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>

<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f4f6f8;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.login-box {
    background: white;
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0 2px 15px rgba(0,0,0,0.1);
    width: 300px;
}

h2 {
    text-align: center;
    color: #333;
    margin-bottom: 20px;
}

input[type="text"],
input[type="password"] {
    width: 100%;
    padding: 10px;
    margin: 8px 0 15px 0;
    border: 1px solid #ccc;
    border-radius: 5px;
}

button {
    width: 100%;
    padding: 10px;
    background-color: #007bff;
    border: none;
    color: white;
    font-weight: bold;
    border-radius: 5px;
    cursor: pointer;
}

button:hover {
    background-color: #0056b3;
}

.error {
    color: red;
    text-align: center;
    margin-bottom: 10px;
}
</style>

</head>

<body>

<div class="login-box">

    <h2>Login</h2>

    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <p class="error"><%= error %></p>
    <%
        }
    %>

    <form action="login" method="post">

        <label>Email:</label>
        <input type="text" name="email" required>

        <label>Password:</label>
        <input type="password" name="password" required>

        <button type="submit">Login</button>

    </form>

</div>

</body>
</html>