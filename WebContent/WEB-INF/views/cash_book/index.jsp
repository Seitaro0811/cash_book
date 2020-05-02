<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2><a href="<c:url value='/?year=${year}&month=${month-1}' />">←</a>
        <c:out value=" ${year}" />年 ${month}月
        <a href="<c:url value='/?year=${year}&month=${month+1}' />">→</a></h2>
        <form name="select" method="GET" action="<c:url value='/' />">
                        <select name="select">
                            <option value="" <c:if test="${selected==''}"><c:out value="selected" /></c:if>>すべて</option>
                            <option value="収入" <c:if test="${selected=='収入'}"><c:out value="selected" /></c:if>>収入</option>
                            <option value="食費" <c:if test="${selected=='食費'}"><c:out value="selected" /></c:if>>食費</option>
                            <option value="日用品費" <c:if test="${selected=='日用品費'}"><c:out value="selected" /></c:if>>日用品費</option>
                            <option value="その他生活費" <c:if test="${selected=='その他生活費'}"><c:out value="selected" /></c:if>>その他生活費</option>
                            <option value="娯楽費" <c:if test="${selected=='娯楽費'}"><c:out value="selected" /></c:if>>娯楽費</option>
                            <option value="その他" <c:if test="${selected=='その他'}"><c:out value="selected" /></c:if>>その他</option>
                        </select>
            <button type="submit">絞り込み</button>
        </form>
        <table id="record_list">
            <thead>
                <tr>
                    <th class="record_date">日付</th>
                    <th class="record_content">内容</th>
                    <th class="record_amount">金額</th>
                    <th class="record_comment">コメント</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="record" items="${records}" varStatus="status">
                    <tr>
                        <td class="record_date"><fmt:formatDate value="${record.date}" pattern="dd" />日</td>
                        <td class="record_content">${record.content}</td>
                        <td class="record_amount">${record.amount} 円</td>
                        <td class="record_comment">
                        <a href="<c:url value='/records/edit?id=${record.id}' />"><c:out value="${c_index.get(status.count-1 + (page-1)*10)}" />…</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
            <tfoot>
                <tr>
                    <td></td>
                    <td class="record_content">合計</td>
                    <td class="record_amount">${total} 円</td>
                    <td class="new_record"><a href="<c:url value='/records/new' />">記帳</a></td>
                </tr>
            </tfoot>
        </table>

        <div id="pagination">
            <p>(全 ${records_count} 件)</p>
            <p>
            <c:forEach var="i" begin="1" end="${(records_count - 1) / 10 + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/?year=${year}&month=${month}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            </p>
        </div>
    </c:param>
</c:import>