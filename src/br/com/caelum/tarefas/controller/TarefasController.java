package br.com.caelum.tarefas.controller;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.caelum.tarefas.dao.JdbcTarefaDao;
import br.com.caelum.tarefas.modelo.Tarefa;

@Controller
public class TarefasController {
  /*
   * Organização dos arquivos e subdiretorios
   *
   * -WEB-INF -view -tarefa -formulario.jsp -adicionada.jsp
   */

  // criando uma nova acao só para carregar o form que adiciona nova tarefa
  // pois o client nao tem acesso ao subdiretorios de WEB-INF
  @RequestMapping("novaTarefa")
  public String form() {
    return "tarefa/formulario";
  }

  // usando o bean validation para valido o bean tarefa
  // usando o bindingresult para segurar a exception e capturar a mensagem
  @RequestMapping("adicionaTarefa")
  public String adiciona(@Valid final Tarefa tarefa, final BindingResult binding) {

    // se tiver erros volta para a tela do forumulario
    if (binding.hasErrors()) {
      return "tarefa/formulario";
    }

    final JdbcTarefaDao dao = new JdbcTarefaDao();
    dao.adicionar(tarefa);

    // por padrao o spring nao procura nas subpastas definidas dentro de views no spring-contex.xml
    // por isso temos que dizer em qual subpasta se encontra a jsp
    return "tarefa/adicionada";
  }
}
