package fr.lernejo.umlgrapher;

public class Launcher {

    public static void main(String[] args) {

        if(args.length == 0) {
            System.out.println("Please enter the name of the class to be analyzed");
            return;
        }

        String className = args[0];
        String graphType = GraphType.Mermaid.toString();
        String server = "http://localhost:1234";

        for(int i = 0; i < args.length; i++) {
            if(args[i].equals("-c") || args[i].equals("--classes")) {
                className = args[i + 1];
            }
            if(args[i].equals("-g") || args[i].equals("--graph-type")) {
                graphType = args[i + 1];
            }
            if(args[i].equals("-s") || args[i].equals("--server")) {
                server = args[i + 1];
            }
        }

        System.out.println("Class name: " + className);
        System.out.println("Graph type: " + graphType);
        System.out.println("Server: " + server);

        try {
            Class<?> cls = Class.forName(className);
            UmlGraph graph = new UmlGraph(cls);
            String output = graph.as(GraphType.valueOf(graphType));
            System.out.println(output);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }
    }
}
// mvn compile exec:java -Dexec.args="-c=fr.lernejo.Sample" -Dgithub_token=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