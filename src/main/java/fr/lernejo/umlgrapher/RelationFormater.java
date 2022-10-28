package fr.lernejo.umlgrapher;

import java.util.Set;
import java.util.stream.Collectors;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;

import fr.lernejo.formater.IRelationFormater;

public class RelationFormater implements IRelationFormater {
     
    @Override
    public String format(Class<?> clazz) {  
        Class<?> type = clazz; 

        Reflections reflections = new Reflections(new ConfigurationBuilder()
            .forPackage("")
            .forPackage("", type.getClassLoader())
        );
        Set<Class<?>> subTypes = reflections.get(
            Scanners.SubTypes
            .get(type)
            .asClass(this.getClass().getClassLoader(), type.getClassLoader())
        );
        if(type.isInterface()){
            return subTypes.stream().map(c -> c.getSimpleName() + " <|.. " +type.getSimpleName() + " :implements").collect(Collectors.joining("\n"));
        }
        else if(type.getSuperclass() != null){
            return type.getSimpleName() + " <|-- " + type.getSuperclass().getSimpleName() + " :extends" + "\n";
        }
        return "";
    }
}