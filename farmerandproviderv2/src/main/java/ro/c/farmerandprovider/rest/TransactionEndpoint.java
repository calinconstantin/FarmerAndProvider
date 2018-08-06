package ro.c.farmerandprovider.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.c.farmerandprovider.DTO.FarmerRequest;
import ro.c.farmerandprovider.service.TransactionService;

@CrossOrigin
@RequestMapping(value="/transaction")
@RestController
public class TransactionEndpoint {

    @Autowired
    TransactionService transactionService;

    @RequestMapping(value = "/findBestProviderOffer", method = RequestMethod.POST)
    public ResponseEntity<?> findBestProviderOffer(@RequestBody FarmerRequest farmerRequest){
        return ResponseEntity.ok(transactionService.bestProviderOffer(farmerRequest));
    }

    @RequestMapping(value = "/buyProviderOffer", method = RequestMethod.POST)
    public ResponseEntity<?> buyProviderOffer(@RequestBody FarmerRequest farmerRequest){
        return ResponseEntity.ok(transactionService.buyOffer(farmerRequest));
    }

    @RequestMapping(value = "/returnRentedEquipment", method = RequestMethod.GET)
    public ResponseEntity<?> returnEquipment(@RequestParam Long idFarmer){
        return ResponseEntity.ok(transactionService.equipmentBackToProviderStock(idFarmer));
    }
}
