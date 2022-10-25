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
        return Objects.equals(to, other.to) && Objects.equals(from, other.from) && type == other.type;
    }

    public enum Type {
        extend,
        implement,
    }
}
