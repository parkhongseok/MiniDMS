<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>업로드 완료 - MiniDMS</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css" />
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="layout-wrapper">
    <div class="main-container">
        <h2>파일 업로드 성공</h2>

        <div class="filepath">${savedPath}</div>

        <form action="/" method="get">
            <button type="submit">홈으로 돌아가기</button>
        </form>
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>
