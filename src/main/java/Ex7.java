import java.lang.reflect.InvocationTargetException;

public class Ex7 {
    public static void main(String[] args) {
        var teste = new Teste("Andressa", 1, true);

        try {
            System.out.println(Jaqueson.converterJson(teste));
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
