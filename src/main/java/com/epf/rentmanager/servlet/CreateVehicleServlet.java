package com.epf.rentmanager.servlet;


import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;

import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.validateur.ValidateurVehicle;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cars/create")
public class CreateVehicleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Autowired
    VehicleService vehicleService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp").forward(request, response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            Vehicle vehicle = new Vehicle();
            String constructeur = request.getParameter("constructeur");
            int nb_places = Integer.parseInt(request.getParameter("nb_places"));
            vehicle.setConstructeur(constructeur);
            vehicle.setNb_places(nb_places);
            if (ValidateurVehicle.nbPlaces_valide(vehicle)){
                vehicleService.create(vehicle);
                response.sendRedirect("../cars");
            }
            else{
                response.getWriter().write("Le nombre de places doit être compris entre 2 et 9");
            }
        }
        catch(ServiceException e){
            e.printStackTrace();
        }


    }

}
