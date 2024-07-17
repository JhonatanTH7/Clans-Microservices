package com.Groups.Clans.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.Groups.Clans.dto.request.ClanGetRequest;
import com.Groups.Clans.dto.request.ClanRequest;
import com.Groups.Clans.dto.request.ClanUpdateRequest;
import com.Groups.Clans.entities.Clan;
import com.Groups.Clans.repositories.ClanRepository;
import com.Groups.Clans.services.abstract_services.IClanService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClanService implements IClanService {

    @Autowired
    private final ClanRepository clanRepository;

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
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public Clan update(Long id, ClanUpdateRequest clanUpdateRequest) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Clan disable(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'disable'");
    }

}
