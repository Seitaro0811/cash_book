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
        <h2><a href="">←</a> ____年 __月 <a href="">→</a></h2>
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
                <c:forEach var="record" items="${records}">
                    <tr>
                        <td class="record_date"><fmt:formatDate value="${record.date}" pattern="yyyy-MM-dd" /></td>
                        <td class="record_content">${record.content}</td>
                        <td class="record_amount">${record.amount}</td>
                        <td class="record_comment"><c:out value="${record.comment}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
            <tfoot>
                <tr>
                    <td></td>
                    <td></td>
                    <td>総支出</td>
                    <td>残金</td>
                </tr>
            </tfoot>
        </table>

        <div id="pagination">
            (全 ${records_count} 件)<br />
            <c:forEach var="i" begin="1" end="${(records_count - 1) / 15 + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/?page=${1}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:param>
</c:import>