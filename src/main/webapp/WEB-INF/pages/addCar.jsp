<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:pageTemplate pageTitle="Add Car">

    <div class="container">
        <main>
            <h1 class="mb-3">Add Car</h1>

            <form class="needs-validation" novalidate
                  method="POST"
                  action="${pageContext.request.contextPath}/AddCar">

                <div class="col-12 mb-3">
                    <label for="license_plate" class="form-label">License plate</label>
                    <input type="text" class="form-control" id="license_plate"
                           name="license_plate" required>
                    <div class="invalid-feedback">
                        License plate is required.
                    </div>
                </div>

                <div class="col-12 mb-3">
                    <label for="parking_spot" class="form-label">Parking spot</label>
                    <input type="text" class="form-control" id="parking_spot"
                           name="parking_spot" required>
                    <div class="invalid-feedback">
                        Parking spot is required.
                    </div>
                </div>

                <div class="col-12 mb-3">
                    <label for="owner_id" class="form-label">Owner</label>
                    <select class="form-select custom-select d-block w-100" id="owner_id" name="owner_id" required>
                        <option value="">Choose...</option>

                        <c:forEach items="${users}" var="u">
                            <option value="${u.id}">${u.username}</option>
                        </c:forEach>

                    </select>
                    <div class="invalid-feedback">
                        Please choose an owner.
                    </div>
                </div>

                <button class="btn btn-primary btn-lg" type="submit">Save</button>

            </form>
        </main>
    </div>
</t:pageTemplate>