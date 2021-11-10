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
    //use http://localhost:8080/api/v1/transaction?from=20210708&to=20211011&all=true
    @GetMapping
    public List<Transaction> getTransactionRecord(
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to,
            @RequestParam(required = true)  String all
    ){
        //if "all" is the exact character "true", then return all the transactions.
        //else search by time interval
        return transactionService.getTransaction(from,to, all);
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
        transactionService.editTransaction(transactionId,name,amount,time);
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
        transactionService.deleteTransaction(transactionId);
    }


}
