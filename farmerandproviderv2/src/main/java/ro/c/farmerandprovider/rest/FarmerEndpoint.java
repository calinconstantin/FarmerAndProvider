package ro.c.farmerandprovider.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.c.farmerandprovider.models.Farmer;
import ro.c.farmerandprovider.service.FarmerService;

@RestController
@CrossOrigin
@RequestMapping(value = "/farmer")
public class FarmerEndpoint {

    @Autowired
    FarmerService farmerService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createFarmer (@RequestBody Farmer farmer){
        return ResponseEntity.ok(farmerService.createFarmer(farmer));
    }

    @RequestMapping(value = "/addFunds", method = RequestMethod.POST)
    public ResponseEntity<?> addFunds (@RequestBody Long id,
                                       @RequestBody Double moneyy){
        return ResponseEntity.ok(farmerService.addFunds(id, moneyy));
    }

    @RequestMapping(value = "/viewStatsFarmer", method = RequestMethod.POST)
    public ResponseEntity<?> viewStatsFarmer (@RequestParam Long idFarmer){
        return ResponseEntity.ok(farmerService.viewFarmerAllStats(idFarmer));
    }
}
