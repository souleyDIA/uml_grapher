package fr.lernejo.umlgrapher;

import java.util.List;

import fr.lernejo.UmlRelationAnalysis;
import fr.lernejo.mermaid.ClassFormater;
import fr.lernejo.mermaid.RelationFormater;

public class UmlGraph {
 
    private final Class<?> cls;
 
    public UmlGraph(Class<?> cls) {
        this.cls = cls;
    }

    public String as(GraphType type) {
        if (type == GraphType.Mermaid) {
            UmlRelationAnalysis analysis = new UmlRelationAnalysis(List.of(cls));
            RelationFormater formater = new RelationFormater();
            ClassFormater classFormater = new ClassFormater();
            return "classDiagram\n" + classFormater.format(analysis.listClasses()) + "\n" + formater.format(analysis.listRelations());
        }
        return "";
    }
} 
