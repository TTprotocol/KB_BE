package org.scoula.security.account.mapper;

import org.scoula.security.dto.LoginDTO;
import org.scoula.security.dto.MemberDTO;
import org.scoula.security.dto.RefreshTokenDTO;

public interface UserDetailsMapper {
    public MemberDTO get(String username);
    void updateRefreshToken(RefreshTokenDTO refreshTokenDTO);
    String getRefreshToken(String user_id);
    void clearRefreshToken(String user_id);
}