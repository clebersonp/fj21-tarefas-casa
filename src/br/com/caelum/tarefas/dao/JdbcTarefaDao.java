package br.com.caelum.tarefas.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import br.com.caelum.tarefas.exception.TarefaDaoException;
import br.com.caelum.tarefas.infraestrutura.ConnectionFactory;
import br.com.caelum.tarefas.modelo.Tarefa;

public class JdbcTarefaDao {

  final Connection connection;

  public JdbcTarefaDao() {
    this.connection = new ConnectionFactory().getConnection();
  }

  public void adicionar(final Tarefa tarefa) {

    final String sql =
        "insert into tarefas (descricao, finalizado, data_finalizacao) values (?, ?, ?)";

    try (final PreparedStatement stmt = this.connection.prepareStatement(sql)) {

      // setando os valores no preparedStatement
      stmt.setString(1, tarefa.getDescricao());
      stmt.setBoolean(2, tarefa.isFinalizado());

      Date dataFinalizado = null;

      if (tarefa.getDataFinalizacao() != null) {
        dataFinalizado = new Date(tarefa.getDataFinalizacao().getTimeInMillis());
      }

      stmt.setDate(3, dataFinalizado);

      stmt.execute();

      System.out.println("Tarefa criada com sucesso!");
    } catch (final SQLException e) {
      throw new TarefaDaoException(e);
    }
  }
}
