package fr.lernejo.umlgrapher;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UmlGraph {

    private final Class<?> cls;
    public UmlGraph(Class<?> cls) {
        this.cls = cls;
    }
    private final RelationFormater formater = new RelationFormater(); 
    private final MermaidHelper helper = new MermaidHelper();
    private final FieldFormater fieldFormater = new FieldFormater(helper); 
    private final MethodFormater methodFormater = new MethodFormater(new ParameterFormater());
    private final InterfaceFormater interfaceFormater = new InterfaceFormater();  
    private final ClassFormater classFormater = new ClassFormater(fieldFormater, methodFormater, interfaceFormater);   

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
