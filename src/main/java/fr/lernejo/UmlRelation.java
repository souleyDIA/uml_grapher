package fr.lernejo;

import java.util.Objects;

public class UmlRelation {
    private final Class<?> from;
    private final Class<?> to;
    private final Type type;

    public UmlRelation(Class<?> from, Class<?> to, Type type) {
        this.from = from;
        this.to = to;
        this.type = type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, type);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass())
            return false;
        UmlRelation other = (UmlRelation) obj;
        return from.equals(other.from) && to.equals(other.to) && type.equals(other.type);
    }

    public enum Type {
        extend,
        implement,
    }
}
