<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Success</title>

<style>
body {
    font-family: Arial;
    margin: 20px;
}

.msg {
    padding: 10px;
    background-color: #d4edda;
    color: #155724;
    width: fit-content;
    border-radius: 5px;
}

.section-title {
    margin-top: 25px;
    font-size: 18px;
    font-weight: bold;
}

table {
    margin-top: 10px;
    border-collapse: collapse;
    width: 70%;
}

th, td {
    border: 1px solid #000;
    padding: 10px;
}

th {
    background-color: #f2f2f2;
}

.btn {
    margin-top: 20px;
    padding: 10px 15px;
    background-color: red;
    color: white;
    text-decoration: none;
    display: inline-block;
    border-radius: 5px;
}
</style>

</head>
<body>


<h2 class="msg">${message}</h2>

<div class="section-title">Newly Registered Courses</div>

<c:if test="${not empty newCourses}">
    <table>
        <tr>
            <th>Course Name</th>
            
        </tr>

        <c:forEach var="c" items="${newCourses}">
            <tr>
                <td>${c.name}</td>
            </tr>
        </c:forEach>

    </table>
</c:if>

<c:if test="${empty newCourses}">
    <p>No new courses registered.</p>
</c:if>

<div class="section-title">All Registered Courses</div>

<c:if test="${not empty previousCourses}">
    <table>
        <tr>
            <th>Course Name</th>
            <th>Instructor</th>
            <th>Credits</th>
            <th>Date</th>
        </tr>

        <c:forEach var="c" items="${previousCourses}">
            <tr>
                <td>${c.name}</td>
                <td>${c.instructor}</td>
                <td>${c.credits}</td>
                <td>${c.date}</td>
            </tr>
        </c:forEach>

    </table>
</c:if>

<c:if test="${empty previousCourses}">
    <p>No previous registrations.</p>
</c:if>

<a class="btn" href="${pageContext.request.contextPath}/logout">
    Logout
</a>

</body>
</html>