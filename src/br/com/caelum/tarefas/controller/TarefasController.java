package br.com.caelum.tarefas.controller;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    return "formulario";
  }

  // usando o bean validation para valido o bean tarefa
  // usando o bindingresult para segurar a exception e capturar a mensagem
  @RequestMapping("adicionaTarefa")
  public String adiciona(@Valid final Tarefa tarefa, final BindingResult binding) {

    // se tiver erros volta para a tela do forumulario
    if (binding.hasErrors()) {
      return "formulario";
    }

    final JdbcTarefaDao dao = new JdbcTarefaDao();
    dao.adicionar(tarefa);

    // por padrao o spring nao procura nas subpastas definidas dentro de views no spring-contex.xml
    // por isso temos que dizer em qual subpasta se encontra a jsp
    return "adicionada";
  }

  // o framework spring mvc injeta esse model para gente setar objetos para tela
  @RequestMapping("listaTarefas")
  public String listagem(final Model model) {
    final JdbcTarefaDao dao = new JdbcTarefaDao();
    model.addAttribute("tarefas", dao.lista());
    return "lista";
  }

  // o framework spring mvc injeta esse model e o id para gente setar objetos para tela
  @RequestMapping("mostraTarefa")
  public String mostraTarefa(final Long id, final Model model) {
    final JdbcTarefaDao dao = new JdbcTarefaDao();
    model.addAttribute("tarefa", dao.recuperar(id));
    return "mostra";
  }

  // redirecionamento para outras acoes do lado do server forward: ou do lado do navegador redirect:
  // foward mantem a url e executa a acao
  // redirect atualiza a url e executa a acao
  @RequestMapping("removeTarefa")
  public String remove(final Tarefa tarefa) {
    final JdbcTarefaDao dao = new JdbcTarefaDao();
    dao.remover(tarefa);
    // return "forward:listaTarefas";
    return "redirect:listaTarefas";
  }

  @RequestMapping("alteraTarefa")
  public String altera(final Tarefa tarefa) {
    final JdbcTarefaDao dao = new JdbcTarefaDao();
    dao.alterar(tarefa);
    return "redirect:listaTarefas";
  }

  @ResponseBody
  @RequestMapping("finalizaTarefa")
  public void finaliza(final Long id) {
    new JdbcTarefaDao().finaliza(id);
  }
}
