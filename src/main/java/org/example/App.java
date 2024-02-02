package org.example;

import org.example.data.Building;
import org.example.data.Flat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

import static org.example.data.BuildingCRUDRepository.*;
import static org.example.data.FlatCRUDRepository.processWithCriteriaAPI;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("osbb");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        System.out.println("-----------------");
        List<Building> buildingsN = processWithNativeSQL(entityManager);
        for (Building house : buildingsN) {
            System.out.println(house.getAddress());

            for (Flat flat : house.getFlats())
                System.out.println("\t:" + flat.getNumber());
        }

        System.out.println("-----------------");
        List<Building> buildings = processWithJPQL(entityManager);
        for (Building building : buildings) {
            System.out.println(building.getAddress());

            for (Flat flat : building.getFlats())
                System.out.println("\t:" + flat.getNumber());
        }

        System.out.println("-----------------");
        List<Building> buildingsD = processWithNamedByAddress(entityManager, "Sajevicha");
        for (Building building : buildingsD) {
            System.out.println(building.getAddress());
        }

        System.out.println("-----------------");
        List<Flat> flats = processWithCriteriaAPI(entityManager, 1, 0f);
        for (Flat flat : flats)
            System.out.println("\t:" + flat.getNumber());

        try {
            transaction.begin();

            // Операції з базою даних тут

            entityManager.persist(
                    newBuilding());

            deleteBuilding(entityManager, 5);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
