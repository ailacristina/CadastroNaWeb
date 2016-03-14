package Modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@NamedQueries({
    @NamedQuery(name = Terapeuta.EXISTE_LOGIN, query = "select count(u) from Terapeuta u where u.login = :login"),
    @NamedQuery(name = Terapeuta.POR_LOGIN_E_SENHA, query = "select u from Terapeuta u where u.login = :login and u.senha = :senha")
})
@Entity
public class Terapeuta implements Serializable{
    
    public static final String EXISTE_LOGIN = "Usuario.existeUsuarioComEmail";
    public static final String POR_LOGIN_E_SENHA = "Usuario.porEmailESenha";
    
    @Id @GeneratedValue
    private Long id;
    private String nome;
    private String login;
    private String email;
    private String sexo;
    private String cpf;
    @Temporal(TemporalType.DATE)
    private Date nascimento;
    private String senha; 
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    } 
      
}
