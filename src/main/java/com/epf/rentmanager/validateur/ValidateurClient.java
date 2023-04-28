package com.epf.rentmanager.validateur;

import com.epf.rentmanager.AppConfiguration;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;


public class ValidateurClient {
    private static List<Client> liste;
    static ApplicationContext context=new AnnotationConfigApplicationContext(AppConfiguration.class);
    private static ClientService clientService = context.getBean(ClientService.class);


    public static boolean isLegal(Client client){
        return client.getAge()>=18;
    }
    public static boolean emailValide(Client client){
        try {
            liste = clientService.findAll();
            for (Client c : liste){
                if (c.getEmail().equals(client.getEmail())){
                    return false;
                }
            }
            return true;
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
    public static boolean nomValide(Client client){
        return (client.getNom().length()>=3 && client.getPrenom().length()>=3);
    }
}
