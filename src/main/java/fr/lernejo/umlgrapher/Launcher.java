package fr.lernejo.umlgrapher;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "java -jar umlgrapher.jar", mixinStandardHelpOptions = true, version = "1.0", description = "UMLGrapher")
public class Launcher implements Callable<Integer> {

    @Option(names = {"-c", "--classes"}, required = true, description = "Classes to analyse")
    private String classes;

    @Option(names = {"-g", "--graph-type"}, defaultValue = "Mermaid", description = "Graph type")
    private String graphType;

    @Override
    public Integer call() throws Exception {
        UmlGraph graph = new UmlGraph(Class.forName(classes));
        System.out.println(graph.as(GraphType.valueOf(graphType)));
        return 0;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new Launcher()).execute(args);
        System.exit(exitCode);
    }
}