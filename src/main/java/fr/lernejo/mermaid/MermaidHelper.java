package fr.lernejo.mermaid;

import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

public class MermaidHelper {
    
    public String Visibility(Member member) {
        int modifiers = member.getModifiers();
        if (java.lang.reflect.Modifier.isPublic(modifiers))
            return "+";
        if (java.lang.reflect.Modifier.isProtected(modifiers))
            return "#";
        if (java.lang.reflect.Modifier.isPrivate(modifiers))
            return "-";
        
        return "~";
    }

    public String _static(Member member) {
        int modifiers = member.getModifiers();
        return Modifier.isStatic(modifiers) ? "$" : "";
    }
}
