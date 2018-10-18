package br.com.caelum.tarefas.exception;

public class TarefaDaoException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public TarefaDaoException() {
    super();
  }

  public TarefaDaoException(final String message, final Throwable cause,
      final boolean enableSuppression, final boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public TarefaDaoException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public TarefaDaoException(final String message) {
    super(message);
  }

  public TarefaDaoException(final Throwable cause) {
    super(cause);
  }
}
