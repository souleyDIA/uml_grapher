
package fr.lernejo.umlgrapher;

import java.lang.reflect.Field;

import fr.lernejo.formater.IFieldFormater;

public class FieldFormater implements IFieldFormater {
    
    private final MermaidHelper helper;
    
    public FieldFormater(MermaidHelper helper) {
        this.helper = helper;
    }
    
    @Override
    public String format(Field field) {
        StringBuilder sb = new StringBuilder();

        sb.append(helper.Visibility(field.getModifiers()));
        sb.append(field.getType().getSimpleName());
        sb.append(" ");
        sb.append(field.getName());
        sb.append(helper._static(field.getModifiers()));
        return sb.toString();
    }
}
