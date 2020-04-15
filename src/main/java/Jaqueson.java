import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;

public final class Jaqueson {
    public static final String converterJson(Object object) throws InvocationTargetException, IllegalAccessException {

        final var abreJson = "{";
        final var fechaJson = "}";
        var json = new StringJoiner(",");
        Class<?> classeObjeto = object.getClass();



        for(Method m : classeObjeto.getDeclaredMethods()){
            if(m.getAnnotation(JSON.class) != null ){
                final var retorno = m.invoke(object);
                if(m.getReturnType() == boolean.class){
                    final var nomeAtributo = m.getName().split("is")[1].toLowerCase();
                    final var atributo = retorno.toString();

                    json.add("{\"" + nomeAtributo + "\": \"" + atributo + "\"}");

                }else if(m.getReturnType() == String.class){

                    final var nomeAtributo = m.getName().split("get")[1].toLowerCase();
                    final var atributo = retorno.toString();
                    json.add("{\"" + nomeAtributo + "\": \"" + atributo + "\"}");

                } else if(m.invoke(object) instanceof Number){
                    final var nomeAtributo = m.getName().split("get")[1].toLowerCase();
                    final var atributo = retorno.toString();
                    json.add("{\"" + nomeAtributo + "\": \"" + atributo + "\"}");

                }else if(m.getReturnType().isArray()) {
                    var corpoArray = new StringJoiner(",");
                    final var nomeAtributo = m.getName().split("get")[1].toLowerCase();

                    for(int i = 0; i < Array.getLength(retorno); i++){
                        corpoArray.add(Array.get(retorno, i).toString());
                    }


                    json.add("{\"" + nomeAtributo + "\": \"[" + corpoArray.toString() + "]\"}");

                }else if(m.getReturnType() == Collection.class){

                    var corpoArray = new StringJoiner(",");
                    final var nomeAtributo = m.getName().split("get")[1].toLowerCase();
                    final var atributo = (List<?>) retorno;
                    atributo.forEach(a -> corpoArray.add(a.toString()));

                    json.add("{\"" + nomeAtributo + "\": \"[" + corpoArray + "]\"}");
                }else if(retorno == null) {
                    json.add("\"null\"");
                }else {

                    json.add(converterJson(retorno));
                }

            }
        }



        return abreJson + json + fechaJson;
    }


}



