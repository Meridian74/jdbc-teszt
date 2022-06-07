package service;

import model.Transaction;
import repository.TransactionDAO;
import repository.TransactionDAOpostgre;

import java.util.List;

public class TransactionSaveIntoDB {

    private final TransactionDAO transactionDAO = new TransactionDAOpostgre();

    public void saveTransactionsIntoDB(List<Transaction> transactions) {
        transactionDAO.saveAll(transactions);
    }

}