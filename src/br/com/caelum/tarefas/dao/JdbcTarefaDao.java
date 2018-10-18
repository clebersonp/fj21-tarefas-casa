package br.com.caelum.tarefas.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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

  public List<Tarefa> lista() {
    final List<Tarefa> tarefas = new ArrayList<>();

    final String sql = "select * from tarefas";
    try (final PreparedStatement stmt = this.connection.prepareStatement(sql)) {
      final ResultSet rs = stmt.executeQuery();
      while (rs.next()) {

        Calendar dataFinalizacao = null;

        if (rs.getDate("data_finalizacao") != null) {
          dataFinalizacao = Calendar.getInstance();
          dataFinalizacao.setTime(rs.getDate("data_finalizacao"));
        }

        final Tarefa tarefa = new Tarefa(rs.getLong("id"), rs.getString("descricao"),
            rs.getBoolean("finalizado"), dataFinalizacao);

        tarefas.add(tarefa);
      }
      return tarefas;
    } catch (final SQLException e) {
      throw new TarefaDaoException(e);
    }
  }

  public void remover(final Tarefa tarefa) {
    final String sql = "delete from tarefas where id = ?";
    if ((tarefa != null) && (tarefa.getId() != null)) {
      try (final PreparedStatement stmt = this.connection.prepareStatement(sql)) {
        stmt.setLong(1, tarefa.getId());
        stmt.execute();
      } catch (final SQLException e) {
        throw new TarefaDaoException(e);
      }
    }
  }

  public Tarefa recuperar(final Long id) {
    Tarefa tarefa = null;
    final String sql = "select * from tarefas where id = ?";

    try (final PreparedStatement stmt = this.connection.prepareStatement(sql)) {

      stmt.setLong(1, id);

      final ResultSet rs = stmt.executeQuery();
      if (rs.next()) {

        Calendar dataFinalizacao = null;

        if (rs.getDate("data_finalizacao") != null) {
          dataFinalizacao = Calendar.getInstance();
          dataFinalizacao.setTime(rs.getDate("data_finalizacao"));
        }

        tarefa = new Tarefa(rs.getLong("id"), rs.getString("descricao"),
            rs.getBoolean("finalizado"), dataFinalizacao);
      }

      return tarefa;

    } catch (final SQLException e) {
      throw new TarefaDaoException(e);
    }
  }

  public void alterar(final Tarefa tarefa) {

    if ((tarefa != null) && (tarefa.getId() != null)) {
      final String sql =
          "update tarefas set descricao=?, finalizado=?, data_finalizacao=? where id = ?";
      try (final PreparedStatement stmt = this.connection.prepareStatement(sql)) {
        stmt.setLong(4, tarefa.getId());
        stmt.setString(1, tarefa.getDescricao());
        stmt.setBoolean(2, tarefa.isFinalizado());

        Date dataFinalizado = null;
        if (tarefa.getDataFinalizacao() != null) {
          dataFinalizado = new Date(tarefa.getDataFinalizacao().getTimeInMillis());
        }

        stmt.setDate(3, dataFinalizado);

        stmt.execute();
      } catch (final SQLException e) {
        throw new TarefaDaoException(e);
      }
    }

  }
}
