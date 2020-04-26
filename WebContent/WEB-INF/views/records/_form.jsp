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
    <option value="">選択してください</option>
        <option value="収入" <c:if test="${selected == '収入'}"> <c:out value="selected" /> </c:if>>収入</option>
    <option disabled>支出</option>
        <option value="食費" <c:if test="${selected == '食費'}"> <c:out value="selected" /> </c:if>>食費</option>
        <option value="日用品費" <c:if test="${selected == '日用品費'}"> <c:out value="selected" /> </c:if>>日用品費</option>
        <option value="その他生活費" <c:if test="${selected == 'その他生活費'}"> <c:out value="selected" /> </c:if>>その他生活費</option>
        <option value="娯楽費" <c:if test="${selected == '娯楽費'}"> <c:out value="selected" /> </c:if>>娯楽費</option>
        <option value="その他" <c:if test="${selected == 'その他'}"> <c:out value="selected" /> </c:if>>その他</option>
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