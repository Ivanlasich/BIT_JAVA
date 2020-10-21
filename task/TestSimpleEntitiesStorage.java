package task;


import org.junit.Test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import static junit.framework.TestCase.assertTrue;

public class TestSimpleEntitiesStorage {
    @Test
    public void testSave() {
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager);
        Account account2 = new DebitCard(2, transactionManager);
        Account account3 = new DebitCard(3, transactionManager);
        Account account4 = new DebitCard(4, transactionManager);
        Account account5 = new DebitCard(5, transactionManager);
        KeyExtractor keyExtractor = new AccountKeyExtractor();
        SimpleEntitiesStorage simpleEntitiesStorage = new SimpleEntitiesStorage(keyExtractor);
        simpleEntitiesStorage.save(account1);
        simpleEntitiesStorage.save(account2);
        simpleEntitiesStorage.save(account3);
        simpleEntitiesStorage.save(account4);
        simpleEntitiesStorage.save(account5);
        List list = simpleEntitiesStorage.findAll();
        List arrayList = new ArrayList();
        arrayList.add(account1);
        arrayList.add(account2);
        arrayList.add(account3);
        arrayList.add(account4);
        arrayList.add(account5);
        assertTrue(arrayList.equals(list));
    }

    @Test
    public void saveAll() {
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager);
        Account account2 = new DebitCard(2, transactionManager);
        Account account3 = new DebitCard(3, transactionManager);
        Account account4 = new DebitCard(4, transactionManager);
        Account account5 = new DebitCard(5, transactionManager);
        KeyExtractor keyExtractor = new AccountKeyExtractor();
        SimpleEntitiesStorage simpleEntitiesStorage = new SimpleEntitiesStorage(keyExtractor);
        List arrayList = new ArrayList();
        arrayList.add(account1);
        arrayList.add(account2);
        arrayList.add(account3);
        arrayList.add(account4);
        arrayList.add(account5);
        simpleEntitiesStorage.saveAll(arrayList);
        List list = simpleEntitiesStorage.findAll();
        assertTrue(arrayList.equals(list));
    }

    @Test
    public void testFindByKey() {
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager);
        Account account2 = new DebitCard(2, transactionManager);
        Account account3 = new DebitCard(3, transactionManager);
        Account account4 = new DebitCard(4, transactionManager);
        Account account5 = new DebitCard(5, transactionManager);
        KeyExtractor keyExtractor = new AccountKeyExtractor();
        SimpleEntitiesStorage simpleEntitiesStorage = new SimpleEntitiesStorage(keyExtractor);
        List arrayList = new ArrayList();
        arrayList.add(account1);
        arrayList.add(account2);
        arrayList.add(account3);
        arrayList.add(account4);
        arrayList.add(account5);
        simpleEntitiesStorage.saveAll(arrayList);
        Long index = new Long(2);
        Object account = simpleEntitiesStorage.findByKey(index);
        assertTrue(account.equals(account2));
    }

    @Test
    public void testFindAll() {
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager);
        Account account2 = new DebitCard(2, transactionManager);
        Account account3 = new DebitCard(3, transactionManager);
        KeyExtractor keyExtractor = new AccountKeyExtractor();
        SimpleEntitiesStorage simpleEntitiesStorage = new SimpleEntitiesStorage(keyExtractor);
        simpleEntitiesStorage.save(account1);
        simpleEntitiesStorage.save(account2);
        simpleEntitiesStorage.save(account3);
        List list = simpleEntitiesStorage.findAll();
        List arrayList = new ArrayList();
        arrayList.add(account1);
        arrayList.add(account2);
        arrayList.add(account3);
        assertTrue(arrayList.equals(list));
    }

    @Test
    public void testDeleteByKey() {
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager);
        Account account2 = new DebitCard(2, transactionManager);
        Account account3 = new DebitCard(3, transactionManager);
        Account account4 = new DebitCard(4, transactionManager);
        Account account5 = new DebitCard(5, transactionManager);
        KeyExtractor keyExtractor = new AccountKeyExtractor();
        SimpleEntitiesStorage simpleEntitiesStorage = new SimpleEntitiesStorage(keyExtractor);
        simpleEntitiesStorage.save(account1);
        simpleEntitiesStorage.save(account2);
        simpleEntitiesStorage.save(account3);
        simpleEntitiesStorage.save(account4);
        simpleEntitiesStorage.save(account5);
        simpleEntitiesStorage.deleteByKey(new Long(3));
        List list = simpleEntitiesStorage.findAll();
        List arrayList = new ArrayList();
        arrayList.add(account1);
        arrayList.add(account2);
        arrayList.add(account4);
        arrayList.add(account5);
        assertTrue(arrayList.equals(list));
    }

    @Test
    public void testDeleteAll() {
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new DebitCard(1, transactionManager);
        Account account2 = new DebitCard(2, transactionManager);
        Account account3 = new DebitCard(3, transactionManager);


        KeyExtractor keyExtractor = new AccountKeyExtractor();
        SimpleEntitiesStorage simpleEntitiesStorage = new SimpleEntitiesStorage(keyExtractor);

        simpleEntitiesStorage.save(account1);
        simpleEntitiesStorage.save(account2);
        simpleEntitiesStorage.save(account3);

        List deleteKeys = new ArrayList();
        deleteKeys.add(new Long(1));
        deleteKeys.add(new Long(3));

        simpleEntitiesStorage.deleteAll(deleteKeys);
        List list = simpleEntitiesStorage.findAll();
        List arrayList = new ArrayList();


        arrayList.add(account2);
        assertTrue(arrayList.equals(list));
    }

}
