<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>문서 업로드</title>
</head>
<body>
<h2>문서 업로드</h2>

<form action="/upload" method="post" enctype="multipart/form-data">
    <label>제목: <input type="text" name="title"/></label><br/>
    <label>설명: <textarea name="description"></textarea></label><br/>
    <label>파일: <input type="file" name="file"/></label><br/>
    <button type="submit">업로드</button>
</form>

</body>
</html>
