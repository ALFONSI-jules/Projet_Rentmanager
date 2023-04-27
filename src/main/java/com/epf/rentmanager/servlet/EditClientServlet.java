package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/users/edit")
public class EditClientServlet extends HttpServlet{
    @Autowired
    private ClientService clientService;

    private static final long serialVersionUID = 1L;
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            request.setAttribute("user", clientService.findById(Integer.parseInt(request.getParameter("id"))));

        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/edit.jsp").forward(request, response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {
            Client client = new Client();
            client.setId(Integer.parseInt(request.getParameter("id")));
            client.setNom(request.getParameter("nom"));
            client.setPrenom(request.getParameter("prenom"));
            client.setEmail(request.getParameter("email"));
            client.setNaissance(LocalDate.parse(request.getParameter("naissance")));
            clientService.edit(client);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


        response.sendRedirect("../users");
    }
}
