package org.scoula.security.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class CustomUser extends User {
    private MemberDTO member;

    // 실질적인사용자데이터
    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CustomUser(MemberDTO dto) {
        super(dto.getUserId(), dto.getPassword(), dto.getAuthList());
        this.member = dto;
    }
}
