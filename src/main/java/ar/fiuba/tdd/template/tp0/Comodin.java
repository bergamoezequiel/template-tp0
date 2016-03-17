package ar.fiuba.tdd.template.tp0;

/**
 * Created by cbergamo on 16/03/2016.
 */

public class Comodin {
    public static boolean esComodin(String com) {
        return ( com.equals("?") || com.equals("*") || com.equals("+") );
    }
}
