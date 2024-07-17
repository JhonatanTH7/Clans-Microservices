package com.Groups.Clans.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Groups.Clans.dto.request.ClanGetRequest;
import com.Groups.Clans.entities.Clan;
import com.Groups.Clans.services.abstract_services.IClanService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path = "/clan")
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

}