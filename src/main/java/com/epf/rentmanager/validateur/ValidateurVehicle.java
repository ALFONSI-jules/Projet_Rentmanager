package com.epf.rentmanager.validateur;
import com.epf.rentmanager.AppConfiguration;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ReservationService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ValidateurVehicle {
    private static List<Reservation> liste;

    private static long differenceInDays=0;
    static ApplicationContext context=new AnnotationConfigApplicationContext(AppConfiguration.class);
    private static ReservationService reservationService = context.getBean(ReservationService.class);

    public static boolean voitureJour(Reservation reservation){
        try {
            liste = reservationService.findAll();
            for (Reservation r : liste){
                if (r.getVehicle_id() == reservation.getVehicle_id()){

                    if (reservation.getDebut().isAfter(r.getDebut()) && reservation.getDebut().isBefore(r.getFin()) ){
                        return false;
                    }
                    else if (reservation.getFin().isAfter(r.getDebut()) && reservation.getFin().isBefore(r.getFin()) ){
                        return false;
                    }
                    else if (reservation.getDebut().isEqual(r.getDebut()) || reservation.getDebut().isEqual(r.getFin()) ){
                        return false;
                    }
                    else if (reservation.getFin().isEqual(r.getDebut()) || reservation.getFin().isEqual(r.getFin()) ){
                        return false;
                    }
                }
            }
            return true;
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean resa7jours(Reservation reservation){
        LocalDate debut = reservation.getDebut();
        LocalDate fin = reservation.getFin();
        long differenceInDays2 = ChronoUnit.DAYS.between(debut, fin);
        return differenceInDays2 <= 7;
    }
    public static boolean nbPlaces_valide(Vehicle vehicle){
        int nb_places = vehicle.getNb_places();
        return nb_places >= 2 && nb_places <= 9;
    }
}
