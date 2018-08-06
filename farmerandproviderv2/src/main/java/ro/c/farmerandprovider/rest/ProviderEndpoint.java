package ro.c.farmerandprovider.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.c.farmerandprovider.DTO.FarmerRequest;
import ro.c.farmerandprovider.models.Provider;
import ro.c.farmerandprovider.service.ProviderService;

@RestController
@CrossOrigin
@RequestMapping(value = "/provider")
public class ProviderEndpoint {

    @Autowired
    ProviderService providerService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createProvider(@RequestBody Provider provider){
        return ResponseEntity.ok(providerService.createProvider(provider));
    }

    @RequestMapping(value = "/viewStats", method = RequestMethod.POST)
    public ResponseEntity<?> viewStats(@RequestBody Long idProvider){
        return ResponseEntity.ok(providerService.providerStatsTotal(idProvider));
    }
}
