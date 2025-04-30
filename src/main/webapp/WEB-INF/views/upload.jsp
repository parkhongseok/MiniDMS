<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>문서 업로드 - MiniDMS</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css" />
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="layout-wrapper">
    <div class="main-container">
        <h2>📤 문서 업로드</h2>

        <form action="/upload" method="post" enctype="multipart/form-data">

            <div class="label-group">
                <label for="file">파일 선택</label>
                <input type="file" id="file" name="file" required />
            </div>

            <div class="label-group">
                <label for="description">파일 설명</label>
                <textarea id="description" name="description" required></textarea>
            </div>

            <button type="submit">업로드</button>
        </form>
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>
