package com.example.BancTransfer.configurationSpringSecurity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import java.time.LocalDate;

@Data //gettr si setter
@NoArgsConstructor //constuctor fara parametri
@AllArgsConstructor //constuctori cu toti parametri
@EqualsAndHashCode // eqauls si hascode
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String country;
    @Column
    private String occupation;
    @Column
    private String role;
    @Column
    private String gender;
    @Column
    private LocalDate dateOfBirth;


}