package edu.security.gp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GpController {

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String inicio() {
        return "inicio";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/projetos")
    public String projetos() {
        return "projetos";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/relatorio-cursos")
    public String relatorioCursos() {
        return "relatorio-cursos";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/relatorio-equipe")
    public String relatorioEquipe() {
        return "relatorio-equipe";
    }
}