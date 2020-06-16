<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<br/>
<table>
    <c:if test="${!empty noteList}">
        <tr>
            <th></th>
            <th>
                <spring:message code="notes.noteheader"/>
            </th>
            <th>
                <spring:message code="notes.date"/>
            </th>
        </tr>
        <c:forEach var="note" items="${noteList}" varStatus="i">
            <tr>
                <td>
                        ${i.index+1}.
                </td>
                <td>
                    <a href="<c:url value="/notes/${note.id}"/>">${note.noteDetailsList[0].header}</a>
                </td>
                <td>
                    <fmt:formatDate	value="${note.noteDetailsList[0].date.getTime()}" pattern="MMM d, YYYY 'at' HH:mm a " />
                </td>
            </tr>
        </c:forEach>
    </c:if>
        <tr>
            <td colspan="3" style="border:none">
                <a href="<c:url value="/notes/addNote"/>">
                    <input type="button" class="form-button" value="<spring:message code="notes.submit"/>" style="width:50%"/>
                </a>
            </td>
        </tr>
</table>





