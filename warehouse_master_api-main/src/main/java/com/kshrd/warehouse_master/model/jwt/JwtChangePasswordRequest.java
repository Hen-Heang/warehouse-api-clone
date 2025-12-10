package com.kshrd.warehouse_master.model.jwt;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtChangePasswordRequest {
    private String email;
    private String oldPassword;
    private String newPassword;
}
