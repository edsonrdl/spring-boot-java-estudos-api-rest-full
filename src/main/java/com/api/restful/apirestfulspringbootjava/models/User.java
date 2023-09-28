package com.api.restful.apirestfulspringbootjava.models;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import java.util.Objects;



@Entity //Definir que a class user é uma entidade com o nome da tabela =users
@Table(name = "users")
public class User {
    public static final String TABLE_NANE="users";
    public interface CreateUser{}
    public interface UpdateUser{}

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)//Para gerar os id desde o primeiro de forma crescente
    @Column(name="id",unique=true)
    private Long id;
    
    @Column(name="name",length = 15,nullable =false,unique = true)
    private String name;
    @Size(groups=CreateUser.class,min=3,max = 15, message = "O nome não pode ter mais de 15 caracteres e menos que 3")
    @NotEmpty(groups=CreateUser.class,message = "O nome não pode estar vazio")


    @Column(name = "surname", length = 70, nullable = false, unique = true)
    @Size(groups=CreateUser.class,max = 70, message = "O sobrenome não pode ter mais de 70 caracteres")
    @NotEmpty(groups=CreateUser.class,message = "O sobrenome não pode estar vazio")
    private String surname;

    @Id
    @Column(name = "cpf", length = 11, nullable = false, unique = true)
    @Size(groups=CreateUser.class,min = 11, max = 11, message = "O CPF deve ter exatamente 11 dígitos")
    @Pattern(groups=CreateUser.class,regexp = "^[0-9]*$", message = "O CPF deve conter apenas números")
    private String cpf;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    @Size(groups=CreateUser.class,max = 100, message = "O email não pode ter mais de 100 caracteres")
    @Email(groups=CreateUser.class,message = "O email deve ser válido")
    @NotEmpty(groups=CreateUser.class,message = "O email não pode estar vazio")
    private String email;

    @Column(name = "phone_number", length = 15)
    @Size(groups={CreateUser.class,UpdateUser.class},max = 15, message = "O número de telefone não pode ter mais de 15 caracteres")
    private String phoneNumber;

    @Column(name = "date_of_birth")
    @Past(groups={CreateUser.class,UpdateUser.class},message = "A data de nascimento não é válida ,tem que ser no passado")
    @NotEmpty(groups={CreateUser.class,UpdateUser.class},message = "A data de nascimento não pode estar vazia")
    private LocalDate dateOfBirth;

    @JsonProperty(access = Access.WRITE_ONLY) //A senha só pode ser escrita e não retornada 
    @Column(name = "password")
    @Size(groups={CreateUser.class,UpdateUser.class},min = 6, max = 25, message = "O Password deve ter mais de 6 caracteres e menor de 25")
    @NotEmpty(groups={CreateUser.class,UpdateUser.class},message = "A password de nascimento não pode estar vazia")
    private String password;

    //private List<Task> task= new ArrayList<Task>();
    public User(){
        
    }
    public User(Long id,String name,String surname,String cpf,String email,String phoneNumber,LocalDate dateOfBirth,String password){
        this.id=id;
        this.name=name;
        this.surname=surname;
        this.cpf=cpf;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.dateOfBirth=dateOfBirth;
        this.password=password;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
     

    //Essas implementações personalizadas de equals e hashCode permitem que você controle como os objetos da classe User são comparados e armazenados em coleções baseadas em hash
    //Isso é útil quando você precisa lidar com objetos personalizados em seu código.

     @Override 
     public boolean equals(Object obj){
        if(obj==this)
            return true;
        if(obj ==null)
        return false;
        if(!(obj instanceof User))
            return false;
            User other=(User)obj;
            if(this.id==null)
                if(other.id!=null)
                return false;
                else if(!this.id.equals(other.id))
                return false;
                return Objects.equals(this.id, other.id)&& Objects.equals(this.name,other.name) && Objects.equals(this.password, other.password);
     }
// equals(Object obj):

// O método equals é usado para determinar se dois objetos da mesma classe são iguais em termos de conteúdo. Ele deve ser sobrescrito para fornecer uma implementação personalizada de igualdade para a sua classe. Aqui está o que esse método faz no código que você mostrou:

// Primeiro, ele verifica se o objeto sendo comparado (obj) é o próprio objeto atual (this). Se for o caso, retorna true, porque um objeto sempre é igual a ele mesmo.

// Em seguida, verifica se o objeto obj é nulo. Se for nulo, retorna false, pois um objeto não pode ser igual a nulo.

// Depois, verifica se o objeto obj é uma instância da classe User. Se não for, retorna false, porque só faz sentido comparar objetos da mesma classe.

// Em seguida, converte o objeto obj em um objeto do tipo User e compara os campos id, name, e password para determinar se os objetos são iguais. Se todos os campos forem iguais, retorna true; caso contrário, retorna false.

// É importante notar que a implementação usa a verificação nula para evitar exceções de NullPointerException ao acessar campos nulos.
   
    @Override
     public int hashCode(){
         final int prime=31;
         int result=1;
         result=prime * result+(this.id==null?0:this.hashCode());
         return result;
     }
// ashCode():

// O método hashCode é usado para calcular um valor numérico que representa o objeto e é usado principalmente em coleções como tabelas de hash. Ele deve ser sobrescrito para que objetos iguais (de acordo com o método equals) produzam o mesmo valor de hashCode. Aqui está o que esse método faz no código que você mostrou:

// Define uma constante prime (geralmente um número primo) que é usada para calcular o valor de hash.

// Inicializa um valor de result com 1.

// Calcula o valor de hash com base nos campos da classe. No seu caso, apenas o campo id é usado para calcular o valor de hash.

// O valor de result é atualizado multiplicando-o por prime e adicionando o valor de hash do campo id.

// Por fim, o valor de result é retornado como o valor de hash calculado.
}
