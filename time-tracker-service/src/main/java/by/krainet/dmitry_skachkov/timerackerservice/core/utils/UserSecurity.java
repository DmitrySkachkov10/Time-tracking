package by.krainet.dmitry_skachkov.timerackerservice.core.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSecurity implements GrantedAuthority {

    private String uuid;

    private String role;

    @Override
    @JsonIgnore
    public String getAuthority() {
        return "ROLE_" + role;
    }

    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_" + role));
    }
}
