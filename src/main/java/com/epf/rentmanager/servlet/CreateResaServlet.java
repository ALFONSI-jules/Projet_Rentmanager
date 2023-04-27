package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.validateur.ValidateurVehicle;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/rents/create")
public class CreateResaServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    ClientService clientService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    VehicleService vehicleService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            request.setAttribute("vehicleObj",vehicleService.findAll());
            request.setAttribute("clientObj", clientService.findAll());
        }
        catch(ServiceException e){
            e.printStackTrace();
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try{
            Reservation reservation = new Reservation();
            int vehicle_id = Integer.parseInt(request.getParameter("vehicle"));
            int client_id = Integer.parseInt(request.getParameter("client"));
            LocalDate debut = LocalDate.parse(request.getParameter("debut"));
            LocalDate fin = LocalDate.parse(request.getParameter("fin"));
            reservation.setClient_id(client_id);
            reservation.setVehicle_id(vehicle_id);
            reservation.setDebut(debut);
            reservation.setFin(fin);
            if (ValidateurVehicle.voitureJour(reservation) && ValidateurVehicle.resa7jours(reservation)){
                reservationService.create(reservation);
            }
            else{

            }

        }
        catch(ServiceException e){
            e.printStackTrace();
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request, response);
    }

}
