package com.unmsm.denuncias.util;

import org.hibernate.type.TrueFalseType;
import org.springframework.beans.factory.parsing.DefaultsDefinition;

import aj.org.objectweb.asm.Label;

public class Haversine {
	
	private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM

    public static double distance(double startLat, double startLong,
                                  double endLat, double endLong) {

        double dLat  = Math.toRadians((endLat - startLat));
        double dLong = Math.toRadians((endLong - startLong));

        startLat = Math.toRadians(startLat);
        endLat   = Math.toRadians(endLat);

        double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c; // <-- d
    }

    public static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }
    
    
    public static boolean isNear(double startLat, double startLong,
            double endLat, double endLong, double distanceMax) {
    	
    	double distance = distance(startLat, startLong, endLat, endLong);
    	
    	System.out.println("Distancia entre usuario y denuncia: "+distance);
    	
    	return distance <= distanceMax ? true : false;
    	
    }
	
}
