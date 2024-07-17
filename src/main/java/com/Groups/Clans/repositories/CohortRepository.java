package com.Groups.Clans.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Groups.Clans.entities.Cohort;

@Repository
public interface CohortRepository extends JpaRepository<Cohort, Long> {

}
