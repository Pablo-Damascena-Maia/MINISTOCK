package com.senac.ministock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PresentationController {

    /**
     * Endpoint que responde com a página HTML de apresentação do projeto.
     * Mapeia a requisição GET para /apresentacao e retorna o nome do arquivo HTML
     * que deve estar na pasta src/main/resources/static/.
     *
     * @return O nome do arquivo HTML (sem a extensão) que será servido.
     */
    @GetMapping("/apresentacao")
    public String getPresentationPage() {
        // Retorna o nome do arquivo HTML (apresentacao.html) que o Spring Boot
        // irá procurar na pasta 'static' ou 'templates'.
        return "apresentacao";
    }
}
