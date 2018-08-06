package ro.c.farmerandprovider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.c.farmerandprovider.DTO.FarmerRequest;
import ro.c.farmerandprovider.models.Provider;
import ro.c.farmerandprovider.models.ProviderStatistics;
import ro.c.farmerandprovider.repository.ProviderRepository;
import ro.c.farmerandprovider.repository.ProviderStatisticsRepository;
import ro.c.farmerandprovider.utils.Constants;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderService {

    @Autowired
    ProviderRepository providerRepository;
    @Autowired
    ProviderStatisticsRepository providerStatisticsRepository;

    public Provider createProvider(Provider provider){
        return providerRepository.save(provider);
    }


    public void createOneProviderStatForOneUtility(long idFarmer, long idProvider, double price, String typeUtility){

        ProviderStatistics newProviderStatistics = new ProviderStatistics(idFarmer,idProvider, typeUtility);
        ProviderStatistics oldProviderStatistics = providerStatisticsRepository.getStatsThatAlreadyExists(idFarmer, idProvider, typeUtility);
        if(oldProviderStatistics!=null){
            newProviderStatistics.setTotalMoneyAccumulated(newProviderStatistics.getTotalMoneyAccumulated()+price);
            newProviderStatistics.setId(oldProviderStatistics.getId());
            providerStatisticsRepository.save(newProviderStatistics);
        }
        else
        {
            newProviderStatistics.setTotalMoneyAccumulated(price);
            providerStatisticsRepository.save(newProviderStatistics);
        }
    }

    public void createProviderStatsDependingOnRequest(FarmerRequest farmerRequest, Provider provider ){
        if(farmerRequest.isTransporting())
            createOneProviderStatForOneUtility(farmerRequest.getIdFarmer(), farmerRequest.getIdProviderOptionalNeededForBuying(),(double)farmerRequest.hectareToEquipment()*provider.getPriceTransporting(), "Transporting");
        if(farmerRequest.isTilling())
            createOneProviderStatForOneUtility(farmerRequest.getIdFarmer(), farmerRequest.getIdProviderOptionalNeededForBuying(), (double) farmerRequest.hectareToEquipment()*provider.getPriceHarvesting(), "Harvesting");
        if(farmerRequest.isHarvesting())
            createOneProviderStatForOneUtility(farmerRequest.getIdFarmer(), farmerRequest.getIdProviderOptionalNeededForBuying(), (double) farmerRequest.hectareToEquipment()*provider.getPriceTilling(), "Tilling");
        if(farmerRequest.isSpraying())
            createOneProviderStatForOneUtility(farmerRequest.getIdFarmer(), farmerRequest.getIdProviderOptionalNeededForBuying(),  (double) farmerRequest.hectareToEquipment()*provider.getPriceSpraying(), "Spraying");
    }
    public String providerStatsTotal(Long idProvider){

        Double totalHarvesting =0.0, totalTilling =0.0, totalTransporting =0.0, totalSpraying =0.0;
        List<ProviderStatistics> providerStatisticsList = providerStatisticsRepository.getByProviderId(idProvider);
        if(!providerStatisticsList.isEmpty()) {
            for (ProviderStatistics providerStatistics : providerStatisticsList)
                switch (providerStatistics.getServiceType()) {
                    case "Harvesting": {
                        totalHarvesting += providerStatistics.getTotalMoneyAccumulated();
                    }
                    break;
                    case "Tilling": {
                        totalTilling += providerStatistics.getTotalMoneyAccumulated();
                    }
                    break;
                    case "Transporting": {
                        totalTransporting += providerStatistics.getTotalMoneyAccumulated();
                    }
                    break;
                    case "Spraying": {
                        totalSpraying += providerStatistics.getTotalMoneyAccumulated();
                    }
                    break;
                }
            return Constants.allStatsForOneProvider(totalTilling,totalHarvesting,totalSpraying,totalTransporting,
                         providerRepository.findById(providerStatisticsList.get(0).getId()).get().getUsername());
        }
        else
            return Constants.errorCodeNull;
    }
}
