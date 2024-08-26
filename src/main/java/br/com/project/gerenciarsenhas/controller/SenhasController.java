package br.com.project.gerenciarsenhas.controller;

import br.com.project.gerenciarsenhas.domain.senha.DadosAlteracaoSenha;
import br.com.project.gerenciarsenhas.domain.senha.DadosCadastroSenha;
import br.com.project.gerenciarsenhas.domain.senha.Senha;
import br.com.project.gerenciarsenhas.domain.senha.SenhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.ArrayList;
import java.util.List;

@Controller // diz que isso é um controlador, vai receber requisições http
@RequestMapping("/senhas") // para onde vai ser mapeado
public class SenhasController {

    @Autowired
    private SenhaRepository repository;

    @GetMapping("/formulario") // se ouver uma requisição get, esse metodo vai ser chamadoo
    public String carregaPaginaForm(Long id, Model model) {

        if (id != null) {
            var senha = repository.getReferenceById(id);
            model.addAttribute("senha", senha);
        }

        return "senhas/formulario";
    }

    @GetMapping
    public String carregaPaginaList(Model model) { // Model manda informações para a pagina
        model.addAttribute("lista", repository.findAll());// nome do atributo usado no html e a lista criada no back
        return "senhas/listagem";
    }

    @PostMapping// se ouver uma requisição post, esse metodo vai ser chamadoo
    @Transactional
    public String cadastraSenha(DadosCadastroSenha dados) {
        var senha = new Senha(dados);

        repository.save(senha);

        return "redirect:/senhas";
    }

    @PutMapping
    @Transactional
    public String alteraSenha(DadosAlteracaoSenha dados) {

        var senha = repository.getReferenceById(dados.id());
        senha.atualizaDados(dados);

        return "redirect:/senhas";
    }

    @DeleteMapping
    @Transactional
    public String removeSenha(Long id){
        repository.deleteById(id);

        return "redirect:/senhas";
    }

}
