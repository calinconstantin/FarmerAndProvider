package ro.c.farmerandprovider.DTO;

import ro.c.farmerandprovider.models.Provider;

import java.util.ArrayList;

public class BestOffer {
    private ArrayList<Provider> bestOfferList = new ArrayList<>();
    private double price;

    public ArrayList<Provider> getBestOfferList() {
        return bestOfferList;
    }

    public void setBestOfferList(ArrayList<Provider> bestOfferList) {
        this.bestOfferList = bestOfferList;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
