package com.ownproject.Sorganizer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface MechanicRepository extends JpaRepository<Mechanic,Integer> {

    public Mechanic findByMechanic(String mechanic);
}
