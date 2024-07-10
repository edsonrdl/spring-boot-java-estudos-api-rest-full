package com.api.todolist.models;

import java.time.LocalDate;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import com.api.todolist.models.enums.ProfileEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity 
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true)
        private Long id;

        @Column(name = "name", length = 15, nullable = false, unique = false)
        @Size(min = 3, max = 15, message = "O nome não pode ter mais de 15 caracteres e menos que 3")
        @NotBlank(message = "O Nome é obrigatório e não pode estar vazio")
        private String name;

        @Column(name = "surname", length = 70, nullable = false, unique = true)
        @Size(max = 70, message = "O sobrenome não pode ter mais de 70 caracteres")
        @NotBlank(message = "O sobrenome é obritarório e não pode estar vazio")
        private String surname;

        @Column(name = "username", length = 100, nullable = false)
        @Size(min = 15, max = 100, message = "O Nome de usuário  não pode ter mais de 100 caracteres e menos que 15")
        @NotBlank(message = "O Nome de usuário é obrigatório e não pode estar vazio")
        private String username;

        @Column(name = "date_of_birth")
        @Past(message = "A data de nascimento não é válida ,tem que ser no passado")
        @Pattern(regexp = "\\d{4}-\\d{2}\\-d{2}", message = "Espera datas no formato yyyy-MM-dd")
        @NotEmpty(message = "A data de nascimento não pode estar vazia")
        private LocalDate dateOfBirth;

        @JsonProperty(access = Access.WRITE_ONLY)
        @Column(name = "password")
        @NotBlank(message = "O password é obrigatório e não pode ser nulo ou vazio")
        @Size(min = 6, max = 25, message = "O Password deve ter mais de 6 caracteres e menor de 25")
        private String password;

        @OneToMany(mappedBy = "user") 
        @JsonProperty(access = Access.WRITE_ONLY)
        private List<Task> tasks = new ArrayList<Task>();

        @Column(name = "profile", nullable = false)
        @ElementCollection(fetch = FetchType.EAGER)
        @CollectionTable(name = "user_profile")
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private Set<Integer> profiles = new HashSet<>();

        public Set<ProfileEnum> getProfiles() {
                return this.profiles.stream().map(x -> ProfileEnum.toEnum(x)).collect(Collectors.toSet());
        }

        public void addProfile(ProfileEnum profileEnum) {
                this.profiles.add(profileEnum.getCode());
        }

}
