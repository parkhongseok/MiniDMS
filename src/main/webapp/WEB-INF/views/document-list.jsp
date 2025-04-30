<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>문서 목록 - MiniDMS</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css" />
</head>
<body>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="layout-wrapper">
    <div class="main-container ">
        <h2>📁 문서 목록</h2>

        <c:if test="${empty documentList}">
            <p>등록된 문서가 없습니다.</p>
        </c:if>

        <c:if test="${not empty documentList}">

             <c:forEach var="doc" items="${documentList}">
                 <div class="document-card ">
                     <div class="doc-header">
                         <div class="doc-title">${doc.safeFilename}</div>
                         <div class="doc-meta">
                             <span>${doc.safeUploader}</span> ·
                             <span>${doc.uploadedAt}</span>
                         </div>
                     </div>


                     <button class="toggle-details">상세 보기</button>
                     <div class="doc-details" style="display:none;">

                         <p><strong>상세 설명 : </strong> ${doc.safeDescription}</p>
                         <p><strong>파일 경로 : </strong> ${doc.filePath}</p>
                         <p><strong>파일 크기 : </strong> ${doc.displayFilesize}</p>
                         <p><strong>파일 버전 : </strong> ${doc.versionNumber}</p>
                     </div>
                 </div>
             </c:forEach>

        </c:if>
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>

<script>
$(document).ready(function() {
    $('.toggle-details').click(function() {
        $(this).next('.doc-details').slideToggle();
    });
});
</script>
</body>
</html>

