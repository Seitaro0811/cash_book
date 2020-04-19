<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>ユーザー情報　編集</h2>
        <p>(パスワードは変更する場合のみ入力してください)</p>
        <form method="POST" action="<c:url value='/book_users/update' />">
            <c:import url="_form.jsp" />
        </form>

        <p><a href="<c:url value='/' />">トップページ</a></p>
    </c:param>
</c:import>