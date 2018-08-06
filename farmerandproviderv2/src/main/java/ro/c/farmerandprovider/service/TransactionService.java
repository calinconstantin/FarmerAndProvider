package ro.c.farmerandprovider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.c.farmerandprovider.DTO.BestOffer;
import ro.c.farmerandprovider.DTO.FarmerRequest;
import ro.c.farmerandprovider.models.*;
import ro.c.farmerandprovider.repository.BorrowedEquipmentRepository;
import ro.c.farmerandprovider.repository.FarmerRepository;
import ro.c.farmerandprovider.repository.ProviderRepository;
import ro.c.farmerandprovider.utils.Constants;

@Service
public class TransactionService {

    @Autowired
    FarmerRepository farmerRepository;
    @Autowired
    ProviderRepository providerRepository;
    @Autowired
    BorrowedEquipmentRepository borrowedEquipmentRepository;
    @Autowired
    ProviderService providerService;
    @Autowired
    FarmerService farmerService;

    public BestOffer bestProviderOffer(FarmerRequest farmerRequest){

        double totalPrice, bestPrice = Integer.MAX_VALUE;
        BestOffer bestOffer = new BestOffer();

        for(Provider provider: providerRepository.findAll())
        {
            totalPrice = farmerRequest.requestToPrice(provider);

            if(totalPrice ==-1)
                continue;
            if(totalPrice==bestPrice)
                bestOffer.getBestOfferList().add(provider);
            if(totalPrice<bestPrice){
                bestPrice = totalPrice;
                bestOffer.getBestOfferList().clear();
                bestOffer.getBestOfferList().add(provider);
                bestOffer.setPrice(bestPrice);
            }
        }
        if(bestPrice!=Integer.MAX_VALUE){
            return bestOffer;
        } else
            return null;
    }

    public String buyOffer(FarmerRequest farmerRequest) {

        Farmer farmer = farmerRepository.findById(farmerRequest.getIdFarmer()).get();
        Provider provider = providerRepository.findById(farmerRequest.getIdProviderOptionalNeededForBuying()).get();

        if(farmer.getFunds() < farmerRequest.requestToPrice(provider))
           return Constants.notEnoughFunds;
        if(equipmentToRent(farmerRequest,provider,farmer).equals(Constants.successfulOperation)){
            farmer.setFunds(farmer.getFunds()-farmerRequest.requestToPrice(provider));
            farmerRepository.save(farmer);
            farmerService.farmerAddStats(  farmerRequest, provider);
            providerService.createProviderStatsDependingOnRequest(farmerRequest,provider);
            return Constants.successfulOperation;
        }
       else
           return Constants.errorCodeNull;
    }

    public String equipmentBackToProviderStock (Long idFarmer){

        BorrowedEquipment borrowedEquipment=borrowedEquipmentRepository.getByFarmerId(idFarmer);
        if(borrowedEquipment==null)
            return Constants.errorCodeNull;

        borrowedEquipment.setForTilling(0);
        borrowedEquipment.setForHarvesting(0);
        borrowedEquipment.setForSpraying(0);
        borrowedEquipment.setForTransporting(0);

        Provider provider = providerRepository.findById(borrowedEquipment.getIdProvider()).get() ;
        provider.setEquipmentTilling(provider.getEquipmentTilling() + borrowedEquipment.getForTilling());
        provider.setEquipmentHarvesting(provider.getEquipmentHarvesting() + borrowedEquipment.getForHarvesting());
        provider.setEquipmentSpraying(provider.getEquipmentSpraying() + borrowedEquipment.getForSpraying());
        provider.setEquipmentTransporting(provider.getEquipmentTransporting() + borrowedEquipment.getForTransporting());

        borrowedEquipmentRepository.save(borrowedEquipment);
        providerRepository.save(provider);
        return Constants.successfulOperation;
    }

    public String equipmentToRent(FarmerRequest farmerRequest, Provider provider, Farmer farmer){
        BorrowedEquipment borrowedEquipment = new BorrowedEquipment(farmer.getId(), provider.getId());

        if (farmerRequest.isTransporting()) {
            if(provider.getEquipmentTransporting() - farmerRequest.hectareToEquipment()>=0)
                provider.setEquipmentTransporting(provider.getEquipmentTransporting() - farmerRequest.hectareToEquipment());
            else
                return Constants.errorCodeNull;
            borrowedEquipment.setForTransporting(farmerRequest.hectareToEquipment());

        }
        if (farmerRequest.isSpraying()) {
            if(provider.getEquipmentSpraying() - farmerRequest.hectareToEquipment()>=0)
                provider.setPriceSpraying(provider.getEquipmentTransporting() - farmerRequest.hectareToEquipment());
            else
                return Constants.errorCodeNull;
            borrowedEquipment.setForSpraying(farmerRequest.hectareToEquipment());
        }
        if (farmerRequest.isHarvesting()){
            if(provider.getEquipmentHarvesting() - farmerRequest.hectareToEquipment()>=0)
                provider.setEquipmentHarvesting(provider.getEquipmentTransporting() - farmerRequest.hectareToEquipment());
            else
                return Constants.errorCodeNull;
            borrowedEquipment.setForHarvesting(farmerRequest.hectareToEquipment());

        }
        if (farmerRequest.isTilling()) {
            if(provider.getEquipmentTilling() - farmerRequest.hectareToEquipment()>=0)
                provider.setEquipmentTransporting(provider.getEquipmentTilling() - farmerRequest.hectareToEquipment());
            else
                return Constants.errorCodeNull;
            borrowedEquipment.setForTilling(farmerRequest.hectareToEquipment());

        }
        borrowedEquipmentRepository.save(borrowedEquipment);
        providerRepository.save(provider);
        return Constants.successfulOperation;
    }
}
