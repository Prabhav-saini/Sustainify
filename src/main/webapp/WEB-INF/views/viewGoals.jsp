<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
        .achieved-btn {
            margin: 0;
            padding: 5px 10px;
            font-size: 14px;
        }
        .achieved {
            background-color: #28a745; /* Green for achieved */
            color: white;
            cursor: not-allowed; /* Indicate button is disabled */
        }
        .achieved:hover {
            background-color: #218838; /* Darker green on hover */
        }
        .not-achieved {
            background-color: #007bff; /* Blue for not achieved */
            color: white;
        }
        .not-achieved:hover {
            background-color: #0056b3; /* Darker blue on hover */
        }
    </style>
    <script>
        function markAchieved(goalId) {
            if (confirm('Are you sure you want to mark this goal as achieved?')) {
                $.ajax({
                    url: `${pageContext.request.contextPath}/api/mark/goal/achieved`,
                    type: 'POST',
                    data: { goalId: goalId },
                    success: function() {
                        location.reload(); // Reload the page to reflect changes
                    },
                    error: function() {
                        alert('Failed to mark goal as achieved.');
                    }
                });
            }
        }
    </script>
</head>
<body>
    <div class="container">
        <h1>Your Goals</h1>
        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Description</th>
                    <th>Created Date</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Achieved</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="goal" items="${goals}">
                    <tr>
                        <td>${goal.id}</td>
                        <td>${goal.description}</td>
                        <td>${goal.createdDate}</td>
                        <td>${goal.startDate}</td>
                        <td>${goal.endDate}</td>
                        <td>${goal.achieved ? 'Yes' : 'No'}</td>
                        <td>
                            <button
                                class="achieved-btn ${goal.achieved ? 'achieved' : 'not-achieved'}"
                                <c:if test="${!goal.achieved}">
                                    onclick="markAchieved(${goal.id})"
                                </c:if>
                                ${goal.achieved ? 'disabled' : ''}>
                                ${goal.achieved ? 'Achieved' : 'Mark Achieved'}
                            </button>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty goals}">
                    <tr>
                        <td colspan="7" style="text-align:center;">No goals found.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
        <a href="${pageContext.request.contextPath}/api/home" class="btn back-btn">Back to Home</a>
    </div>
</body>
</html>
