package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users/delete")
public class DeleteClientServlet extends HttpServlet{

    @Autowired
    private ClientService clientService;
    @Autowired
    private ReservationService reservationService;

    private static List<Reservation> liste;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            liste = reservationService.findAll();
            if (liste.size()!=0){
                for (Reservation r : liste){
                    if (r.getClient_id() == Integer.parseInt(request.getParameter("id"))){
                        reservationService.delete(r.getId());
                        clientService.delete(Integer.parseInt(request.getParameter("id").toString()));
                    }
                    else{
                        clientService.delete(Integer.parseInt(request.getParameter("id").toString()));
                    }
                }
            }
            else{
                clientService.delete(Integer.parseInt(request.getParameter("id").toString()));
            }



        } catch (NumberFormatException | ServiceException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        response.sendRedirect("../users");
    }
}

