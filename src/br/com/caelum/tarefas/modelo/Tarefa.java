package br.com.caelum.tarefas.modelo;

import java.util.Calendar;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

public class Tarefa {

  private Long id;

  // essa chave de mensagem fica no arquivo padrao do bean validator ValidationMessages.properties
  // dentro do src
  @Size(min = 5, message = "{tarefa.descricao.pequena}")
  private String descricao;
  private boolean finalizado;

  @DateTimeFormat(pattern = "dd/MM/yyyy")
  private Calendar dataFinalizacao;

  public Tarefa() {}

  public Tarefa(final Long id, final String descricao, final boolean finalizado,
      final Calendar dataFinalizacao) {
    this.id = id;
    this.descricao = descricao;
    this.finalizado = finalizado;
    this.dataFinalizacao = dataFinalizacao;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getDescricao() {
    return this.descricao;
  }

  public void setDescricao(final String descricao) {
    this.descricao = descricao;
  }

  public boolean isFinalizado() {
    return this.finalizado;
  }

  public void setFinalizado(final boolean finalizado) {
    this.finalizado = finalizado;
  }

  public Calendar getDataFinalizacao() {
    return this.dataFinalizacao;
  }

  public void setDataFinalizacao(final Calendar dataFinalizacao) {
    this.dataFinalizacao = dataFinalizacao;
  }

  @Override
  public String toString() {
    return "Tarefa [id=" + this.id + ", descricao=" + this.descricao + ", finalizado="
        + this.finalizado + ", dataFinalizacao=" + this.dataFinalizacao + "]";
  }
}
