package ar.fiuba.tdd.template.tp0;

import java.util.Random;


public class SetRegExp extends AbstractRegularExpresion  {

    public  boolean isSubRegEx(String exp) {
        if (  ( exp == null ) || ( exp.length() < 3 )  ) {

            return false;
        }
        exp = removeComodin(exp);
        return validateStructure(exp);


    }

    private boolean validateStructure(String exp) {
        String firstChar = exp.substring(0,1);
        String anteUltimoChar = exp.substring( exp.length() - 2,exp.length() - 1);
        String lastChar = exp.substring( exp.length() - 1,exp.length() );
        if ( firstChar.equals("[") && lastChar.equals("]") ) {
            String antepenultimoChar = exp.substring( exp.length() - 3,exp.length() - 2);
            if (anteUltimoChar.equals("\\") && !antepenultimoChar.equals("\\")  ) {
                return false;
            }
            String intermediate = exp.substring(1,Math.max(1, exp.length() - 1));
            if ( !hasIntermediateBrackets(intermediate) ) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasIntermediateBrackets(String intermediateString) {
        if ( intermediateString.contains("[") || intermediateString.contains("]") ) {
            for (int i = 0;i < intermediateString.length();i++) {
                String sub = intermediateString.substring(i, i + 1);
                if ( sub.equals("[") || sub.equals("]") ) {
                    if ( i == 0 ) {
                        return true;
                    } else {
                        if (!intermediateString.substring(i - 1, i).equals("\\")) {
                            return true;
                        }

                    }

                }

            }

        }
        return false;
    }

    public boolean hasQuantifier(String exp) {
        return ( Quantifier.isQuantifier(exp.substring( exp.length() - 1,exp.length())) );
    }

    public String generateMatchingString(int max) {
        String intermediate;
        int maximo = determineMaximum(max);
        int minimo = determineMinimum();
        if (hasQuantifier(regularE)) {
            intermediate = regularE.substring(1, Math.max(1, regularE.length() - 2));
        } else {
            intermediate = regularE.substring(1, Math.max(1, regularE.length() - 1));
        }
        intermediate = this.removeLeaks(intermediate);
        String literal = obtainRandomLiteral(intermediate);
        return obtainLiteralxTimes(minimo,maximo,literal);
    }

    private String obtainRandomLiteral(String values) {
        int minimum = 0;
        int maximum = values.length() - 1;
        Random rand = new Random();
        int lugar = rand.nextInt((maximum - minimum) + 1) + minimum;
        return (values.substring(lugar,lugar + 1));
    }

    private String removeLeaks(String intermediate) {
        String anterior = "";
        for (int i = 0; i < intermediate.length();i++) {
            String caracter = intermediate.substring(i,i + 1);

            if (caracter.equals("\\") && !anterior.equals("\\")) {
                StringBuilder sb = new StringBuilder(intermediate);
                sb.deleteCharAt(i);
                intermediate = sb.toString();
                anterior = "";
            } else {
                anterior = caracter;

            }

        }
        return intermediate;
    }



    private String removeComodin(String exp) {
        if (hasQuantifier(exp)) {
            return exp.substring(0,exp.length() - 1);
        } else {
            return exp;
        }
    }

}
