package com.Groups.Clans.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.Groups.Clans.entities.Cohort;
import com.Groups.Clans.repositories.CohortRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class DatabaseSeed implements CommandLineRunner {

    @Autowired
    private final CohortRepository cohortRepository;

    @Override
    public void run(String... args) throws Exception {

        if (this.cohortRepository.count() > 0) {
            log.info("Seeding Database Cohort Wasn't Executed Because there is a record already");
            return;
        }

        Cohort cohort1 = Cohort
                .builder()
                .name("Cohort1")
                .build();
        Cohort cohort2 = Cohort
                .builder()
                .name("Cohort2")
                .build();
        Cohort cohort3 = Cohort
                .builder()
                .name("Cohort3")
                .build();

        this.cohortRepository.save(cohort1);
        this.cohortRepository.save(cohort2);
        this.cohortRepository.save(cohort3);

        log.info("Seeding Database Cohort Executed");

        // log.info("Seeding Database Cohort Info");
        // log.error("Seeding Database Cohort Error");
        // log.warn("Seeding Database Cohort Warn");

    }

}
