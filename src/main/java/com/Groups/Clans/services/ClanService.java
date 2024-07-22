package com.Groups.Clans.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.Groups.Clans.dto.request.ClanGetRequest;
import com.Groups.Clans.dto.request.ClanRequest;
import com.Groups.Clans.dto.request.ClanUpdateRequest;
import com.Groups.Clans.entities.Clan;
import com.Groups.Clans.entities.Cohort;
import com.Groups.Clans.repositories.ClanRepository;
import com.Groups.Clans.repositories.CohortRepository;
import com.Groups.Clans.services.abstract_services.IClanService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClanService implements IClanService {

    @Autowired
    private final ClanRepository clanRepository;

    @Autowired
    private final CohortRepository cohortRepository;

    @Override
    public Page<Clan> findAll(ClanGetRequest clanGetRequest) {
        if (clanGetRequest.getPage() < 0)
            clanGetRequest.setPage(0);
        PageRequest pagination = PageRequest.of(clanGetRequest.getPage(), clanGetRequest.getSize());
        return this.clanRepository.getAll(clanGetRequest.getName(), clanGetRequest.getDescription(),
                clanGetRequest.isActive(), clanGetRequest.getCohortId(), pagination);
    }

    @Override
    public Clan create(ClanRequest clanRequest) {
        Cohort cohort = this.cohortRepository.findById(clanRequest.getCohortId())
                .orElseThrow(() -> new RuntimeException("Cohort not found"));
        Clan clan = Clan.builder()
                .name(clanRequest.getName())
                .description(clanRequest.getDescription())
                .cohort(cohort)
                .build();
        return this.clanRepository.save(clan);
    }

    @Override
    public Clan update(Long id, ClanUpdateRequest clanUpdateRequest) {
        Clan clan = this.clanRepository.findById(id).orElseThrow(() -> new RuntimeException("Clan not found"));

        if (clanUpdateRequest.getCohortId() != clan.getCohort().getId()) {
            Cohort cohort = this.cohortRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Cohort not found"));
            clan.setCohort(cohort);
        }

        clan.setName(clanUpdateRequest.getName());
        clan.setDescription(clanUpdateRequest.getDescription());
        clan.setUpdatedAt(LocalDateTime.now());
        clan.setActive(clanUpdateRequest.isActive());
        return this.clanRepository.save(clan);
    }

    @Override
    public Clan disable(Long id) {
        Clan clan = this.clanRepository.findById(id).orElseThrow(() -> new RuntimeException("Clan not found"));
        clan.setActive(false);
        clan.setUpdatedAt(LocalDateTime.now());
        return this.clanRepository.save(clan);
    }

}
