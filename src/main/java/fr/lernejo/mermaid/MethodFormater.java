package fr.lernejo.mermaid;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

import fr.lernejo.formater.IMethodFormater;

public class MethodFormater implements IMethodFormater{
    
    private final ParameterFormater parameterFormater = new ParameterFormater();

    @Override
    public String format(Method method) {
        StringBuilder sb = new StringBuilder();
        MermaidHelper helper = new MermaidHelper();
        List<Parameter> parameters = List.of(method.getParameters());
        sb.append(helper.Visibility(method.getModifiers()));
        sb.append(method.getName());
        sb.append("(");
        sb.append(parameterFormater.format((parameters)));
        sb.append(")");
        sb.append(helper._static(method.getModifiers()));
        sb.append(
            method.getReturnType().getSimpleName().equals("void") ? "" : " : " + method.getReturnType().getSimpleName()
        );
        return sb.toString();
    }
}