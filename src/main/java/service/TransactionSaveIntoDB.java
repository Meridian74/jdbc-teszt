package service;

import model.Transaction;
import repository.TransactionDAO;
import java.util.List;

public class TransactionSaveIntoDB {

    TransactionDAO transactionDAO;

    public TransactionSaveIntoDB(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }


    public void saveTransactionsIntoDB(List<Transaction> transactions) {
        transactionDAO.saveAll(transactions);
    }

}