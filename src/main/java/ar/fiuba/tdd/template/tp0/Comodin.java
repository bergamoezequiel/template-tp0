package ar.fiuba.tdd.template.tp0;

/**
 * Created by cbergamo on 16/03/2016.
 */

public class Comodin {

    public static int maxlenght(String comod) {
        if (comod.equals("?")) {
            return 1;
        }
        else {
            return 99999;
        }
    }

    public static int minlenght(String comod) {
        if (comod.equals("+")) {
            return 1;
        }
        else {
            return 0;
        }
    }

    public static boolean esComodin(String com) {
        return ( com.equals("?") || com.equals("*") || com.equals("+") );
    }

    public static int  minimaCantidadDeApariciones(String com) {
        if (!esComodin(com)) {
            return -1;
        }

        if (com.equals("?") || com.equals("*")) {
            return 0;
        }
        else {
            return 1;
        }
    }


}


