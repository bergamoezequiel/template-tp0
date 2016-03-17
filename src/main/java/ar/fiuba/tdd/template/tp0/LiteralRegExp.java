package ar.fiuba.tdd.template.tp0;

/**
 * Created by cbergamo on 16/03/2016.
 */
public class LiteralRegExp {
    public static boolean isSubRegEx(String exp) {
        if (  ( exp == null ) || ( exp.length() == 0 )  ) {
            return false;
        }
        if (exp.length() == 3 && tieneComodin(exp) && estaEscapado(exp)) {
            return true;
        }
        if (exp.length() == 2) {
            if ( estaEscapado(exp) ) {
                return true;
            }
            else {
                if ( tieneComodin(exp) ) {
                    String primerChar = exp.substring(0,1);
                    System.out.println("entro a verificar si es comun luego de comn");
                    System.out.println(exp);
                    return noEsCaracterReservado(primerChar);
                }
            }
        }
        return ( exp.length() == 1 && noEsCaracterReservado(exp));
    }

    private static boolean tieneComodin(String exp) {
        return ( Comodin.esComodin(exp.substring( exp.length() - 1,exp.length())) );
    }

    private static boolean estaEscapado(String exp) {
        String scape = exp.substring(0,1);
        return (scape.equals("\\"));

    }

    private static boolean noEsCaracterReservado(String exp) {
        String caracteresEspeciales = ".[]+-*?";
        return (!caracteresEspeciales.contains(exp));
    }

}

