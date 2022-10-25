package fr.lernejo.mermaid;

import fr.lernejo.UmlRelation;
import fr.lernejo.formater.IRelationFormater;
import java.lang.reflect.Field;


public class RelationFormater implements IRelationFormater {
    
    @Override
    public String format(UmlRelation relation) {  
        StringBuilder sb = new StringBuilder();
        Field type;
        try {
            sb = new StringBuilder(relation.getClass().getDeclaredField("from").getName());
            type = relation.getClass().getDeclaredField("type");
            type.setAccessible(true);

            switch (type.toString()) {
             case "extend": sb.append(" --> "); break;
             case "implement": sb.append(" ..> "); break;
            }
            sb.append(relation.getClass().getDeclaredField("to").getName());

        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
