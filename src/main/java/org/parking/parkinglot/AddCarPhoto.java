package org.parking.parkinglot;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import org.ejb.CarsBean;
import org.common.CarDto;

import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "AddCarPhoto", value = "/AddCarPhoto")
@MultipartConfig
public class AddCarPhoto extends HttpServlet {

    @Inject
    CarsBean carsBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        if (id != null) {
            CarDto car = carsBean.findById(Long.parseLong(id));
            request.setAttribute("car", car);
        }

        request.getRequestDispatcher("/WEB-INF/pages/addCarPhoto.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long carId = Long.parseLong(request.getParameter("car_id"));
        Part filePart = request.getPart("photo");

        String fileName = filePart.getSubmittedFileName();
        String fileType = filePart.getContentType();

        InputStream inputStream = filePart.getInputStream();
        byte[] fileContent = inputStream.readAllBytes();

        carsBean.addPhotoToCar(carId, fileName, fileType, fileContent);

        response.sendRedirect(request.getContextPath() + "/Cars");
    }
}
