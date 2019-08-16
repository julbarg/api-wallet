package com.leovegas.apiwallet.service;

import com.leovegas.apiwallet.entity.Player;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PlayerDAOService {

    @PersistenceContext
    private EntityManager entityManager;

    public long insert(Player player) {
        entityManager.persist(player);

        return player.getId();
    }
}
