package example.parkinglot.parking;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.inject.Inject;
import org.parking.parkinglot.ejb.UserBean;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "AddUser", value = "/AddUser")
public class AddUser extends HttpServlet {

    @Inject
    UserBean usersBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String> userGroups = Arrays.asList(
                "READ_CARS", "WRITE_CARS",
                "READ_USERS", "WRITE_USERS"
        );

        request.setAttribute("userGroups", userGroups);

        request.getRequestDispatcher("/WEB-INF/pages/addUser.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String[] groups = request.getParameterValues("user_groups");
        if (groups == null) {
            groups = new String[0];
        }

        usersBean.createUser(username, email, password, Arrays.asList(groups));

        response.sendRedirect(request.getContextPath() + "/Users");
    }
}
