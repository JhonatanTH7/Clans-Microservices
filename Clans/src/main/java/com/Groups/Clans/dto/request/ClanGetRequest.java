package com.Groups.Clans.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClanGetRequest {

    private Integer page;
    private Integer size;
    private String name;
    private String description;
    private Long cohortId;
    private boolean isActive;

}
