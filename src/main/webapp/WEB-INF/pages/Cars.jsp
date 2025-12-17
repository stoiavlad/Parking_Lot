<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:pageTemplate pageTitle="Cars">
    <h1>Cars</h1>

    <!-- BUTOANE SUS -->
    <form method="POST" action="${pageContext.request.contextPath}/Cars">
        <div class="mb-3">
            <a href="${pageContext.request.contextPath}/AddCar"
               class="btn btn-primary me-2">
                Add Car
            </a>

            <button type="submit"
                    name="delete"
                    class="btn btn-danger">
                Delete Cars
            </button>
        </div>

        <!-- HEADER -->
        <div class="container text-center">
            <div class="row fw-bold mb-2">
                <div class="col"> </div>
                <div class="col">License Plate</div>
                <div class="col">Parking Spot</div>
                <div class="col">Owner</div>
                <div class="col">Photo</div>
                <div class="col">Actions</div>
            </div>

            <!-- LISTA MASINI -->
            <c:forEach var="car" items="${cars}">
                <div class="row mb-2 align-items-center">

                    <!-- CHECKBOX -->
                    <div class="col">
                        <input type="checkbox"
                               name="car_ids"
                               value="${car.id}" />
                    </div>

                    <!-- LICENSE -->
                    <div class="col">
                            ${car.licensePlate}
                    </div>

                    <!-- PARKING -->
                    <div class="col">
                            ${car.parkingSpot}
                    </div>

                    <!-- OWNER -->
                    <div class="col">
                            ${car.ownerName}
                    </div>

                    <!-- PHOTO -->
                    <div class="col">
                        <img src="${pageContext.request.contextPath}/CarPhotos?id=${car.id}"
                             width="80"
                             alt="car photo"/>
                    </div>

                    <!-- ACTIONS -->
                    <div class="col">
                        <a class="btn btn-sm btn-secondary me-1"
                           href="${pageContext.request.contextPath}/AddCarPhoto?id=${car.id}">
                            Add photo
                        </a>

                        <a class="btn btn-sm btn-secondary"
                           href="${pageContext.request.contextPath}/EditCar?id=${car.id}">
                            Edit Car
                        </a>
                    </div>

                </div>
            </c:forEach>
        </div>
    </form>

    <!-- FREE PARKING SPOTS -->
    <p class="mt-3">
        Free parking spots: ${freeParkingSpots}
    </p>

</t:pageTemplate>
