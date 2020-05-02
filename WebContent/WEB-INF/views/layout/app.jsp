<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>おこづかい帳</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <div id="title">
                    <h1>おこづかい帳</h1>
                </div>
                <c:if test="${login_user != null}">
                    <div id="user">
                        <a href="<c:url value='/book_users/edit' />"><c:out value="${login_user.getName()}" /></a>
                    </div>
                </c:if>
            </div>
            <div id="content">
                ${param.content}
            </div>
            <div id="footer">
                <c:choose>
                    <c:when test="${login_user != null}">
                        <p><a href="<c:url value='/logout' />">ログアウト</a></p>
                    </c:when>
                    <c:otherwise>
                        <p><a href="<c:url value='/book_users/new' />">新規登録</a></p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </body>
</html>