import Entities.Owner;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class OwnerDAO {

    private final EntityManagerFactory entityManagerFactory;
    public OwnerDAO(EntityManagerFactory emf) {
        entityManagerFactory = emf;
    }
    public Owner findById(Integer id) {

        Owner owner = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {

            transaction.begin();
            owner = entityManager.find(Owner.class, id);
            transaction.commit();

        } catch (Exception e) {

            if (transaction.isActive()) transaction.rollback();

        } finally {
            entityManager.close();
        }

        return owner;
    }

    public void saveOwner(Owner owner) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();;
        try {
            transaction.begin();
            entityManager.persist(owner);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            entityManager.close();
        }
    }
    public void updateOwner(Owner owner) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
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
        } finally {
            entityManager.close();
        }
    }

    public void deleteOwner(Owner owner) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
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
        } finally {
            entityManager.close();
        }
    }
}
