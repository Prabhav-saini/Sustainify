<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Activities</title>
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
        <h1>User Activities</h1>
        <table>
            <thead>
                <tr>
                    <th>Activity Type</th>
                    <th>Date Logged</th>
                    <th>Amount</th>
                    <th>Amount Unit</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="activity" items="${activities}">
                    <tr>
                        <td>${activity.type}</td>
                        <td>${activity.dateLogged}</td>
                        <td>${activity.amount}</td>
                        <td>${activity.amountUnit}</td>
                    </tr>
                </c:forEach>
                <c:if test="${empty activities}">
                    <tr>
                        <td colspan="4" style="text-align:center;">No activities found.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
        <a href="${pageContext.request.contextPath}/api/home" class="btn back-btn">Back to Home</a>
    </div>
</body>
</html>
