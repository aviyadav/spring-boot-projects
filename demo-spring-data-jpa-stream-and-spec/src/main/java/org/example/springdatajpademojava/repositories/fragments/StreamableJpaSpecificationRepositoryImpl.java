package org.example.springdatajpademojava.repositories.fragments;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import java.util.stream.Stream;

import static org.hibernate.jpa.HibernateHints.HINT_FETCH_SIZE;

public class StreamableJpaSpecificationRepositoryImpl<T> implements StreamableJpaSpecificationRepository<T> {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Stream<T> stream(Specification<T> specification, Class<T> clazz) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);

        Root<T> root = criteriaQuery.from(clazz);
        criteriaQuery.select(root);

        if(specification != null) {
            criteriaQuery.where(specification.toPredicate(root, criteriaQuery, criteriaBuilder));
        }
        return entityManager.createQuery(criteriaQuery)
                .setHint(HINT_FETCH_SIZE, "1")
                .getResultStream();
    }
}
