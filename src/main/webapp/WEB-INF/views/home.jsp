<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sustainify - Eco Living</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
            color: #333;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        /* Header */
        header {
            background-color: #00796b;
            color: #fff;
            padding: 10px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 100%;
        }

        header .app-name {
            font-size: 24px;
            font-weight: bold;
            text-align: center;
            margin: 0 auto;
        }

        header .slogan {
            font-size: 14px;
            margin-top: 4px;
            font-style: italic;
        }

        /* Profile dropdown */
        .profile {
            position: absolute;
            left: 1cm;
            display: inline-block;
        }

        .username-display {
            color: #00796b;
            font-weight: bold;
            background-color: #f4f4f9;
            padding: 5px 16px;
            display: block;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        .profile button {
            background-color: #4caf50;
            color: #fff;
            padding: 10px;
            font-size: 16px;
            border: none;
            cursor: pointer;
            border-radius: 5px;
        }

        .profile button:hover {
            background-color: #45a049;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            right: 0;
            background-color: #fff;
            min-width: 200px; /* Increased width */
            max-width: 250px; /* Optional max width */
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            z-index: 1;
            overflow-wrap: break-word;
        }

        .dropdown-content a {
            color: #333;
            padding: 10px 16px;
            text-decoration: none;
            display: block;
        }

        .dropdown-content a:hover {
            background-color: #ddd;
        }

        .profile:hover .dropdown-content {
            position: absolute;
            left: 1cm;
            display: block;
        }

        /* Main container */
        .container {
            display: flex;
            justify-content: space-between;
            width: 90%;
            margin: 20px 0;
            flex-wrap: wrap;
        }

        .box {
            background-color: #e0f2f1;
            padding: 20px;
            border-radius: 8px;
            margin: 10px;
            flex-basis: 45%;
            text-align: center;
        }

        .box h2 {
            font-size: 20px;
            margin-bottom: 10px;
        }

        .log-section {
            margin-top: 10px;
        }

        .log-section input {
            width: 80%;
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }

        .log-button, .view-button {
            background-color: #00796b;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 10px;
        }

        .log-button:hover, .view-button:hover {
            background-color: #00695c;
        }

        /* Tips section */
        .tips {
            width: 90%;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            margin: 10px 0;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .tips h2 {
            font-size: 20px;
            margin-bottom: 10px;
        }

        .tip-form input {
            width: 80%;
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }
    </style>
</head>
<body>
<header>
    <div class="profile">
        <button>Profile</button>
        <div class="dropdown-content">
            <!-- Username with new style -->
            <a class="username-display" href="http://localhost:8080/sustainify/api/view-profile">${username}</a>
            <a href="http://localhost:8080/sustainify/api/update-profile">Update Profile</a>
            <a href="http://localhost:8080/sustainify/api/logout">Log Out</a>
        </div>
    </div>
    <div class="app-name">
        Sustainify<br>
        <span class="slogan">Eco Living</span>
    </div>
</header>

<div class="container">
    <div class="box">
        <h2>Log Activity</h2>
        <form action="log-activity" method="post">
            <div class="log-section">
                <input type="text" name="type" placeholder="Enter Activity Type (e.g., Recycling)" required />
                <input type="number" name="amount" placeholder="Amount (e.g., 5)" required />
                <input type="text" name="amountUnit" placeholder="Unit (e.g., kg, liters)" required />
                <button type="submit" class="log-button">Log Activity</button>
            </div>
            <button class="view-button" onclick="location.href='http://localhost:8080/sustainify/api/view/activities'">View Activities</button>
        </form>
    </div>

    <div class="box">
        <h2>Log Goal</h2>
        <form action="log-goal" method="post">
            <div class="log-section">
                <input type="text" name="description" placeholder="Goal Title (e.g., Reduce Plastic Use)" required />
                <input type="text" name="targetDays" placeholder="Target Days To Achieve Goal" required />
                <button type="submit" class="log-button">Log Goal</button>
            </div>
            <button class="view-button" onclick="location.href='http://localhost:8080/sustainify/api/view/goals'">View Goals</button>
        </form>
    </div>
</div>

<div class="tips">
        <h2>Submit a Tip</h2>
        <form action="submit-tip" method="post">
            <div class="tip-form">
                <input type="text" name="title" placeholder="Enter Tip Title" required />
                <input type="text" name="content" placeholder="Tip Description" required />
                <button type="submit" class="log-button">Submit Tip</button>
            </div>
            <button class="view-button" onclick="location.href='http://localhost:8080/sustainify/api/view/tips'">View Tips</button>
        </form>
    </div>
</body>
</html>
