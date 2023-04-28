package com.epf.rentmanager.servlet;


import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

import com.epf.rentmanager.validateur.ValidateurClient;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/users/create")
public class CreateClientServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Autowired
    ClientService clientService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            Client client1 = new Client();
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String email = request.getParameter("email");
            LocalDate date = LocalDate.parse(request.getParameter("naissance"));
            client1.setNom(nom);
            client1.setPrenom(prenom);
            client1.setEmail(email);
            client1.setNaissance(date);
            if (ValidateurClient.isLegal(client1) && ValidateurClient.emailValide(client1) && ValidateurClient.nomValide(client1)){
                clientService.create(client1);
                response.sendRedirect("../users");
            }
            else {
                response.getWriter().write("Attention, le client doit avoir plus de 18 ans, son adresse mail ne doit " +
                        "pas être déjà prise, et son nom et prénom doivent faire au moins 3 caractères.");
            }
        }
        catch(ServiceException e){
            e.printStackTrace();
        }

    }


}
