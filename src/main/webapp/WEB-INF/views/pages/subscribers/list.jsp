<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<br/>
<table>
    <c:if test="${!empty subscriberList}">
        <tr>
            <th></th>
            <th>
                <spring:message code="subscribers.photo"/>
            </th>
            <th>
                <spring:message code="subscribers.username"/>
            </th>
        </tr>
        <c:forEach var="subscriber" items="${subscriberList}" varStatus="i">

            <tr>
                <td>
                        ${i.index+1}.
                </td>
                <td>
                    <img src="<c:url value="/images/${subscriber.subscriber.login}.jpg"/>" width="50" border="0" align="center"	onError="this.src='<c:url value="/resources/img"/>/avatar.jpg';" />
                </td>
                <td>
                    <p>${subscriber.subscriber.login}</p>
                    <c:if test="${subscriber.status=='WAITING'}">
                        <a href="<c:url value="/subscribers/acceptSubscriber/${subscriber.id}"/>">
                            <input type="button" class="button" value="<spring:message code="subscribers.accept"/>"/>
                        </a>
                    </c:if>

                    <a href="<c:url value="/subscribers/deleteSubscriber/${subscriber.id}"/>">
                        <input type="button" class="button" value="<spring:message code="subscribers.decline"/>"/>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </c:if>
</table>