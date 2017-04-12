<%--
  Created by IntelliJ IDEA.
  User: abhishek
  Date: 25/02/17
  Time: 1:07 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <meta name="layout" content="main">

    <style>

    .button {
        font: bold 11px Arial;
        text-decoration: none;
        background-color: #EEEEEE;
        color: #333333;
        padding: 8px 39px;
        border-top: 1px solid #CCCCCC;
        border-right: 1px solid #333333;
        border-bottom: 1px solid #333333;
        border-left: 1px solid #CCCCCC;
        margin-left: 2.3em;
    }
    </style>
</head>

<body>
<ul class="errors">
    <li> ${flash.message}</li>
</ul>

<a href="../" class="button">Back</a>
</body>
</html>