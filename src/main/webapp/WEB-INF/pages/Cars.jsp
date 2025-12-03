<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Cars">

    <h1>Cars</h1>

    <form method="POST" action="${pageContext.request.contextPath}/Cars">

        <!-- ADD BUTTON -->
        <c:if test="${pageContext.request.isUserInRole('WRITE_CARS')}">
            <a href="${pageContext.request.contextPath}/AddCar" class="btn btn-primary btn-lg">Add Car</a>
        </c:if>

        <!-- DELETE BUTTON -->
        <c:if test="${pageContext.request.isUserInRole('WRITE_CARS')}">
            <button class="btn btn-danger" type="submit">Delete selected</button>
        </c:if>

        <div class="container text-center mt-4">

            <!-- HEADER -->
            <div class="row fw-bold mb-2">
                <c:if test="${pageContext.request.isUserInRole('WRITE_CARS')}">
                    <div class="col">Select</div>
                </c:if>

                <div class="col">License Plate</div>
                <div class="col">Parking Spot</div>
                <div class="col">Owner Name</div>

                <c:if test="${pageContext.request.isUserInRole('WRITE_CARS')}">
                    <div class="col">Edit</div>
                </c:if>
            </div>

            <!-- ROWS -->
            <c:forEach var="car" items="${cars}">
                <div class="row mb-2">

                    <!-- CHECKBOX -->
                    <c:if test="${pageContext.request.isUserInRole('WRITE_CARS')}">
                        <div class="col">
                            <input type="checkbox" name="car_ids" value="${car.id}" />
                        </div>
                    </c:if>

                    <!-- CAR DATA -->
                    <div class="col">${car.licensePlate}</div>
                    <div class="col">${car.parkingSpot}</div>
                    <div class="col">${car.ownerName}</div>

                    <!-- EDIT BUTTON -->
                    <c:if test="${pageContext.request.isUserInRole('WRITE_CARS')}">
                        <div class="col">
                            <a href="${pageContext.request.contextPath}/EditCar?id=${car.id}"
                               class="btn btn-warning btn-sm">Edit</a>
                        </div>
                    </c:if>

                </div>
            </c:forEach>

        </div>
    </form>

    <h5 class="mt-3">Free parking spots: ${numberOfFreeParkingSpots}</h5>

</t:pageTemplate>
