<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MiniDMS - Home</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            text-align: center;
        }
        h1 {
            margin-bottom: 40px;
        }
        button {
            padding: 12px 24px;
            margin: 10px;
            font-size: 16px;
        }
    </style>
</head>
<body>
    <h1>Mini Document Management System</h1>

    <form action="/upload" method="get">
        <button type="submit">문서 업로드</button>
    </form>

    <form action="/documents" method="get">
        <button type="submit">문서 목록</button>
    </form>

    <form action="/test-list" method="get">
        <button type="submit">테스트 데이터 확인</button>
    </form>
</body>
</html>
