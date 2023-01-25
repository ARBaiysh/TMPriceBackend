package kg.baiysh.TMPriceBackend.entity;


import kg.baiysh.TMPriceBackend.entity.enums.ERole;
import kg.baiysh.TMPriceBackend.entity.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;


@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    private String id;
    private String username;
    private String password;
    private String fio;

    @Enumerated(value = EnumType.STRING)
    private ERole role;

    @Enumerated(value = EnumType.STRING)
    private EStatus status;

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return status.equals(EStatus.ACTIVE);
    }

    @Override
    public boolean isAccountNonLocked() {
        return status.equals(EStatus.ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status.equals(EStatus.ACTIVE);
    }

    @Override
    public boolean isEnabled() {
        return status.equals(EStatus.ACTIVE);
    }
}
