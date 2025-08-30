package org.school.backend.adapters.schema.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER", schema="public")
public class UserJpa {
    @Id
    @Column(name = "ID",columnDefinition = "uuid", updatable = false,nullable = false)
    @GeneratedValue
    @UuidGenerator
    UUID id;

    @Column(name = "NAME")
    String name;

    @Column(name = "ROLE")
    String role;

    @Column(name =  "PASSWORD")
    String password;

    public UserJpa(
            String name,
            String role,
            String password
    ){
        this.name = name;
        this.role = role;
        this.password = password;
    }
}
