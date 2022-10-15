package fr.lernejo.umlgrapher;

public class UmlGraph {

    public UmlGraph(Class<?>... classes) {
        // TODO
    }

    public  String as(GraphType type) {
        if(type == GraphType.Mermaid) {
            return """
                classDiagram
                class Machin {
                    <<interface>>
                }
                """;
        }
    return "";
    }
} 