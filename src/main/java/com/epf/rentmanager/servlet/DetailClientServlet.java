package com.epf.rentmanager.servlet;


import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/users/details")
public class DetailClientServlet extends HttpServlet {


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

            request.setAttribute("nb_reservations", reservationService.findByClientId(Integer.parseInt(request.getParameter("id"))).size());
            request.setAttribute("reservations", reservationService.findByClientId(Integer.parseInt(request.getParameter("id"))));
            request.setAttribute("vehicle", vehicleService);
            request.setAttribute("user", clientService.findById(Integer.parseInt(request.getParameter("id"))));

        }
        catch(ServiceException e){
            e.printStackTrace();

        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/details.jsp").forward(request, response);

    }

}
