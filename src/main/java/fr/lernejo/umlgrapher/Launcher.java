package fr.lernejo.umlgrapher;

public class Launcher {

    public static void main(String[] args) {
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