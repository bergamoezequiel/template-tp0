package ar.fiuba.tdd.template.tp0;

/**
 * Created by cbergamo on 16/03/2016.
 */
public class SetRegExp {

    public static boolean isSubRegEx(String exp) {
        if (  ( exp == null ) || ( exp.length() == 0 )  ) {

            return false;
        }
        String primerChar = exp.substring(0,1);

        if ( primerChar.equals("[") ) {
            if (exp.length() == 1) {
                return true;
            }
            System.out.println(exp.substring(1,Math.max(1, exp.length() - 1)));
            String intermediate = exp.substring(1,Math.max(1, exp.length() - 1));
           // System.out.println(HasIntermediateBrackets(intermediate));
            if ( !HasIntermediateBrackets(intermediate) ) {
                String ultimoChar = exp.substring(exp.length() - 1,exp.length());
                if ( !ultimoChar.equals("[") ) {
                    return true;
                }

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
}
