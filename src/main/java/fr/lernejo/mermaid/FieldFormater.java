package fr.lernejo.mermaid;

import java.lang.reflect.Field;

import fr.lernejo.formater.IFieldFormater;

public class FieldFormater implements IFieldFormater {
    
    @Override
    public String format(Field field) {
        StringBuilder sb = new StringBuilder();
        MermaidHelper helper = new MermaidHelper();

        sb.append(helper.Visibility(field.getModifiers()));
        sb.append(field.getType().getSimpleName());
        sb.append(helper._static(field.getModifiers()));
        return sb.toString();
    }
}
