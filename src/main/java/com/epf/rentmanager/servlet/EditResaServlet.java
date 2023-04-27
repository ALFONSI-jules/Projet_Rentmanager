package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/rents/edit")
public class EditResaServlet extends HttpServlet {
    @Autowired
    private ClientService clientService;
    @Autowired
    private ReservationService reservationService;
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

            request.setAttribute("clientObj", clientService.findAll());
            request.setAttribute("vehicleObj", vehicleService.findAll());
            request.setAttribute("reservation", reservationService.findById(Integer.parseInt(request.getParameter("id"))));

        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/edit.jsp").forward(request, response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {
            Reservation reservation = new Reservation();


            reservation.setClient_id(Integer.parseInt(request.getParameter("client")));
            reservation.setVehicle_id(Integer.parseInt(request.getParameter("vehicle")));
            reservation.setDebut(LocalDate.parse(request.getParameter("debut")));
            reservation.setFin(LocalDate.parse(request.getParameter("fin")));
            reservation.setId(Integer.parseInt(request.getParameter("id")));
            reservationService.edit(reservation);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


        response.sendRedirect("../rents");
    }

}
