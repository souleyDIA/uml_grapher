package fr.lernejo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UmlRelationAnalysis {

    private final Set<Class<?>> classes = new HashSet<>();
    private final Set<UmlRelation> relations = new HashSet<>();
    
    public UmlRelationAnalysis(List<Class<?>> classes) {
        for (Class<?> cls : classes) {
            analyze(cls);
        }
    }

    public List<Class<?>> listClasses() {
        return classes.stream().collect(Collectors.toList());
    }

    public List<UmlRelation> listRelations() {
        return relations.stream().collect(Collectors.toList());
    }

    public void analyze(Class<?> cls) {
        classes.add(cls);

        if(isNullOrObject(cls)) {
            return;
        }
        
        for (Class<?> i : cls.getInterfaces()) {
            relations.add(new UmlRelation(cls, i, cls.isInterface() ? UmlRelation.Type.implement : UmlRelation.Type.extend));
            analyze(i);
        }
        if (cls.getSuperclass() != null) {
            relations.add(new UmlRelation(cls, cls.getSuperclass(), UmlRelation.Type.extend));
            analyze(cls.getSuperclass());
        }
    }

    private boolean isNullOrObject(Class<?> cls) {
        return cls == null || cls == Object.class;
    }
    
}