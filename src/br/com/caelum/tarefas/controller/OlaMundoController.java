package br.com.caelum.tarefas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OlaMundoController {

  // Sera o nome do recurso chamado na url
  @RequestMapping("/olaMundoSpring")
  public String execute() {
    System.out.println("Executando a lógica com Spring MVC");
    return "ok"; // essa sera a pagina ok.jsp, como mapeanos no spring-context.xml não precisamos
                 // falar o tipos do arquivo
  }

}
