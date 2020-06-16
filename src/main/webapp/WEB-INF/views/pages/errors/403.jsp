<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="grid-form">
    <div class="form">
        <h1>
            <spring:message code="error.access"/>
        </h1>
        <a  style="text-decoration: none; color: black" href="<c:url value="/notes" />" role="button">
            <spring:message code="error.link"/>
        </a>
    </div>
</div>
