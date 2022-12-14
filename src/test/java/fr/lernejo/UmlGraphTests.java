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
                }""");
        }
    
        interface Machin {
        }

        public sealed interface Living {
            sealed interface Animal extends Living {
                final class Ant implements Animal {
                }
        
                final class Cat implements Animal {
                }
            }
        
            sealed interface Plant extends Living {
                sealed interface Tree extends Plant {
                    final class Alder implements Tree {
                    }
                }
            }
        }

        public sealed interface Image {

            void display();
        
            final class RealImage implements Image {
        
                private final String fileName;
        
                public RealImage(String fileName){
                    this.fileName = fileName;
                    loadFromDisk(fileName);
                }
        
                @Override
                public void display() {
                    System.out.println("Displaying " + fileName);
                }
        
                private void loadFromDisk(String fileName){
                    System.out.println("Loading " + fileName);
                }
            }
        
            final class LazyLoadedImage implements Image{
        
                private RealImage realImage;
                private final String fileName;
        
                public LazyLoadedImage(String fileName){
                    this.fileName = fileName;
                }
        
                @Override
                public void display() {
                    if(realImage == null){
                        realImage = new RealImage(fileName);
                    }
                    realImage.display();
                }
            }
        }

    @Test void class_with_relation() {
        UmlGraph graph = new UmlGraph(Image.class);

        String output = graph.as(GraphType.Mermaid);

        Assertions.assertThat(output).isEqualTo("""
            classDiagram
            class Image {
                <<interface>>
            }RealImage <|.. Image :implements
            LazyLoadedImage <|.. Image :implements""");
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

    // add more tests here to get a better code coverage
    @Test
    void parent_class() {
        UmlGraph graph = new UmlGraph(Living.class);

        String output = graph.as(GraphType.Mermaid);

        Assertions.assertThat(output).isEqualTo("""
            classDiagram
            class Living {
                <<interface>>
            }Animal <|.. Living :implements
            Plant <|.. Living :implements""");
    }
}
