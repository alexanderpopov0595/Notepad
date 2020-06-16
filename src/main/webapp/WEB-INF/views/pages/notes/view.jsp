<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<sec:authentication var="principal" property="principal" />
<div class="grid-form">
    <form:form class="form" method="POST" modelAttribute="note" >
        <div class="form-header">
            <h3>
                <spring:message code="notes.update.title"/>
            </h3>
        </div>
        <input type="hidden" name="id" value="${note.id}" />
        <input type="hidden" name="user.id" value="${note.user.id}"/>
        <div class="form-element">
            <fmt:formatDate	value="${note.noteDetailsList[0].date.getTime()}" pattern="MMM d, YYYY 'at' HH:mm a " />
        </div>
        <div class="form-element">
            <c:if test="${principal.username==note.user.login}">
                <input type="text" class="form-input" name="noteDetailsList[0].header" placeholder="<spring:message code="notes.header"/>" value="${note.noteDetailsList[0].header}" required="true"/>
            </c:if>
            <c:if test="${principal.username!=note.user.login}">
                <input type="text" class="form-input" name="noteDetailsList[0].header" placeholder="<spring:message code="notes.header"/>" value="${note.noteDetailsList[0].header}" readonly/>
            </c:if>
        </div>
        <div class="form-element">
            <c:if test="${principal.username==note.user.login}">
               <textarea class="form-input" name="noteDetailsList[0].text" placeholder="<spring:message code="notes.text"/>" value="${note.noteDetailsList[0].text}" rows="4" cols="50" maxlength="255" required >${note.noteDetailsList[0].text}
               </textarea>
            </c:if>
            <c:if test="${principal.username!=note.user.login}">
               <textarea class="form-input" name="noteDetailsList[0].text" placeholder="<spring:message code="notes.text"/>" value="${note.noteDetailsList[0].text}" rows="4" cols="50" maxlength="255" readonly >${note.noteDetailsList[0].text}
               </textarea>
            </c:if>
        </div>
        <c:if test="${! empty note.noteDetailsList && note.noteDetailsList.size()>1}">
            <span class="title" onclick="openHistory()">
                <spring:message code="notes.show"/>
            </span>
            <div class="history">
                <br/>
                <c:forEach var="noteDetails" items="${note.noteDetailsList}" varStatus="i">
                    <c:if test="${i.index!=0}">
                        <div class="form-element">
                            <fmt:formatDate	value="${noteDetails.date.getTime()}" pattern="MMM d, YYYY 'at' h:mm a " />
                        </div>
                        <div class="form-element">
                            <h4>${noteDetails.header}<h4/>
                        </div>
                        <div class="form-element">
                            <textarea class="form-input" placeholder="<spring:message code="notes.text"/>"  rows="4" cols="50" maxlength="255" readonly >${noteDetails.text}
                            </textarea>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </c:if>
        <c:if test="${principal.username==note.user.login}">
            <div class="form-element">
                <input class="form-button delete" type="submit" value="<spring:message code="notes.delete"/>" formaction="<c:url value="/notes/${note.id}/delete"/>"/>
                <!--<input class="form-button delete" type="submit" value="<spring:message code="notes.export"/>" formaction="<c:url value="/notes/${note.id}/export"/>"/>-->
            </div>
            <div class="form-element">
                <input class="form-button" type="submit" value="<spring:message code="notes.update"/>" />

            </div>
        </c:if>
    </form:form>
</div>

