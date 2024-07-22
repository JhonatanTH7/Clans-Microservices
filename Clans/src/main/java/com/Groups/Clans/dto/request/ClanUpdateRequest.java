package com.Groups.Clans.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = false)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ClanUpdateRequest extends ClanRequest {

    private boolean isActive;

}
