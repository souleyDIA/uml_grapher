package fr.lernejo.mermaid;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.lernejo.formater.IClassFormater;
import fr.lernejo.formater.IFieldFormater;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class ClassFormater implements IClassFormater {
  
    private final IFieldFormater fieldFormater = new FieldFormater();
    private final MethodFormater methodFormater = new MethodFormater();

    @Override
    public String format(Class<?> clazz) {
        StringBuilder sb = new StringBuilder("class ");
        sb.append(clazz.getSimpleName());
        sb.append("{\n");
        sb.append(
            Stream.of(member(clazz).split("\n")).filter(s -> !s.isEmpty())
                .collect(Collectors.joining("\n\t", "\t", ""))
                
        );
        sb.append("\n}");
        return sb.toString();
    }

    protected String member(Class<?> clazz) {
        return Stream.of(
            clazz.getDeclaredFields(),
            clazz.getDeclaredMethods()
        ).map(
            Stream::of
        ).flatMap(
            s -> s
        ).map(
            m -> m instanceof Field ? fieldFormater.format((Field) m) : methodFormater.format((Method) m)
        ).collect(Collectors.joining("\n"));

    }
}
    
