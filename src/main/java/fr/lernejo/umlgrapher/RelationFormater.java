package fr.lernejo.umlgrapher;

import fr.lernejo.formater.IRelationFormater;

public class RelationFormater implements IRelationFormater {
     
    @Override
    public String format(Class<?> clazz) {  
        StringBuilder sb = new StringBuilder();
         if(clazz.isInterface()) {
            sb.append(clazz.getSimpleName());
            sb.append(" ..|> ");
            sb.append(" : ");
            sb.append(clazz.getName());
        } else {
            Class<?> c = clazz.getSuperclass();
            if(c != null) {
                sb.append(clazz.getSimpleName());
                sb.append(" --|> ");
                sb.append(" : ");
                sb.append(clazz.getName());
            }
        }
        return sb.toString();
    }
}
