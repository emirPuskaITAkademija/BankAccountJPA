package entity;

import org.eclipse.persistence.exceptions.EclipseLinkException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.awt.HeadlessException;
import java.util.Collections;
import java.util.List;

public class ActiveBankAccount {
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("BANK_PU");
    public void save(){
        try{
            EntityManager entityManager = EMF.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(this);
            entityManager.getTransaction().commit();
        }catch (EclipseLinkException e){
            System.err.println(e.getMessage());
        }
    }

    public void update(){
        try{
            EntityManager entityManager = EMF.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(this);
            entityManager.getTransaction().commit();
        }catch (EclipseLinkException e){
            System.err.println(e.getMessage());
        }
    }

    public void delete(){
        try{
            EntityManager entityManager = EMF.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(this);
            entityManager.getTransaction().commit();
        }catch (EclipseLinkException e){
            System.err.println(e.getMessage());
        }
    }

    public static List<BankAccount> loadAll(){
        try{
            EntityManager entityManager = EMF.createEntityManager();
            entityManager.getTransaction().begin();
            Query query = entityManager.createNamedQuery("BankAccount.findAll");
            entityManager.getTransaction().commit();
            return query.getResultList();
        }catch (EclipseLinkException e){
            System.err.println(e.getMessage());
            return Collections.emptyList();
        }
    }

    public static BankAccount loadByAccountNumber(String accountNumber){
        try{
            EntityManager entityManager = EMF.createEntityManager();
            entityManager.getTransaction().begin();
//            Query query = entityManager.createNamedQuery("BankAccount.findByAccountNumber");
//            query.setParameter("accountNumber", accountNumber);
            BankAccount bankAccount = entityManager.find(BankAccount.class, accountNumber);
            entityManager.getTransaction().commit();
            return bankAccount;
        }catch (EclipseLinkException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static List<BankAccount> loadByAmountGreaterThan(Double amount){
        try{
            EntityManager entityManager = EMF.createEntityManager();
            entityManager.getTransaction().begin();
            Query query = entityManager.createNamedQuery("BankAccount.findByAmountGreaterThan");
            query.setParameter("amount", amount);
            entityManager.getTransaction().commit();
            return query.getResultList();
        }catch (EclipseLinkException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
