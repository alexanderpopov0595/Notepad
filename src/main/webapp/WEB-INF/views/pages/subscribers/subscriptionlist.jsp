<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<br/>
<table>
    <c:if test="${!empty subscriptionList}">
        <tr>
            <th></th>
            <th>
                <spring:message code="subscribers.photo"/>
            </th>
            <th>
                <spring:message code="subscribers.username"/>
            </th>
        </tr>
        <c:forEach var="subscriber" items="${subscriptionList}" varStatus="i">

                <tr>
                    <td>
                            ${i.index+1}.
                    </td>
                    <td>
                        <img src="<c:url value="/images/${subscriber.user.login}.jpg"/>" width="50" border="0" align="center"	onError="this.src='<c:url value="/resources/img"/>/avatar.jpg';" />
                    </td>
                    <td>
                        <a href="<c:url value="/notes/user/${subscriber.user.login}"/>">
                            ${subscriber.user.login}
                        </a>
                    </td>
                </tr>


        </c:forEach>
    </c:if>
</table