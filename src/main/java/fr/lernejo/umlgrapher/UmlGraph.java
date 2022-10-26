package fr.lernejo.umlgrapher;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.lernejo.UmlRelationAnalysis;
import fr.lernejo.mermaid.ClassFormater;
import fr.lernejo.mermaid.FieldFormater;
import fr.lernejo.mermaid.InterfaceFormater;
import fr.lernejo.mermaid.MermaidHelper;
import fr.lernejo.mermaid.MethodFormater;
import fr.lernejo.mermaid.ParameterFormater;
import fr.lernejo.mermaid.RelationFormater;

public class UmlGraph {
 
    private final Class<?> cls;
 
    public UmlGraph(Class<?> cls) {
        this.cls = cls;
    }

    public String as(GraphType type) {
        if (type == GraphType.Mermaid) {
            // UmlRelationAnalysis analysis = new UmlRelationAnalysis(List.of(cls));
            RelationFormater formater = new RelationFormater(); 
            MermaidHelper helper = new MermaidHelper();
            FieldFormater fieldFormater = new FieldFormater(helper); 
            MethodFormater methodFormater = new MethodFormater(new ParameterFormater());
            InterfaceFormater interfaceFormater = new InterfaceFormater();  
            ClassFormater classFormater = new ClassFormater(fieldFormater, methodFormater, interfaceFormater);

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
