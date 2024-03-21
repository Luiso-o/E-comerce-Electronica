package com.luis.pcstore.dto;

import lombok.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileDto {
    //testeado
    private UUID id_user;
    private String name;
    private String surname;
}
