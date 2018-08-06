package ro.c.farmerandprovider.utils;

import org.springframework.beans.factory.annotation.Autowired;
import ro.c.farmerandprovider.repository.ProviderRepository;

public class Constants {

    public static final String errorCodeNotInDT ="The objecte you appelated in database doest not exist.";
    public static final String errorCodeNull ="Something is missing, thinking-emoji.";
    public static final String successfulOperation ="Horray! Everything worked, it is a miracle.";
    public static final String notEnoughFunds="You don't have enough moneyyy.";

    public static final String allStatsForOneProvider(double tilling, double harvesting, double spraying, double transporting, String name){
        return "The company: "+name+" has made: "+"Tilling: "+tilling+" RON"+" Harvesting: "+harvesting+" RON"
                                              +" Spraying: "+spraying+" RON"+" Transporting: "+transporting+" RON";
    }
}
