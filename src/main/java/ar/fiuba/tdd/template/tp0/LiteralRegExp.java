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
        if (exp.length() == 3 && hasComodin(exp) && estaEscapado(exp)) {
            return true;
        }

        boolean hasValidLong2 = has2ValidStructure(exp);
        return (hasValidLong2 || (exp.length() == 1 && noEsCaracterReservado(exp)));
    }

    public boolean has2ValidStructure(String exp) {
        if (exp.length() == 2) {
            if ( estaEscapado(exp) ) {
                return true;
            } else {
                if ( hasComodin(exp) ) {
                    String firstChar = exp.substring(0,1);
                    return noEsCaracterReservado(firstChar);
                }
            }
        }
        return false;
    }

    public  boolean hasComodin(String exp) {
        return ( Comodin.esComodin(exp.substring( exp.length() - 1,exp.length())) );
    }

    private static boolean estaEscapado(String exp) {
        String scape = exp.substring(0,1);
        return (scape.equals("\\"));

    }

    private static boolean noEsCaracterReservado(String exp) {
        String specialCarater = ".[]+-*?";
        return (!specialCarater.contains(exp));
    }



    public String generateMatchingString(int maxi) {

        int maximum = determineMaximum(maxi);
        int minimum = determineMinimum();
        String literal = obetenerLiteral();

        return obtainLiteralxTimes(minimum,maximum,literal);

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
        } else {
            if ( hasComodin(regularE) ) {
                literal = regularE.substring(0,1);

            }
        }
        return literal;
    }


}

