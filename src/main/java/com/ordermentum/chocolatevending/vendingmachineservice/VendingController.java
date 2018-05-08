package com.ordermentum.chocolatevending.vendingmachineservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class VendingController {

    private static final Logger logger = LoggerFactory.getLogger(VendingController.class);

    private VendingMachineService vendingMachineService;

    @Autowired
    public VendingController(VendingMachineService vendingMachineService) {
        this.vendingMachineService = vendingMachineService;
    }

    @GetMapping("/getWhatChocolatesICanBuy")
    @ResponseBody
    public ResponseEntity<VendingTxn> getWhatChocolatesICanBuy(@RequestParam("coinValue") int coinValue){
        logger.info("Received request to getWhatChocolatesICanBuy for coinValue {}", coinValue);
        try {
            VendingTxn vendingTxn = vendingMachineService.insertCoinAndGetSelection(coinValue);
            return "".equalsIgnoreCase(vendingTxn.getOptionalErrorMsg()) ? new ResponseEntity<>(vendingTxn, HttpStatus.OK) : new ResponseEntity<>(vendingTxn, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Error while running getWhatChocolatesICanBuy for value: {}", coinValue, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/buyChocolate")
    @ResponseBody
    public ResponseEntity<VendingTxn> buyChocolate(@RequestBody BuyPayload buyPayLoad){
        try {
            VendingTxn vendingTxn = vendingMachineService.buyChocolate(buyPayLoad.getChocolateName(), buyPayLoad.getCoinValue());
            return "".equalsIgnoreCase(vendingTxn.getOptionalErrorMsg()) ? new ResponseEntity<>(vendingTxn, HttpStatus.OK) : new ResponseEntity<>(vendingTxn, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Error while running buyChocolate for buyPayLoad: {}", buyPayLoad, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
