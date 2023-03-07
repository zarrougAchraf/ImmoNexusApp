package tn.devteam.immonexus.Services;

import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Entities.UserAdress;
import tn.devteam.immonexus.Entities.VisitAdress;

@Service
public class DistanceService {
    
    // the radius of the Earth in kilometers
    private static final int EARTH_RADIUS_KM = 6371;

        public double calculateDistance(VisitAdress visitAdress, UserAdress userAdress) {
            double latDistance = Math.toRadians(visitAdress.getVisitAdressLatitude() - userAdress.getUserAdresstLatitude());
            double lonDistance = Math.toRadians(visitAdress.getVisitAdressLongitude() - userAdress.getUserAdressLongitude());
            double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                    + Math.cos(Math.toRadians(userAdress.getUserAdresstLatitude())) * Math.cos(Math.toRadians(visitAdress.getVisitAdressLatitude()))
                    * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            return EARTH_RADIUS_KM * c;

    } }


