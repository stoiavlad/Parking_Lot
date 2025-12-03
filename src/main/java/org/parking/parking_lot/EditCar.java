package org.parking.parking_lot;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.parking.parkinglot.common.CarDto;
import org.parking.parkinglot.common.UserDto;
import org.parking.parkinglot.ejb.CarsBean;
import org.parking.parkinglot.ejb.UserBean;

import java.io.IOException;
import java.util.List;

@ServletSecurity(@HttpConstraint(rolesAllowed = {"WRITE_CARS"}))
@WebServlet(name = "EditCar", value = "/EditCar")
public class EditCar extends HttpServlet {

    @Inject
    UserBean usersBean;

    @Inject
    CarsBean carsBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<UserDto> users = usersBean.findAllUsers();
        req.setAttribute("users", users);

        Long carId = Long.parseLong(req.getParameter("id"));
        CarDto car = carsBean.findById(carId);
        req.setAttribute("car", car);

        req.getRequestDispatcher("/WEB-INF/pages/editCar.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long carId = Long.parseLong(request.getParameter("car_id"));
        String licensePlate = request.getParameter("license_plate");
        String parkingSpot = request.getParameter("parking_spot");
        Long userId = Long.parseLong(request.getParameter("owner_id"));

        carsBean.updateCar(carId, licensePlate, parkingSpot, userId);

        response.sendRedirect(request.getContextPath() + "/Cars");
    }
}
