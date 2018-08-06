package ro.c.farmerandprovider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.c.farmerandprovider.DTO.FarmerRequest;
import ro.c.farmerandprovider.models.Farmer;
import ro.c.farmerandprovider.models.FarmerStatistics;
import ro.c.farmerandprovider.models.Provider;
import ro.c.farmerandprovider.repository.FarmerRepository;
import ro.c.farmerandprovider.repository.FarmerStatisticsRepository;
import ro.c.farmerandprovider.utils.Constants;

import java.util.ArrayList;
import java.util.List;

@Service
public class FarmerService {

    @Autowired
    FarmerRepository farmerRepository;
    @Autowired
    FarmerStatisticsRepository farmerStatisticsRepository;

    public Farmer createFarmer(Farmer farmer){
        return farmerRepository.save(farmer);
    }

    public String addFunds(Long idFarmer, Double moneyy){

        Farmer farmer;
        if(farmerRepository.findById(idFarmer).isPresent()) {
            farmer = farmerRepository.findById(idFarmer).get();
            farmer.setFunds(farmer.getFunds()+moneyy);
            farmerRepository.save(farmer);
            return Constants.successfulOperation;
        }
        else
            return Constants.errorCodeNotInDT;
    }

    public void farmerAddStats(FarmerRequest farmerRequest, Provider provider){

        FarmerStatistics farmerStatistics = farmerStatisticsRepository.getByIdFarmerAndIdProvider(farmerRequest.getIdFarmer(),farmerRequest.getIdProviderOptionalNeededForBuying());
        if(farmerStatistics==null) {
            farmerStatistics = new FarmerStatistics(farmerRequest.getIdFarmer(),farmerRequest.getIdProviderOptionalNeededForBuying());
            if(farmerRequest.isHarvesting())
                farmerStatistics.setTotalHarvesting((double) farmerRequest.hectareToEquipment()*provider.getPriceHarvesting());
            else
                farmerStatistics.setTotalHarvesting(0);
            if(farmerRequest.isTilling())
                farmerStatistics.setTotalTilling((double)farmerRequest.hectareToEquipment()*provider.getPriceTilling());
            else
                farmerStatistics.setTotalTilling(0);
            if(farmerRequest.isSpraying())
                farmerStatistics.setTotalSpraying((double)farmerRequest.hectareToEquipment()*provider.getPriceSpraying());
            else
                farmerStatistics.setTotalSpraying(0);
            if(farmerRequest.isTransporting())
                farmerStatistics.setTotalTransporting((double) farmerRequest.hectareToEquipment()*provider.getPriceTransporting());
            else
                farmerStatistics.setTotalTransporting(0);
        }
        else{
            if(farmerRequest.isTransporting())
                farmerStatistics.setTotalTransporting(farmerStatistics.getTotalTransporting()+(double) farmerRequest.hectareToEquipment()*provider.getPriceTransporting());
            if(farmerRequest.isTilling())
                farmerStatistics.setTotalTilling(farmerStatistics.getTotalTilling()+(double)farmerRequest.hectareToEquipment()*provider.getPriceTilling());
            if(farmerRequest.isHarvesting())
                farmerStatistics.setTotalHarvesting(farmerStatistics.getTotalHarvesting()+(double) farmerRequest.hectareToEquipment()*provider.getPriceHarvesting());
            if(farmerRequest.isSpraying())
                farmerStatistics.setTotalSpraying(farmerStatistics.getTotalSpraying()+(double)farmerRequest.hectareToEquipment()*provider.getPriceSpraying());
        }
        farmerStatisticsRepository.save(farmerStatistics);
    }

    public ArrayList<String> viewFarmerAllStats(Long idFarmer){
        List<FarmerStatistics> farmerStatisticsList = farmerStatisticsRepository.getByIdFarmer(idFarmer);
        ArrayList<String> response = new ArrayList<>();
        for(FarmerStatistics farmerStatistics : farmerStatisticsList){
            String string ="The farmer with id: "+idFarmer+" has rented from provider with id: "+farmerStatistics.getIdProvider()+"."
                            +" Tilling: "+farmerStatistics.getTotalTilling()+" Harvesting: "+farmerStatistics.getTotalHarvesting()
                            +" Spraying: "+farmerStatistics.getTotalSpraying()+" Transporting: "+farmerStatistics.getTotalTransporting();
            response.add(string);
        }
        return response;
    }
}
