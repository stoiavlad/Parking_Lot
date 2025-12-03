<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Add User">

    <h1>Add User</h1>

    <form method="POST" action="${pageContext.request.contextPath}/AddUser">

        <div class="mb-3">
            <label class="form-label">Username</label>
            <input type="text" name="username" class="form-control" required />
        </div>

        <div class="mb-3">
            <label class="form-label">Email</label>
            <input type="email" name="email" class="form-control" required />
        </div>

        <div class="mb-3">
            <label class="form-label">Password</label>
            <input type="password" name="password" class="form-control" required />
        </div>

        <div class="mb-3">
            <label class="form-label">Groups</label>
            <select name="user_groups" multiple class="form-control" size="4">
                <c:forEach var="group" items="${userGroups}">
                    <option value="${group}">${group}</option>
                </c:forEach>
            </select>
        </div>

        <button type="submit" class="btn btn-primary mt-3">Save</button>

    </form>

</t:pageTemplate>
