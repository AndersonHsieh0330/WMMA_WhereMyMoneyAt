package com.AndersonHsieh.WMMA.ServiceLayer;

import com.AndersonHsieh.WMMA.DataAccessLayer.TransactionRepository;
import com.AndersonHsieh.WMMA.Model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository repository){
        this.transactionRepository = repository;
    }

    public List<Transaction> getTransaction(String from, String to){
        return transactionRepository.findTransactionByDateInterval(from, to);
    }
    public void addTransaction(String name, Double amount) {
        transactionRepository.save(
                new Transaction(name,amount, LocalDateTime.now())
        );
    }


    @Transactional
    //doesn't use a query(a method like save(Entity))from the repository class
    //because the entity goes into a management state
    public void editTransaction(Long transactionId, String name, Double amount, LocalDateTime time) {
        //get the transaction from repository
        Transaction editedTransaction = transactionRepository.findById(transactionId).orElseThrow(() -> new IllegalStateException(
                "Transaction with id: " + transactionId + "does not exist"
        ));

        if(name!=null&&name.length()>0){
            editedTransaction.setName(name);
        }

        if(amount!=null){
            //the balance of the transaction can be 0
            editedTransaction.setAmount(amount);
        }

        if(time!=null){
            editedTransaction.setTime(time);
        }

    }
}
