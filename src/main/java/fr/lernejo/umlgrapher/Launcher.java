package fr.lernejo.umlgrapher;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

import fr.lernejo.formater.IConstructor;

@Command(name = "java -jar umlgrapher.jar", mixinStandardHelpOptions = true, version = "1.0", description = "UMLGrapher")
public class Launcher implements Callable<Integer> {

    private final RelationFormater formater = new RelationFormater(); 
    private final MermaidHelper helper = new MermaidHelper();
    private final FieldFormater fieldFormater = new FieldFormater(helper); 
    private final MethodFormater methodFormater = new MethodFormater(new ParameterFormater());
    private final InterfaceFormater interfaceFormater = new InterfaceFormater();  
    private final IConstructor constructorFormater = new ConstructorFormater(new ParameterFormater(), helper);
    private final ClassFormater classFormater = new ClassFormater(fieldFormater, methodFormater, interfaceFormater, constructorFormater);   

    @Option(names = {"-c", "--classes"}, required = true, description = "Classes to analyse")
    private String classes;

    @Option(names = {"-g", "--graph-type"}, defaultValue = "Mermaid", description = "Graph type")
    private String graphType;

    @Override
    public Integer call() throws Exception {
        UmlGraph graph = new UmlGraph(Class.forName(classes), formater, classFormater);
        System.out.println(graph.as(GraphType.valueOf(graphType)));
        return 0;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new Launcher()).execute(args);
        System.exit(exitCode);
    }
}