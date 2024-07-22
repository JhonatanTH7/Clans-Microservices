package com.Groups.Clans.services.abstract_services;

import org.springframework.data.domain.Page;

import com.Groups.Clans.dto.request.ClanGetRequest;
import com.Groups.Clans.dto.request.ClanRequest;
import com.Groups.Clans.dto.request.ClanUpdateRequest;
import com.Groups.Clans.entities.Clan;

public interface IClanService {
    Page<Clan> findAll(ClanGetRequest clanGetRequest);

    Clan create(ClanRequest clanRequest);

    Clan update(Long id, ClanUpdateRequest clanUpdateRequest);

    Clan disable(Long id);
}
