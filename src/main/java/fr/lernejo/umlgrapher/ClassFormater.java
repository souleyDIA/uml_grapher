package fr.lernejo.umlgrapher;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.lernejo.formater.IClassFormater;
import fr.lernejo.formater.IConstructor;
import fr.lernejo.formater.IFieldFormater;
import fr.lernejo.formater.IInterfaceFormater;
import fr.lernejo.formater.IMethodFormater;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class ClassFormater implements IClassFormater {
   
    private final IFieldFormater fieldFormater;
    private final IMethodFormater methodFormater;
    private final IInterfaceFormater interfaceFormater;
    private final IConstructor constructorFormater;

    public ClassFormater(IFieldFormater fieldFormater, MethodFormater methodFormater, IInterfaceFormater interfaceFormater, IConstructor constructorFormater) {
        this.fieldFormater = fieldFormater;
        this.methodFormater = methodFormater;
        this.interfaceFormater = interfaceFormater;
        this.constructorFormater = constructorFormater;
    }

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
            clazz.getDeclaredMethods(),
            clazz.getInterfaces(),
            clazz.getDeclaredConstructors()
        ).flatMap(Stream::of)
            .map(m -> {
                if (m instanceof Field) return fieldFormater.format((Field) m);
                else if (m instanceof Method) return methodFormater.format((Method) m);
                else if (m instanceof Class) return interfaceFormater.format((Class<?>) m);
                else if (m instanceof Constructor) return constructorFormater.format((Constructor<?>) m);
                
                return "";
            }).collect(Collectors.joining("\n"));

    }
}
    
