<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="grid-form">
    <form:form class="form" method="POST" modelAttribute="note">
        <div class="form-header">
            <h3>
                <spring:message code="notes.form.title"/>
            </h3>
        </div>
        <div class="form-element">
            <input type="text" class="form-input" name="noteDetailsList[0].header" placeholder="<spring:message code="notes.header"/>" required/>
        </div>
        <div class="form-element">
            <textarea class="form-input" name="noteDetailsList[0].text" placeholder="<spring:message code="notes.text"/>" rows="4" cols="50" maxlength="255" required ></textarea>
        </div>
        <div class="form-element">
            <input class="form-button" type="submit" value="<spring:message code="notes.submit"/>"/>
        </div>
    </form:form>
</div>

