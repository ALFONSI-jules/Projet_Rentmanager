package com.epf.rentmanager.ui;
import java.time.LocalDate;

import com.epf.rentmanager.AppConfiguration;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class test {
    public static void main(String[] args){
        final int CE_QUE_JE_VEUX=2;
        ApplicationContext context=new AnnotationConfigApplicationContext(AppConfiguration.class);
        ClientService clientService = context.getBean(ClientService.class);
        try {
            System.out.println (clientService.findAll());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }
}
