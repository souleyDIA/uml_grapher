package fr.lernejo.umlgrapher;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "java -jar umlgrapher.jar", mixinStandardHelpOptions = true, version = "1.0", description = "UMLGrapher")
public class Launcher implements Callable<Integer> {

    @Option(names = {"-c", "--classes"}, required = true, description = "Classes to analyse")
    // array of classes to analyse
    private final Class<?> [] classes = null;

    @Option(names = {"-g", "--graph-type"}, defaultValue = "Mermaid", description = "Graph type")
    private final GraphType graphType = GraphType.Mermaid;

    @Override
    public Integer call() throws Exception {
        UmlGraph graph = new UmlGraph(classes);
        System.out.println(graph.as(graphType));
        return 0;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new Launcher()).execute(args);
        System.exit(exitCode);
    }
}
