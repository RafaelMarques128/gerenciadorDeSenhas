package br.com.project.gerenciarsenhas.domain.senha;

import jakarta.persistence.*;

@Entity // serve para dizer ao jpa que esssa classe precisar mapear no banco de daos
@Table(name = "senhas") // endicar a tabela da classe filme
public class Senha {

    @Id// identificador de chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // fazer com que o proprio banco gerar os valores de identidade
    private Long id;

    private String link;

    private String login;

    private String senha;

    public Senha(DadosCadastroSenha dados){ // passando os dados do record
        this.link = dados.link();
        this.login = dados.login();
        this.senha = dados.senha();
    }

    public Senha() {
        // Construtor padrão necessário para o JPA compilar e instanciar a entidade senha
    }

    @Override
    public String toString() {
        return "Senha{" +
                "link='" + link + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void atualizaDados(DadosAlteracaoSenha dados) {
        this.link = dados.link();
        this.login = dados.login();
        this.senha = dados.senha();
    }
}
