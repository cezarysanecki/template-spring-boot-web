package pl.cezarysanecki.templateforspringboot.util;

public class ImplementMePleaseException extends RuntimeException {

  public ImplementMePleaseException(final String task, final String comment) {
    super(task + ": " + comment);
  }

}
