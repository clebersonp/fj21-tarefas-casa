package br.com.caelum.tarefas.infraestrutura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

  private static final String URL = "jdbc:mysql://localhost:3306/fj21?useSSL=false";
  private static final String USER = "root";
  private static final String PASSWORD = USER;
  private static final String CLASS_DRIVER = "com.mysql.jdbc.Driver";

  public Connection getConnection() {

    try {
      // registrando o driver do mysql para o tomcat encontrar a classe
      Class.forName(CLASS_DRIVER);

      return DriverManager.getConnection(URL, USER, PASSWORD);

    } catch (final ClassNotFoundException | SQLException e) {
      throw new RuntimeException("Erro ao carregar o driver do banco de dados!", e);
    }
  }
}
