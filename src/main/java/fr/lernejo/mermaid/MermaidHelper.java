package fr.lernejo.mermaid;

import java.lang.reflect.Modifier;

public class MermaidHelper {
    
    public String Visibility(int modifiers) {
        if (java.lang.reflect.Modifier.isPublic(modifiers))
            return "+";
        if (java.lang.reflect.Modifier.isProtected(modifiers))
            return "#";
        if (java.lang.reflect.Modifier.isPrivate(modifiers))
            return "-";
        
        return "~";
    }

    public String _static(int modifiers) {
        return Modifier.isStatic(modifiers) ? "$" : "";
    }
}
