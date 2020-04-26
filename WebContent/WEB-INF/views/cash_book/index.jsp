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
        <table id="record_list" border="1">
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
                    <td>総支出</td>
                    <td>${expenditure} 円</td>
                    <td>残金</td>
                    <td>${income - expenditure} 円</td>
                </tr>
            </tfoot>
        </table>

        <div id="pagination">
            (全 ${records_count} 件)<br />
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
        </div>
        <p><a href="<c:url value='/records/new' />">記帳</a></p>
    </c:param>
</c:import>