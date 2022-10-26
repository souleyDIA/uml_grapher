package fr.lernejo.umlgrapher;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.List;

import fr.lernejo.formater.IConstructor;

public class ConstructorFormater implements IConstructor {
    private final ParameterFormater parameterFormater;
    private final MermaidHelper helper;
    
    public ConstructorFormater(ParameterFormater parameterFormater, MermaidHelper helper) {
        this.parameterFormater = parameterFormater;
        this.helper = helper;
    }

    @Override
    public String format(Constructor<?> constructor) {
        StringBuilder result = new StringBuilder();
        List<Parameter> parameters = List.of(constructor.getParameters());
		result.append(helper.Visibility(constructor.getModifiers()));
		result.append(constructor.getDeclaringClass().getSimpleName());
		result.append("(");
		result.append(parameterFormater.format(parameters));
		result.append(")");
		return result.toString();
    }
}
