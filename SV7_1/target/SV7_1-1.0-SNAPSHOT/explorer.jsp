<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        td {
            padding-right: 10px;
        }
        table {
            border: solid 0.5vw;
            animation: rainbow 4s linear infinite;
        }
        @keyframes rainbow {
            100%,0%{border-color: rgb(255,0,0);}
            8%{border-color: rgb(255,127,0);}
            16%{border-color: rgb(255,255,0);}
            25%{border-color: rgb(127,255,0);}
            33%{border-color: rgb(0,255,0);}
            41%{border-color: rgb(0,255,127);}
            50%{border-color: rgb(0,255,255);}
            58%{border-color: rgb(0,127,255);}
            66%{border-color: rgb(0,0,255);}
            75%{border-color: rgb(127,0,255);}
            83%{border-color: rgb(255,0,255);}
            91%{border-color: rgb(255,0,127);}
        }
        img {
            position: relative;
            top: 3px;
        }
    </style>
    <title>Directories</title>
</head>
<body>
<style>
    body {
        background: url(https://images.hdqwalls.com/download/ios-11-heritage-stripe-red-yb-1920x1080.jpg);
        background-size: 100%;
    }
</style>
<form action="explorer" method="post" style="float: right">
    <input type="submit" name="logout" value="Logout"/>
</form>

<div style="margin-left: 10px">${now}</div>
<h1 style="margin-left: 10px">${name}</h1>

<div class="content" style="margin-left: 450px">
    <table>
        <tr>
            <th>Файл</th>
            <th>Размер</th>
            <th>Дата</th>
        </tr>
        ${folders}
        ${files}
    </table>
</div>
</body>
</html>