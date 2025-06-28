package org.school.backend.adapters.schema.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "STUDENT", schema="public")
public class StudentLogJpa {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "NICK_NAME")
    private String nickName;

    @Column(name = "NIK")
    private String nik;

    @Column(name ="GENDER")
    private String gender;

    @Column(name = "DATE_BIRTH")
    private LocalDateTime dateBirth;

    @Column(name = "BIRTH_ORDER")
    private String birthOrder;

    @Column(name = "TRIBE")
    private String tribe;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "HEIGHT")
    private String height;

    @Column(name = "WEIGHT")
    private String weight;

    public StudentLogJpa(
            String fullName,
            String nickName,
            String nik,
            String gender,
            LocalDateTime dateBirth,
            String birthOrder,
            String tribe,
            String address,
            String height,
            String weight
    ){
        this.fullName = fullName;
        this.nickName = nickName;
        this.nik = nik;
        this.gender = gender;
        this.dateBirth = dateBirth;
        this.birthOrder = birthOrder;
        this.tribe = tribe;
        this.address= address;
        this.height = height;
        this.weight = weight;
    }
}
