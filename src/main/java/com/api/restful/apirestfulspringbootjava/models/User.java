package com.api.restful.apirestfulspringbootjava.models;

import java.time.LocalDate;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import com.api.restful.apirestfulspringbootjava.models.enums.ProfileEnum;
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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity // Definir que a class user é uma entidade com o nome da tabela =users
@Table(name = User.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
        public static final String TABLE_NAME = "user";

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY) // Para gerar os id desde o primeiro de forma crescente
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

        @Column(name = "username", length = 100, nullable = false, unique = true)
        @Size(min = 15, max = 100, message = "O Nome de usuário  não pode ter mais de 100 caracteres e menos que 15")
        @NotBlank(message = "O Nome de usuário é obrigatório e não pode estar vazio")
        private String username;

        @Column(name = "cpf", length = 15, nullable = false, unique = true)
        @Size(min = 11, max = 15, message = "O CPF deve ter exatamente 11 dígitos")
        @NotBlank(message = "O CPF é obrigatório")
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "O CPF deve estar no formato ***.***.***-**")
        @Pattern(regexp = "^[0-9]*$", message = "O CPF deve conter apenas números")
        private String cpf;

        @Column(name = "email", length = 100, nullable = false, unique = true)
        @Size(max = 100, message = "O email não pode ter mais de 100 caracteres")
        @Email(message = "O email deve ser válido")
        @NotEmpty(message = "O email não pode estar vazio")
        private String email;

        @Column(name = "phone_number", length = 15)
        @Pattern(regexp = "\\+55 \\d{2} 9\\d{4}-\\d{4}", message = "O número de telefone deve estar no formato +55 DDD 9****-****")
        @Pattern(regexp = "^[^\\s]+$", message = "O número de telefone não pode conter espaços")
        @Size(max = 15, message = "O número de telefone não pode ter mais de 15 caracteres")
        private String phoneNumber;

        @Column(name = "date_of_birth")
        @Past(message = "A data de nascimento não é válida ,tem que ser no passado")
        @Pattern(regexp = "\\d{4}-\\d{2}\\-d{2}", message = "Espera datas no formato yyyy-MM-dd")
        @NotEmpty(message = "A data de nascimento não pode estar vazia")
        private LocalDate dateOfBirth;

        @JsonProperty(access = Access.WRITE_ONLY) // A senha só pode ser escrita e não retornada
        @Column(name = "password")
        @NotBlank(message = "O password é obrigatóri e não pode ser nulo ou vazio")
        @Size(min = 6, max = 25, message = "O Password deve ter mais de 6 caracteres e menor de 25")
        private String password;

        // @OneToMany(mappedBy = "user") // Um usuário pode ter várias task
        // (Cardinalidade)
        // private List<Task> tasks= new ArrayList<Task>();

        // Utilizando o lombok para não
        @OneToMany(mappedBy = "user") // Um usuário pode ter várias task (Cardinalidade)
        @JsonProperty(access = Access.WRITE_ONLY) // para não aparecer as tarefas ao buscar usuário e deixar aparecer no
        private List<Task> tasks = new ArrayList<Task>();// model task quando eu quiser ver as tarefas do usuário

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

        // Código não necessário ,utilizar o lombok já cria os métodos de acesso e
        // construtores
        // public User() {

        // }

        // public User(Long id, String name, String surname, String cpf, String email,
        // String phoneNumber,
        // LocalDate dateOfBirth, String password) {
        // this.id = id;
        // this.name = name;
        // this.surname = surname;
        // this.cpf = cpf;
        // this.email = email;
        // this.phoneNumber = phoneNumber;
        // this.dateOfBirth = dateOfBirth;
        // this.password = password;
        // }

        // public Long getId() {
        // return this.id;
        // }

        // public void setId(Long id) {
        // this.id = id;
        // }

        // public String getName() {
        // return this.name;
        // }

        // public void setName(String name) {
        // this.name = name;
        // }

        // public String getSurname() {
        // return this.surname;
        // }

        // public void setSurname(String surname) {
        // this.surname = surname;
        // }

        // public String getCpf() {
        // return this.cpf;
        // }

        // public void setCpf(String cpf) {
        // this.cpf = cpf;
        // }

        // public String getEmail() {
        // return this.email;
        // }

        // public void setEmail(String email) {
        // this.email = email;
        // }

        // public String getPhoneNumber() {
        // return this.phoneNumber;
        // }

        // public void setPhoneNumber(String phoneNumber) {
        // this.phoneNumber = phoneNumber;
        // }

        // public LocalDate getDateOfBirth() {
        // return this.dateOfBirth;
        // }

        // public void setDateOfBirth(LocalDate dateOfBirth) {
        // this.dateOfBirth = dateOfBirth;
        // }

        // public String getPassword() {
        // return this.password;
        // }

        // public void setPassword(String password) {
        // this.password = password;
        // }

        // public List<Task> getTasks() {
        // return this.tasks;
        // }

        // public void setTasks(List<Task> tasks) {
        // this.tasks = tasks;
        // }

        // Essas implementações personalizadas de equals e hashCode permitem que você
        // controle como os objetos da classe User são comparados e armazenados em
        // coleções baseadas em hash
        // Isso é útil quando você precisa lidar com objetos personalizados em seu
        // código.

        // @Override
        // public boolean equals(Object obj) {
        // if (obj == this)
        // return true;
        // if (obj == null)
        // return false;
        // if (!(obj instanceof User))
        // return false;
        // User other = (User) obj;
        // if (this.id == null)
        // if (other.id != null)
        // return false;
        // else if (!this.id.equals(other.id))
        // return false;
        // return Objects.equals(this.id, other.id) && Objects.equals(this.name,
        // other.name)
        // && Objects.equals(this.password, other.password);
        // }
        // equals(Object obj):

        // O método equals é usado para determinar se dois objetos da mesma classe são
        // iguais em termos de conteúdo. Ele deve ser sobrescrito para fornecer uma
        // implementação personalizada de igualdade para a sua classe. Aqui está o que
        // esse método faz no código que você mostrou:

        // Primeiro, ele verifica se o objeto sendo comparado (obj) é o próprio objeto
        // atual (this). Se for o caso, retorna true, porque um objeto sempre é igual a
        // ele mesmo.

        // Em seguida, verifica se o objeto obj é nulo. Se for nulo, retorna false, pois
        // um objeto não pode ser igual a nulo.

        // Depois, verifica se o objeto obj é uma instância da classe User. Se não for,
        // retorna false, porque só faz sentido comparar objetos da mesma classe.

        // Em seguida, converte o objeto obj em um objeto do tipo User e compara os
        // campos id, name, e password para determinar se os objetos são iguais. Se
        // todos os campos forem iguais, retorna true; caso contrário, retorna false.

        // É importante notar que a implementação usa a verificação nula para evitar
        // exceções de NullPointerException ao acessar campos nulos.

        // @Override
        // public int hashCode() {
        // final int prime = 31;
        // int result = 1;
        // result = prime * result + (this.id == null ? 0 : this.hashCode());
        // return result;
        // }
        // ashCode():

        // O método hashCode é usado para calcular um valor numérico que representa o
        // objeto e é usado principalmente em coleções como tabelas de hash. Ele deve
        // ser sobrescrito para que objetos iguais (de acordo com o método equals)
        // produzam o mesmo valor de hashCode. Aqui está o que esse método faz no código
        // que você mostrou:

        // Define uma constante prime (geralmente um número primo) que é usada para
        // calcular o valor de hash.

        // Inicializa um valor de result com 1.

        // Calcula o valor de hash com base nos campos da classe. No seu caso, apenas o
        // campo id é usado para calcular o valor de hash.

        // O valor de result é atualizado multiplicando-o por prime e adicionando o
        // valor de hash do campo id.

        // Por fim, o valor de result é retornado como o valor de hash calculado.
}
