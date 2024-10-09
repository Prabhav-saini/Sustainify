<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .container {
            width: 80%;
            margin: auto;
            overflow: hidden;
            padding: 20px;
            background: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
        }

        .profile-details {
            margin: 20px 0;
        }

        .btn {
            display: inline-block;
            padding: 10px 15px;
            background: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 10px;
        }

        .btn:hover {
            background: #0056b3;
        }

        .back-btn {
            background: #28a745; /* Green color for back button */
        }

        .back-btn:hover {
            background: #218838; /* Darker green on hover */
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>User Profile</h1>

        <!-- Display user details -->
        <div class="profile-details">
            <p><strong>First Name:</strong> ${user.firstName}</p>
            <p><strong>Last Name:</strong> ${user.lastName}</p>
            <p><strong>Email:</strong> ${user.email}</p>
            <p><strong>Mobile Number:</strong> ${user.mobileNumber}</p>
            <p><strong>Last Updated On:</strong> ${user.updatedDate}</p>
        </div>

        <!-- Link to update profile -->
        <a href="${pageContext.request.contextPath}/api/update-profile" class="btn">Update Profile</a>

        <!-- Back to home link -->
        <a href="${pageContext.request.contextPath}/api/home" class="btn back-btn">Back to Home</a>

        <!-- Logout link -->
        <a href="${pageContext.request.contextPath}/api/logout" class="btn">Logout</a>
    </div>
</body>
</html>
