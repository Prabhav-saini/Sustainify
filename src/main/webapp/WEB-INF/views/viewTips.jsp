<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<html>
<head>
    <title>Tips For You</title>
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
        .comment-btn {
            margin: 0;
            padding: 5px 10px;
            font-size: 14px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .comment-btn:hover {
            background-color: #218838;
        }
    </style>
    <script>
        function submitComment(tipId) {
            let commentContent = prompt("Enter your comment:");

            if (commentContent) {
                $.ajax({
                    url: `${pageContext.request.contextPath}/api/submit/comment`,
                    type: 'POST',
                    data: { tipId: tipId, content: commentContent },
                    success: function() {
                        alert('Comment submitted successfully!');
                        location.reload();
                    },
                    error: function() {
                        alert('Failed to submit comment.');
                    }
                });
            }
        }
    </script>
</head>
<body>
    <div class="container">
        <h1>Your Tips</h1>
        <table>
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Content</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="tip" items="${tips}">
                    <tr>
                        <td>${tip.title}</td>
                        <td>${tip.content}</td>
                        <td>
                            <button class="comment-btn" onclick="submitComment(${tip.id})">
                                Submit Comment
                            </button>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty tips}">
                    <tr>
                        <td colspan="3" style="text-align:center;">No tips available.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
         <a href="${pageContext.request.contextPath}/api/home" class="btn back-btn">Back to Home</a>
    </div>
</body>
</html>
