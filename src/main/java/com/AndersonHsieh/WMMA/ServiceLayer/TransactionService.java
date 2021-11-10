package com.AndersonHsieh.WMMA.ServiceLayer;

import com.AndersonHsieh.WMMA.DataAccessLayer.TransactionRepository;
import com.AndersonHsieh.WMMA.Model.Transaction;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository repository) {
        this.transactionRepository = repository;
    }

    public List<Transaction> getTransaction(String from, String to, String all) {
        if ("true".equals(all)) {
            return transactionRepository.findAll();
        } else {
            String[] fromSplit = from.split("-");
            String[] toSplit = to.split("-");
            if (fromSplit.length != 3 && toSplit.length != 3) {
                throw new IllegalArgumentException("Incorrect time request parameter format");
            }else{
                checkDateTimeFormatCorrectly(fromSplit);
                checkDateTimeFormatCorrectly(toSplit);
                //reach here if no exception is thrown
                return transactionRepository.findTransactionByDateInterval(from, to);
            }
        }
    }

    public void addTransaction(String name, Double amount) {
        transactionRepository.save(
                new Transaction(name,amount, LocalDateTime.now())
        );
    }


    @Transactional
    //doesn't use a query(a method like save(Entity))from the repository class
    //because the entity goes into a management state
    public void editTransaction(Long transactionId, String name, Double amount, String time) {
        //get the transaction from repository
        Transaction editedTransaction = transactionRepository.findById(transactionId).orElseThrow(() -> new IllegalStateException(
                "Transaction with id: " + transactionId + "does not exist"
        ));

        //do the checks here instead of in API layer for reusability and code structure
        LocalDateTime parsedTime = parseStringToLocalDateTime(time);

        if(name!=null&&name.length()>0){
            editedTransaction.setName(name);
        }

        if(amount!=null){
            //the balance of the transaction can be 0
            editedTransaction.setAmount(amount);
        }

        if(parsedTime!=null){
            editedTransaction.setTime(parsedTime);
        }

    }


    public void deleteTransaction(Long transactionId) {
        Optional<Transaction> exists = transactionRepository.findTransactionById(transactionId);
        if(exists.isPresent()) {
            transactionRepository.deleteById(transactionId);
        }else{
            throw new IllegalStateException("Transaction with id: " + transactionId + "does not exist");
        }
    }

    public LocalDateTime parseStringToLocalDateTime(String time){
        if(time != null) {
            String[] parts = time.split("-");
            if(parts.length!=6){
                throw new IllegalArgumentException("Incorrect time request parameter format");
            }else{
            checkDateTimeFormatCorrectly(parts);}
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

    public void checkDateTimeFormatCorrectly(String[] timeArray) {
            for (int i = 0; i < timeArray.length; i++) {
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
