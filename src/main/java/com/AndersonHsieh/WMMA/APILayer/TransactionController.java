package com.AndersonHsieh.WMMA.APILayer;

import com.AndersonHsieh.WMMA.Model.Transaction;
import com.AndersonHsieh.WMMA.ServiceLayer.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

//use url = http://localhost:8080/api/v1/transaction
@RequestMapping(path = "api/v1/transaction")
@RestController
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    //@Autowired is used for dependency injection, and is taken cared of by Spring framework
    private TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    //SELECT transactions to retrieve by date
    //use http://localhost:8080/api/v1/transaction?from=20210708&to=20211011
    @GetMapping
    public List<Transaction> getTransactionRecord(
            @RequestParam(required = true) String from,
            @RequestParam(required = true) String to
    ){

        return transactionService.getTransaction(from,to);
    }

    //use http://localhost:8080/api/v1/transaction/<id>?name=<name>&amount=<amount>&time=<2021-11-11-14-45-05>
    @PostMapping(path = "{transactionId}")
    public void editTransaction(
            @PathVariable(value = "transactionId", required = true) Long transactionId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double amount,
            @RequestParam(required = false) String time){
        //time must be in the format of '2021-11-09-13-44-28' corresponds to 'YYYY-MM-DD-HH-MM-SS'
        //T is a separator
        transactionService.editTransaction(transactionId,name,amount,parseStringToLocalDateTime(time));
    }

    //use http://localhost:8080/api/v1/transaction?name=<name>&amount=<amount>
    @PutMapping
    public void addTransaction(
            @RequestParam(required = true) String name,
            @RequestParam(required = true) Double amount
    ){
        transactionService.addTransaction(name, amount);
    }

    //use http://localhost:8080/api/v1/transaction/<id>
    @DeleteMapping(path = "{transactionId}")
    public void deleteTransaction(@PathVariable("transactionId") Long transactionId){

    }

    public LocalDateTime parseStringToLocalDateTime(String time){
        if(time != null) {
            String[] parts = time.split("-");
            checkTimeFormatCorrectly(parts);
            Integer[] convertedParts = convertPartsToInteger(parts);
            return LocalDateTime.of(convertedParts[0], convertedParts[1], convertedParts[2], convertedParts[3],convertedParts[4],convertedParts[5]);
        }
        return null;
    }

    public Integer[] convertPartsToInteger(String[] timeArray){
        //string format should already be checked in "checkTimeFormatCorrectly()" method
        //no need to check again here
        Integer[] results = {0,0,0,0,0,0};
        for(int i = 0;i<6;i++){
            results[i] = Integer.parseInt(timeArray[i]);
        }
        return results;
    }

    public void checkTimeFormatCorrectly(String[] timeArray) {
        if (timeArray.length != 6) {
            throw new IllegalArgumentException("Incorrect time request parameter format");
        } else {
            for (int i = 0; i < 6; i++) {
                //only two branches of if statements, use if instead of switch case
                if (i == 0) {
                    //first entry, which is the year, should be a 4 digit number
                    if (timeArray[i].length() != 4) {
                        throw new IllegalArgumentException("Incorrect time request parameter format");
                    }
                } else {
                    //other entries like month, date, hour, minutes, seconds are all 2 digits
                    if (timeArray[i].length() != 2) {
                        throw new IllegalArgumentException("Incorrect time request parameter format");
                    }
                }
            }

        }
    }
}
