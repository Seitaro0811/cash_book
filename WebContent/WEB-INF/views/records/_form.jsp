<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>
    </div>
</c:if>
<label for="date">日付</label>
<input type="date" name="date" value="<fmt:formatDate value='${record.date}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for="content">内容</label>
<select name="content">
    <option value="収入">収入</option>
    <option value="食費">食費</option>
    <option value="日用品費">日用品費</option>
    <option value="その他生活費">その他生活費</option>
    <option value="娯楽費">娯楽費</option>
    <option value="その他">その他</option>
</select>
<br /><br/>

<label for="amount">金額</label>
<input type="number" name="amount" value="${record.amount}" />
<br /><br />

<label for="comment">備考</label>
<textarea name="comment" rows="10" cols="30">${record.comment}</textarea>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>