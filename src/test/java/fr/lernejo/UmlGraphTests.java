package fr.lernejo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.lernejo.umlgrapher.UmlGraph;
import fr.lernejo.umlgrapher.GraphType;

class UmlGraphTests {

    @Test
    void empty_interface_with_no_relation() {
        UmlGraph graph = new UmlGraph(Machin.class);

        String output = graph.as(GraphType.Mermaid);

        Assertions.assertThat(output).isEqualTo("""
            classDiagram
            class Machin {
                <<interface>>
            }
            """);
    }

    interface Machin {
    }

    @Test
    void parse_arguments() {
        String[] args = new String[] {"-c", "fr.lernejo.Machin", "-g", "Mermaid",};

        String className = args[0];
        String graphType = GraphType.Mermaid.toString();

        for(int i = 0; i < args.length; i++) {
            if(args[i].equals("-c") || args[i].equals("--classes")) {
                className = args[i + 1];
            }
            if(args[i].equals("-g") || args[i].equals("--graph-type")) {
                graphType = args[i + 1];
            }
        }

        Assertions.assertThat(className).isEqualTo("fr.lernejo.Machin");
        Assertions.assertThat(graphType).isEqualTo("Mermaid");
    }

    @Test
    void test_class_not_found() {
        Assertions.assertThatThrownBy(() -> new UmlGraph(Class.forName("fr.lernejo.FakeClass")))
            .isInstanceOf(ClassNotFoundException.class)
            .hasMessage("fr.lernejo.FakeClass");
    }    
}