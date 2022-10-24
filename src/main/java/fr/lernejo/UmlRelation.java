package fr.lernejo;

public class UmlRelation {
    public final Class<?> from;
    public final Class<?> to;
    public final Type type;

    public UmlRelation(Class<?> from, Class<?> to, Type type) {
        this.from = from;
        this.to = to;
        this.type = type;
    }

    @Override
    public int hashCode() {
        return from.hashCode() + to.hashCode() + type.hashCode();
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
