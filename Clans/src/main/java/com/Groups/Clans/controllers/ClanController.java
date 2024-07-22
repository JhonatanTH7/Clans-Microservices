package com.Groups.Clans.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Groups.Clans.dto.request.ClanGetRequest;
import com.Groups.Clans.dto.request.ClanRequest;
import com.Groups.Clans.dto.request.ClanUpdateRequest;
import com.Groups.Clans.entities.Clan;
import com.Groups.Clans.services.abstract_services.IClanService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping(path = "/clans")
@AllArgsConstructor
public class ClanController {

    @Autowired
    private final IClanService iClanService;

    @GetMapping
    public ResponseEntity<Page<Clan>> getMethodName(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "3") Integer size,
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String description,
            @RequestParam(required = false) boolean isActive,
            @RequestParam(required = false) Long cohortId) {
        var req = ClanGetRequest
                .builder()
                .page(page)
                .size(size)
                .name(name)
                .description(description)
                .isActive(isActive)
                .cohortId(cohortId)
                .build();
        return ResponseEntity.ok(this.iClanService.findAll(req));
    }

    @PostMapping
    public ResponseEntity<Clan> create(@RequestBody ClanRequest clanRequest) {
        return ResponseEntity.ok(this.iClanService.create(clanRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clan> update(@PathVariable Long id, @RequestBody ClanUpdateRequest clanUpdateRequest) {
        return ResponseEntity.ok(this.iClanService.update(id, clanUpdateRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Clan> disable(@PathVariable Long id) {
        return ResponseEntity.ok(this.iClanService.disable(id));
    }

}