package pl.cezarysanecki.templateforspringboot.util;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class StreamWrapper<T> {

    private final Stream<T> stream;

    public static <T> StreamWrapper<T> of(Iterable<T> iterator) {
        return of(iterator.iterator());
    }

    public static <T> StreamWrapper<T> of(Iterator<T> iterator) {
        Stream<T> stream = StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(
                        iterator, Spliterator.ORDERED), false);
        return new StreamWrapper<>(stream);
    }

    public Stream<T> stream() {
        return stream;
    }

}
