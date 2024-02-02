package org.example.data;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class FlatCRUDRepository {
    public static List<Flat> processWithCriteriaAPI(EntityManager entityManager, int floor, float reqSquare) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Flat> criteriaQuery = criteriaBuilder.createQuery(Flat.class);

        Root<Flat> root = criteriaQuery.from(Flat.class);
        criteriaQuery.select(root);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(
                criteriaBuilder.equal(root.get("stage").as(Integer.class), floor));

        if (reqSquare > 0.f)
            predicates.add(
                    criteriaBuilder.ge(root.get("square").as(Float.class), reqSquare));

        criteriaQuery.where(
                predicates.toArray(new Predicate[0]));

        TypedQuery<Flat> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
