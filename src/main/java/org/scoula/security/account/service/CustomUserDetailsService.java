package org.scoula.security.account.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.security.dto.CustomUser;
import org.scoula.security.dto.MemberDTO;                // ✅ VO → DTO
import org.scoula.security.account.mapper.UserDetailsMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDetailsMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberDTO dto = mapper.get(username);

        if (dto == null) {
            throw new UsernameNotFoundException(username + " 은(는) 없는 ID입니다.");
        }
        return new CustomUser(dto);
    }
}
