package ar.fiuba.tdd.template.tp0;

/**
 * Created by cbergamo on 16/03/2016.
 */
public class SetRegExp {

    public static boolean isSubRegEx(String exp) {

        if (  ( exp == null ) || ( exp.length() < 3 )  ) {

            return false;
        }
        if (tieneComodin(exp)) {
            exp = exp.substring(0,exp.length() - 1);
        }
        String primerChar = exp.substring(0,1);
        String anteUltimoChar = exp.substring( exp.length() - 2,exp.length() - 1);
        String ultimoChar = exp.substring( exp.length() - 1,exp.length() );

        if ( primerChar.equals("[") && ultimoChar.equals("]") ) {
             String antepenultimoChar = exp.substring( exp.length() - 3,exp.length() - 2);
            if (anteUltimoChar.equals("\\") && !antepenultimoChar.equals("\\")  ) {
                return false;
            }
            String intermediate = exp.substring(1,Math.max(1, exp.length() - 1));
            if ( !HasIntermediateBrackets(intermediate) ) {
                return true;
            }
        }
        return false;
    }

    private static boolean HasIntermediateBrackets(String intermediateString) {
        if ( intermediateString.contains("[") || intermediateString.contains("]") ) {
            for (int i = 0;i < intermediateString.length();i++) {
                String sub = intermediateString.substring(i, i + 1);
                if ( sub.equals("[") || sub.equals("]") ) {
                    if ( i == 0 ) {
                        return true;
                    }
                    else {
                        if (!intermediateString.substring(i - 1, i).equals("\\")) {
                            return true;
                        }

                    }

                }

            }

        }
        return false;
    }

    private static boolean tieneComodin(String exp) {
        return ( Comodin.esComodin(exp.substring( exp.length() - 1,exp.length())) );
    }
}
