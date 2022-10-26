package fr.lernejo.mermaid;

import java.lang.reflect.Modifier;

import fr.lernejo.formater.IInterfaceFormater;

public class InterfaceFormater implements IInterfaceFormater {
    
    @Override
    public String format(Class<?> clazz) {
        int modifiers = clazz.getModifiers();
        if(Modifier.isInterface(modifiers)) {
            return "<<interface>>\n";
        }
        if(Modifier.isAbstract(modifiers)) {
            return "<<abstract>>\n";
        }
        
        return "";
    }
}