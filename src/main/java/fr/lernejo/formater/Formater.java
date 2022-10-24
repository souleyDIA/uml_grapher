package fr.lernejo.formater;

import java.util.List;
import java.util.stream.Collectors;

public interface Formater<T>{
    
    String format(T t);

    default String format(List<T> list) {
        return list.stream().map(this::format).collect(Collectors.joining("\r"));
    }
}
