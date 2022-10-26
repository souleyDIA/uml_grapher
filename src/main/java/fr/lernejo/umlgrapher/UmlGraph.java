package fr.lernejo.umlgrapher;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.lernejo.formater.IConstructor;

public class UmlGraph {

    private final List<Class>    cls;

    
    private final RelationFormater formater = new RelationFormater(); 
    private final MermaidHelper helper = new MermaidHelper();
    private final FieldFormater fieldFormater = new FieldFormater(helper); 
    private final MethodFormater methodFormater = new MethodFormater(new ParameterFormater());
    private final InterfaceFormater interfaceFormater = new InterfaceFormater();  
    private final IConstructor constructorFormater = new ConstructorFormater(new ParameterFormater(), helper);
    private final ClassFormater classFormater = new ClassFormater(fieldFormater, methodFormater, interfaceFormater, constructorFormater);   


    public UmlGraph(List<Class> classes) {        
        this.cls = classes;

    }
 
    public String as(GraphType type) {
        if(type == GraphType.Mermaid){
            StringBuilder sb = new StringBuilder();
            sb.append("classDiagram\n");
            for (Class<?> c : cls) {
                String classDiagram = Stream.of(c.getDeclaredClasses())
                .map(classFormater::format)
                .collect(Collectors.joining("\n"));
                sb.append(classDiagram);
                //sb.append(formater.format(c));
            }
            return sb.toString();
        }
        else{
            return "";
        }
    }

} 
