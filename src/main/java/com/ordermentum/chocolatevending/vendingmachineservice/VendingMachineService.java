package com.ordermentum.chocolatevending.vendingmachineservice;

import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VendingMachineService {

    private static final Logger logger = LoggerFactory.getLogger(VendingMachineService.class);



    public VendingTxn insertCoinAndGetSelection(int coinValue) {
        if (validateCoinValue(coinValue)){
            return new VendingTxn(coinValue, getVeganChocolateListForACoinValue(coinValue), "");
        } else {
            return new VendingTxn(coinValue, ImmutableList.of(), "Coin value is not the right denomination, please check");
        }
    }

    public VendingTxn buyChocolate(String chocolateName, int coinValue){
        Optional<VeganChocolate> chocolateToBuy = getVeganChocolateList()
                .stream()
                .filter(veganChocolate -> chocolateName.equalsIgnoreCase(veganChocolate.getName()))
                .findFirst();
        if(chocolateToBuy.isPresent()){
            int coinValueRemaining = coinValue - chocolateToBuy.get().getPrice();
            return new VendingTxn(coinValueRemaining, getVeganChocolateListForACoinValue(coinValueRemaining), "");
        } else {
            return new VendingTxn(coinValue, getVeganChocolateListForACoinValue(coinValue), "Unable to find the chocolate requested in the system");
        }
    }

    private ImmutableList<VeganChocolate> getVeganChocolateListForACoinValue(int coinValue){
        return getVeganChocolateList()
                .stream()
                .filter(chocolate -> coinValue >= chocolate.getPrice())
                .collect(ImmutableList.toImmutableList());
    }


    public boolean validateCoinValue(int coinValue) {
        logger.debug("Validating currency value {}", coinValue);
        return (coinValue % 5) == 0;
    }

    public ImmutableList<VeganChocolate> getVeganChocolateList(){
        VeganChocolate veganChocolate1 = new VeganChocolate("Caramel", 250);
        VeganChocolate veganChocolate2 = new VeganChocolate("Hazelnut", 310);
        VeganChocolate veganChocolate3 = new VeganChocolate("Organic Raw", 200);
        return ImmutableList.of(veganChocolate1, veganChocolate2, veganChocolate3);
    }
}
