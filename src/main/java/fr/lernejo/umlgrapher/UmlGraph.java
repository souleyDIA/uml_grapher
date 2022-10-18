package fr.lernejo.umlgrapher;

public class UmlGraph {
 
    private final Class<?> cls;
 
    public UmlGraph(Class<?> cls) {
        this.cls = cls;
    }

    public String as(GraphType type) {
        if (type == GraphType.Mermaid) {
            return """
                classDiagram
                class %s {
                    <<interface>>
                }
                """.formatted(cls.getSimpleName());
        }
        return "";
    }
} 
