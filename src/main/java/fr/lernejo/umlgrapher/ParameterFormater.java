package fr.lernejo.umlgrapher;

import java.lang.reflect.Parameter;
import java.util.List;
import java.util.stream.Collectors;

import fr.lernejo.formater.IParamterFormater; 

public class ParameterFormater implements IParamterFormater{

    @Override
    public String format(Parameter parameter) {
        return parameter.getType().getSimpleName() + " " + parameter.getName();
    }

    @Override
    public String format(List<Parameter> parameters) {
        return parameters.stream().map(this::format).collect(Collectors.joining(", "));
    }
}