package fr.lernejo.umlgrapher;

public class UmlGraph {

    private final Class<?> [] cls;

    
    private final RelationFormater formater = new RelationFormater(); 
    private final InterfaceFormater interfaceFormater = new InterfaceFormater();  
    private final ClassFormater classFormater = new ClassFormater(interfaceFormater);   


    public UmlGraph(Class<?> ...classes) {        
        this.cls = classes;

    }
 
    public String as(GraphType type) {
        if(type == GraphType.Mermaid){
            StringBuilder sb = new StringBuilder();
            sb.append("classDiagram\n");
            for (Class<?> c : cls) {
                sb.append(classFormater.format(c));
                sb.append(formater.format(c));
            }
            return sb.toString();
        }
        else{
            return "";
        }
    }

} 
