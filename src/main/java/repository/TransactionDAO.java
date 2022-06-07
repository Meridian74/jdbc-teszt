package repository;

import model.Transaction;

import java.util.List;

public interface TransactionDAO {
   void saveAll(List<Transaction> transactions);

}