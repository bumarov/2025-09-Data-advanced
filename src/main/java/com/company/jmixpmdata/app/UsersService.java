package com.company.jmixpmdata.app;

import com.company.jmixpmdata.entity.Project;
import com.company.jmixpmdata.entity.User;
import io.jmix.data.PersistenceHints;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsersService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<User> getUsersNotInProject(Project project, int firstResult, int maxResults) {
        return entityManager.createQuery(
                "select u from User u " +
                        "where u.id not in " +
                        "(select u1.id from User u1 " +
                        "inner join u1.projects pul " +
                        "where pul.id = ?1)", User.class
        )
                .setParameter(1, project.getId())
                .setFirstResult(firstResult)
                .setMaxResults(maxResults)
//                .setHint(PersistenceHints.SOFT_DELETION, false)
                .getResultList();
    }
}