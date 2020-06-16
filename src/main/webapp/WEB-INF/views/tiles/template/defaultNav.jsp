<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<ul>
    <li>
        <form class="nav-search" method="POST" action="<c:url value="/users/searchUsers"/>">
            <input type="text" name="login" placeholder="<spring:message code="nav.search.message"/>"/>
            <input type="submit" class="button other" value="<spring:message code="nav.search.button"/>"/>
        </form>
    </li>

    <li>
        <a href="?lang=en">en</a>
    </li>
    <li>
        <a href="?lang=de">de</a>
    </li>
    <sec:authorize access="!isAuthenticated()">
        <li style="float:right">
            <a class="active" href="<c:url value="/users/signup"/>">
                <i class="fa fa-fw fa-user"></i>  <spring:message code="nav.signup"/>
            </a>
        </li>
        <li style="float:right">
            <a  href="<c:url value="/users/signin"/>">
                <i class="fa fa-sign-in" aria-hidden="true"></i>  <spring:message code="nav.signin"/>
            </a>
        </li>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <li>
            <a href="<c:url value="/notes"/>">
                <i class="fa fa-sticky-note" aria-hidden="true"></i> <spring:message code="nav.notes"/>
            </a>
        </li>
        <li>
            <a href="<c:url value="/subscribers/subscriptions"/>">
                <i class="fa fa-clipboard" aria-hidden="true"></i> <spring:message code="nav.subscriptions"/>
            </a>
        </li>
        <li>
            <a href="<c:url value="/subscribers"/>">
                <i class="fa fa-users" aria-hidden="true"></i> <spring:message code="nav.subscribers"/>
            </a>
        </li>
        <li style="float:right">
            <a  class="active" href="<c:url value="/users/signout"/>">
                <i class="fa fa-sign-in" aria-hidden="true"></i>  <spring:message code="nav.signout"/>
            </a>
        </li>
        <li style="float:right">
            <a  href="<c:url value="/users/account/update"/>">
                <i class="fa fa-cog" aria-hidden="true"></i>
            </a>
        </li>
    </sec:authorize>
</ul>
