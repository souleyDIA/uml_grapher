package fr.lernejo.umlgrapher;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UmlGraph {

    private final Class<?> cls;
    private final RelationFormater formater;
    private final ClassFormater classFormater; 

    public UmlGraph(Class<?> cls, 
                    RelationFormater formater, 
                    ClassFormater classFormater) {        
        this.cls = cls;
        this.formater = formater;
        this.classFormater = classFormater;
    }
 
    public String as(GraphType type) {
        if (type == GraphType.Mermaid) {
            String classDiagram = Stream.of(cls.getDeclaredClasses())
            .map(classFormater::format)
            .collect(Collectors.joining("\n"));

            String classDiagramOfGivenClass = classFormater.format(cls);
            String relationDiagram = formater.format(cls);
            return  "classDiagram\n" + classDiagram + "\n" + classDiagramOfGivenClass + "\n" + relationDiagram + "\n"; 
        }
        return "";
    }
} 
