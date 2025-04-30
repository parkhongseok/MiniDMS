<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>MiniDMS - Home</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css" />
</head>
<body>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="layout-wrapper">
    <div class="main-container">
        <h1>Mini Document Management System</h1>

        <form action="/upload" method="get">
            <button type="submit">문서 업로드</button>
        </form>

        <form action="/documents" method="get">
            <button type="submit">📁 문서 목록</button>
        </form>

        <form action="/test-list" method="get">
            <button type="submit">🧪 테스트 데이터 확인</button>
        </form>
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>
