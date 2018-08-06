package ro.c.farmerandprovider.DTO;

import org.springframework.beans.factory.annotation.Autowired;
import ro.c.farmerandprovider.models.Provider;
import ro.c.farmerandprovider.repository.FarmerRepository;


public class FarmerRequest {

    @Autowired
    FarmerRepository farmerRepository;

    private Long idFarmer;
    private Long idProviderOptionalNeededForBuying;
    private boolean tilling;
    private boolean harvesting;
    private boolean spraying;
    private boolean transporting;

    public Long getIdProviderOptionalNeededForBuying() {
        return idProviderOptionalNeededForBuying;
    }

    public void setIdProviderOptionalNeededForBuying(Long idProviderOptionalNeededForBuying) {
        this.idProviderOptionalNeededForBuying = idProviderOptionalNeededForBuying;
    }

    public Long getIdFarmer() {
        return idFarmer;
    }

    public void setIdFarmer(Long idFarmer) {
        this.idFarmer = idFarmer;
    }

    public boolean isTilling() {
        return tilling;
    }

    public void setTilling(boolean tilling) {
        this.tilling = tilling;
    }

    public boolean isHarvesting() {
        return harvesting;
    }

    public void setHarvesting(boolean harvesting) {
        this.harvesting = harvesting;
    }

    public boolean isSpraying() {
        return spraying;
    }

    public void setSpraying(boolean spraying) {
        this.spraying = spraying;
    }

    public boolean isTransporting() {
        return transporting;
    }

    public void setTransporting(boolean Transporting) {
        this.transporting = Transporting;
    }

    public int hectareToEquipment(){

        int hectare =0;
        if(farmerRepository.findById(idFarmer).isPresent())
             hectare = farmerRepository.findById(idFarmer).get().getHectare();
        else
            return hectare;
        if(hectare<5){
            return 1;
        } else if(hectare%5==0){
            return hectare/5;
        } else {
            return hectare/5 + 1;
        }
    }


    public double requestToPrice(Provider provider){

        double totalPrice=0;

        if(tilling)
            if (provider.getEquipmentTilling() >= hectareToEquipment()) {
                totalPrice = totalPrice + provider.getPriceTilling()*hectareToEquipment();
            } else
                return -1;
        if(harvesting)
            if (provider.getEquipmentHarvesting() >= hectareToEquipment()) {
                totalPrice = totalPrice + provider.getEquipmentHarvesting()*hectareToEquipment();
            } else
                return -1;
        if(spraying)
            if (provider.getEquipmentSpraying() >= hectareToEquipment()) {
                totalPrice = totalPrice + provider.getPriceSpraying()*hectareToEquipment();
            } else
                return -1;
        if(transporting)
            if (provider.getEquipmentTransporting() >= hectareToEquipment()) {
                totalPrice = totalPrice + provider.getPriceTransporting()*hectareToEquipment();
            } else
                return -1;

        return totalPrice;
    }
}
