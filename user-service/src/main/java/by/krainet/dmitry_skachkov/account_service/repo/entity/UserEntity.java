package by.krainet.dmitry_skachkov.account_service.repo.entity;

import by.krainet.dmitry_skachkov.account_service.repo.entity.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "users", name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {

    @Id
    private UUID uuid;

    private String password;

    private String mail;

    @Column(name = "full_name")
    private String fullName;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Version
    private long version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return version == that.version && Objects.equals(uuid, that.uuid) && Objects.equals(password, that.password) && Objects.equals(mail, that.mail) && Objects.equals(fullName, that.fullName) && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, password, mail, fullName, role, version);
    }
}
