package DAO;

import Entities.Cat;
import Entities.Owner;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


public class CatDAO {


    private EntityManager entityManager;
    public CatDAO(EntityManager em) {
        entityManager = em;
    }
    public Cat findByName(String name) {

        Cat cat = null;

        EntityTransaction transaction = entityManager.getTransaction();
        try {

            transaction.begin();
            cat = entityManager.find(Cat.class, name);
            transaction.commit();

        } catch (Exception e) {

            if (transaction.isActive()) transaction.rollback();
        }

        return cat;
    }

    public void saveCat(Cat cat) {

        EntityTransaction transaction = entityManager.getTransaction();;
        try {
            transaction.begin();
            entityManager.persist(cat);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
    public void updateCat(Cat cat) {

        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(cat);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void deleteCat(Cat cat) {

        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(cat);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void addFriendship(Cat cat, Cat anotherCat) {

        EntityTransaction transaction = entityManager.getTransaction();;
        try {
            transaction.begin();
            cat.addFriend(anotherCat);
            entityManager.merge(cat);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }
    public void updateOwner(Cat cat, Owner owner) {
        EntityTransaction transaction = entityManager.getTransaction();;
        try {
            transaction.begin();
            cat.setOwner(owner);
            entityManager.merge(cat);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
