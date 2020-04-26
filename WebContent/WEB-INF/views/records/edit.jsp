<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${record != null}">
                <h2>詳細と編集</h2>
                <form method="POST" action="<c:url value='/records/update' />">
                    <c:import url="_form.jsp" />
                </form>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <br />
        <form method="POST" action="<c:url value='/records/delete' />">
            <input type="hidden" name="_token" value="${_token}">
            <button type="submit">削除</button>
        </form>

        <p><a href="<c:url value='/' />">トップページに戻る</a></p>
    </c:param>
</c:import>