package fr.lernejo.umlgrapher;

import fr.lernejo.formater.IClassFormater;
import fr.lernejo.formater.IInterfaceFormater;


public class ClassFormater implements IClassFormater {
   
    private final IInterfaceFormater interfaceFormater;

    public ClassFormater(IInterfaceFormater interfaceFormater) {
        this.interfaceFormater = interfaceFormater;
    }

    @Override
    public String format(Class<?> clazz) {
        StringBuilder sb = new StringBuilder("class ");
        sb.append(clazz.getSimpleName());
        sb.append(" {\n");
        sb.append(interfaceFormater.format(clazz));
        //if the class has members (fields or methods) 
        sb.append("\n}");
        return sb.toString();
    }
}
    
