package org.parking.parkinglot;

import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.common.UserDto;
import org.ejb.UserBean;
import org.ejb.InvoiceBean;

import java.io.IOException;
import java.util.List;

@DeclareRoles({"READ_USERS", "WRITE_USERS"})
@ServletSecurity(
        value = @HttpConstraint(rolesAllowed = {"READ_USERS"}),      // GET → READ_USERS
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "POST", rolesAllowed = {"WRITE_USERS"}) // POST → WRITE_USERS
        }
)
@WebServlet(name = "Users", value = "/Users")
public class Users extends HttpServlet {

    @Inject
    UserBean userBean;

    @Inject
    InvoiceBean invoiceBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        List<UserDto> users = userBean.findAllUsers();
        request.setAttribute("users", users);

        if (!invoiceBean.getUserIds().isEmpty()) {
            request.setAttribute(
                    "invoices",
                    userBean.findUsernamesByUserIds(invoiceBean.getUserIds())
            );
        }

        request.getRequestDispatcher("/WEB-INF/pages/Users.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String[] userIdsAsString = request.getParameterValues("user_ids");

        if (userIdsAsString != null) {
            for (String id : userIdsAsString) {
                invoiceBean.getUserIds().add(Long.parseLong(id));
            }
        }

        response.sendRedirect(request.getContextPath() + "/Users");
    }
}
