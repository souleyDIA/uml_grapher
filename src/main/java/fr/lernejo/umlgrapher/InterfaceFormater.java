package fr.lernejo.umlgrapher;

import java.lang.reflect.Modifier;

import fr.lernejo.formater.IInterfaceFormater;

public class InterfaceFormater implements IInterfaceFormater {
    
    @Override
    public String format(Class<?> clazz) {
        int modifiers = clazz.getModifiers();
        if(Modifier.isInterface(modifiers)) {
            return "    <<interface>>";
        }
        if(Modifier.isAbstract(modifiers)) {
            return "<<abstract>>";
        }

        return "";
    }
}
