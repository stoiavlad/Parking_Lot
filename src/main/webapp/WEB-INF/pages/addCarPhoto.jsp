<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:pageTemplate pageTitle="Add Car Photo">
    <h1>Add Photo for Car</h1>

    <c:if test="${not empty car}">
        <p>
            <strong>License plate:</strong> ${car.licensePlate}
        </p>

        <form method="POST"
              action="${pageContext.request.contextPath}/AddCarPhoto"
              enctype="multipart/form-data">

            <!-- File input -->
            <div class="mb-3">
                <input type="file"
                       name="photo"
                       class="form-control"
                       required />
            </div>

            <input type="hidden" name="car_id" value="${car.id}" />

            <button type="submit" class="btn btn-primary">
                Upload Photo
            </button>
        </form>
    </c:if>

</t:pageTemplate>
