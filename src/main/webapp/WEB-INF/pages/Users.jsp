<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Users">
    <h1>Users</h1>

    <!-- FORM pentru INVOICE -->
    <form method="POST" action="${pageContext.request.contextPath}/Users">

        <!-- BUTOANE SUS -->
        <div class="mb-3">
            <!-- ADD USER button (doar WRITE_USERS) -->
            <c:if test="${pageContext.request.isUserInRole('WRITE_USERS')}">
                <a href="${pageContext.request.contextPath}/AddUser"
                   class="btn btn-primary me-2">
                    Add User
                </a>
            </c:if>

            <!-- INVOICE button -->
            <button type="submit" class="btn btn-secondary">
                Invoice
            </button>
        </div>

        <!-- TABEL USERS -->
        <div class="container text-center">
            <div class="row fw-bold mb-2">
                <div class="col">Select</div>
                <div class="col">Username</div>
                <div class="col">Email</div>
            </div>

            <c:forEach var="user" items="${users}">
                <div class="row mb-2">
                    <div class="col">
                        <input type="checkbox"
                               name="user_ids"
                               value="${user.id}" />
                    </div>
                    <div class="col">
                            ${user.username}
                    </div>
                    <div class="col">
                            ${user.email}
                    </div>
                </div>
            </c:forEach>
        </div>

    </form>

    <!-- AFISARE INVOICES -->
    <c:if test="${not empty invoices}">
        <hr/>
        <h2>Invoices</h2>

        <c:forEach var="username" items="${invoices}" varStatus="status">
            ${status.index + 1}. ${username}<br/>
        </c:forEach>
    </c:if>

</t:pageTemplate>
