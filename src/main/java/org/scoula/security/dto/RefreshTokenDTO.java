package org.scoula.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenDTO {
    private String user_id;           // 사용자 ID
    private String jwt_refresh_token; // 리프레시 토큰
}