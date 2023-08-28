package pl.cezarysanecki.templateforspringboot.util;

import java.util.Arrays;
import java.util.Optional;

public enum DefaultEnum {
  DEFAULT;

  public static boolean contains(final String value) {
    return of(value).isPresent();
  }

  public static DefaultEnum ofForce(final String value) {
    return of(value).orElseThrow(() -> new IllegalArgumentException("cannot find enum value for: " + value));
  }

  public static Optional<DefaultEnum> of(final String value) {
    return Arrays.stream(values())
        .filter(enumValue -> enumValue.name().equalsIgnoreCase(value))
        .findFirst();
  }

}
