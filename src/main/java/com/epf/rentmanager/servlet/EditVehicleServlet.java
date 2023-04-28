package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cars/edit")
public class EditVehicleServlet extends HttpServlet {
    @Autowired
    private VehicleService vehicleService;
    private static final long serialVersionUID = 1L;
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("vehicle", vehicleService.findById(Integer.parseInt(request.getParameter("id"))));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/edit.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Vehicle vehicle = new Vehicle();
            vehicle.setId(Integer.parseInt(request.getParameter("id")));
            vehicle.setConstructeur(request.getParameter("constructeur"));
            vehicle.setNb_places(Integer.parseInt(request.getParameter("nb_places")));
            vehicleService.edit(vehicle);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("../cars");
    }

}
