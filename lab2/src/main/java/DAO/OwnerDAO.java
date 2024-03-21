package DAO;

import Entities.Owner;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class OwnerDAO {

    private EntityManager entityManager;
    public OwnerDAO(EntityManager em) {
        entityManager = em;
    }
    public Owner findById(Integer id) {

        Owner owner = null;

        EntityTransaction transaction = entityManager.getTransaction();
        try {

            transaction.begin();
            owner = entityManager.find(Owner.class, id);
            transaction.commit();

        } catch (Exception e) {

            if (transaction.isActive()) transaction.rollback();

        }

        return owner;
    }

    public void saveOwner(Owner owner) {


        EntityTransaction transaction = entityManager.getTransaction();;
        try {
            transaction.begin();
            entityManager.persist(owner);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
    public void updateOwner(Owner owner) {

        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(owner);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void deleteOwner(Owner owner) {


        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(owner);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
