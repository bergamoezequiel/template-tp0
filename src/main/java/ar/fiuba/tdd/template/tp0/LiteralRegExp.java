package ar.fiuba.tdd.template.tp0;

import java.util.Random;

/**
 * Created by cbergamo on 16/03/2016.
 */
public class LiteralRegExp extends AbstractRegularExpresion implements RegularExpresion {


    public boolean isSubRegEx(String exp) {
        if (  ( exp == null ) || ( exp.length() == 0 )  ) {
            return false;
        }
        if (exp.length() == 3 && tieneComodin(exp) && estaEscapado(exp)) {
            return true;
        }

        boolean tieneLong2Valida = tieneEstructura2CaracteresValida(exp);
        return (tieneLong2Valida || (exp.length() == 1 && noEsCaracterReservado(exp)));
    }

    public boolean tieneEstructura2CaracteresValida(String exp) {
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
        return false;
    }

    public  boolean tieneComodin(String exp) {
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



    public String generateMatchingString(int maxi) {

        int maximo = determinarMaximo(maxi);
        int minimo = determinarMinimo();
        System.out.println("La cantidad maxima es " + maximo + " y la minima es " + minimo);
        String literal = obetenerLiteral();

        return obtenerLiteralxCantidadDeVeces(minimo,maximo,literal);

    }



    private String obetenerLiteral() {
        String literal ;
        if (regularE.length() == 1 ) {
            literal = regularE.substring(0,1);
            return literal;
        }
        if (regularE.length() == 3 ) {
            literal = regularE.substring(1,2);
            return literal;
        }
        return obtenerLiteralDeLargo2(regularE);

    }

    public String obtenerLiteralDeLargo2(String regularE) {
        String literal = "";
        if ( estaEscapado(regularE) ) {
            literal = regularE.substring(1,2);
        }
        else {
            if ( tieneComodin(regularE) ) {
                literal = regularE.substring(0,1);

            }
        }
        return literal;
    }


}

