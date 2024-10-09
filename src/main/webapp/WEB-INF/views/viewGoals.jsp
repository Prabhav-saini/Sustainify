<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Your Goals</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 800px;
            margin: auto;
            background: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .back-btn {
            display: inline-block;
            margin: 20px 0;
            padding: 10px 15px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .back-btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Your Goals</h1>
        <table>
            <thead>
                <tr>
                    <th>Description</th>
                    <th>Created Date</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Achieved</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="goal" items="${goals}">
                    <tr>
                        <td>${goal.description}</td>
                        <td>${goal.createdDate}</td>
                        <td>${goal.startDate}</td>
                        <td>${goal.endDate}</td>
                        <td>${goal.achieved ? 'Yes' : 'No'}</td>
                    </tr>
                </c:forEach>
                <c:if test="${empty goals}">
                    <tr>
                        <td colspan="5" style="text-align:center;">No goals found.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
        <a href="${pageContext.request.contextPath}/api/home" class="btn back-btn">Back to Home</a>
    </div>
</body>
</html>
