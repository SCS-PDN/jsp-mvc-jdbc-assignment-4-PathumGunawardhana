<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Courses</title>

<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f4f6f8;
    margin: 0;
    padding: 30px;
}

h2 {
    color: #333;
    margin-bottom: 20px;
}

table {
    width: 70%;
    border-collapse: collapse;
    background-color: white;
    box-shadow: 0 2px 10px rgba(0,0,0,0.1);
}

th {
    background-color: #007bff;
    color: white;
    padding: 12px;
    text-align: left;
}

td {
    padding: 12px;
    border-bottom: 1px solid #ddd;
}

button {
    padding: 8px 14px;
    border: none;
    cursor: pointer;
    border-radius: 5px;
    font-weight: bold;
}

button:hover {
    opacity: 0.9;
}

form[action*="register"] button {
    background-color: #28a745;
    color: white;
}

form[action*="finish"] button {
    background-color: #007bff;
    color: white;
}

form[action*="logout"] button {
    background-color: #dc3545;
    color: white;
}

form {
    display: inline-block;
    margin-right: 10px;
    margin-top: 15px;
}
</style>

</head>
<body>

<h2>Available Courses</h2>

<table>

<tr>
    <th>Course Name</th>
    <th>Action</th>
</tr>

<%
    List<Map<String, Object>> courses =
        (List<Map<String, Object>>) request.getAttribute("courses");

    if (courses != null && !courses.isEmpty()) {

        for (Map<String, Object> c : courses) {
%>

<tr>
    <td><%= c.get("name") %></td>
    <td>

        <form action="<%= request.getContextPath() %>/register/<%= c.get("id") %>" method="post">
            <button type="submit">Register</button>
        </form>

    </td>
</tr>

<%
        }

    } else {
%>

<tr>
    <td colspan="2">No courses found</td>
</tr>

<%
    }
%>

</table>

<br><br>

<form action="<%= request.getContextPath() %>/finish" method="get">
    <button type="submit">Finish Registration</button>
</form>

<form action="<%= request.getContextPath() %>/logout" method="get">
    <button type="submit">Logout</button>
</form>

</body>
</html>