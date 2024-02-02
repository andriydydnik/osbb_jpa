package org.example.data;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

public class BuildingCRUDRepository {
    public static Building newBuilding(){
        Building newBuilding = new Building();
        newBuilding.setAddress("Kudry, 20");

        Flat newFlat1 = new Flat();
        newFlat1.setNumber("101");
        newFlat1.setBuilding(newBuilding);

        Flat newFlat2 = new Flat();
        newFlat2.setNumber("102");
        newFlat2.setBuilding(newBuilding);

        newBuilding.getFlats()
                .add(newFlat1);
        newBuilding.getFlats()
                .add(newFlat2);
        return newBuilding;
    }

    public static void deleteBuilding(EntityManager entityManager, int id){
        Building building = entityManager.find(Building.class, id);
        entityManager.remove(building);
    }

    public static List<Building> processWithNativeSQL(EntityManager entityManager) {
        Query query = entityManager.createNativeQuery(
                "SELECT * FROM buildings b WHERE id = 1",
                Building.class);
        return query.getResultList();
    }

    public static List<Building> processWithJPQL(EntityManager entityManager) {
        TypedQuery<Building> query = entityManager.createQuery(
                "SELECT b FROM Building b WHERE id = :id",
                Building.class);
        query.setParameter("id", 1L);

        return query.getResultList();
    }

    public static List<Building> processWithNamedByAddress(EntityManager entityManager, String address) {
        TypedQuery<Building> query = entityManager.createNamedQuery(
                "Building.findByAddress",
                Building.class);
        query.setParameter("address", address);

        return query.getResultList();
    }
}
