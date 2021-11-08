package com.AndersonHsieh.WMMA.APILayer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//use url = http://localhost:8080/api/v1/transactionrecord
@RequestMapping(path = "api/v1/transactionrecord")
@RestController
public class TransactionController {

    @GetMapping
    public String getTransactionRecord(){

        return "fetching transaction";
    }
}
