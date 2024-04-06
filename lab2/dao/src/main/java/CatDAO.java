import Entities.Cat;
import Entities.Owner;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


public class CatDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("labb2");

    private final EntityManagerFactory entityManagerFactory;
    public CatDAO(EntityManagerFactory emf) {
        entityManagerFactory = emf;
    }
    public Cat findByName(String name) {

        Cat cat = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {

            transaction.begin();
            cat = entityManager.find(Cat.class, name);
            transaction.commit();

        } catch (Exception e) {

            if (transaction.isActive()) transaction.rollback();
        } finally {

            entityManager.close();
        }

        return cat;
    }

    public void saveCat(Cat cat) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();;
        try {
            transaction.begin();
            entityManager.persist(cat);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            entityManager.close();
        }
    }
    public void updateCat(Cat cat) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

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
        } finally {
            entityManager.close();
        }
    }

    public void deleteCat(Cat cat) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
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
        } finally {
            entityManager.close();
        }
    }

    public void addFriendship(Cat cat, Cat anotherCat) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
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
        } finally {
            entityManager.close();
        }

    }
    public void updateOwner(Cat cat, Owner owner) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
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
        } finally {
            entityManager.close();
        }
    }
}
