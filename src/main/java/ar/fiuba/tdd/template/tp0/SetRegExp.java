package ar.fiuba.tdd.template.tp0;

import java.util.Random;

/**
 * Created by cbergamo on 16/03/2016.
 */
public class SetRegExp extends AbstractRegularExpresion implements RegularExpresion {




    public  boolean isSubRegEx(String exp) {
        /*String regEx = "\\[[^^].*[^\\\\]\\][+*?]?";
        Pattern pattern = Pattern.compile("^" + regEx + "$");
        Matcher matcher = pattern.matcher(exp);
        return matcher.find();
        */
        if (  ( exp == null ) || ( exp.length() < 3 )  ) {

            return false;
        }
        exp = quitarComodin(exp);
        return validarEstructura(exp);


    }

    private boolean validarEstructura(String exp) {
        String primerChar = exp.substring(0,1);
        String anteUltimoChar = exp.substring( exp.length() - 2,exp.length() - 1);
        String ultimoChar = exp.substring( exp.length() - 1,exp.length() );
        if ( primerChar.equals("[") && ultimoChar.equals("]") ) {
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

    public boolean tieneComodin(String exp) {
        return ( Comodin.esComodin(exp.substring( exp.length() - 1,exp.length())) );
    }



    public String generateMatchingString(int max) {
        String intermediate;
        int maximo = determinarMaximo(max);
        int minimo = determinarMinimo();
        if (tieneComodin(regularE)) {
            intermediate = regularE.substring(1, Math.max(1, regularE.length() - 2));
        }
        else {
            intermediate = regularE.substring(1, Math.max(1, regularE.length() - 1));
        }
        intermediate = this.removeLeaks(intermediate);
        String literal = obtainRandomLiteral(intermediate);
        return obtenerLiteralxCantidadDeVeces(minimo,maximo,literal);
    }

    private String obtainRandomLiteral(String values) {
        int minimo = 0;
        int maximo = values.length() - 1;
        Random rand = new Random();
        int lugar = rand.nextInt((maximo - minimo) + 1) + minimo;
        return (values.substring(lugar,lugar + 1));
    }

    private String removeLeaks(String intermediate) {
        String anterior = "";
        for (int i = 0; i < intermediate.length();i++) {
            String caracter = intermediate.substring(i,i + 1);
            System.out.println("el  caracter actual ----" + caracter + "-----");
            System.out.println("el  caracter anterior ----" + anterior + "-----");

            if (caracter.equals("\\") && !anterior.equals("\\")) {
                System.out.println("entra a deletear en la pos " + i);
                StringBuilder sb = new StringBuilder(intermediate);
                sb.deleteCharAt(i);
                intermediate = sb.toString();
                anterior = "";
            }
            else {
                anterior = caracter;

            }

        }
        System.out.println("luego del quitador de barras, el intermedio quedo " + intermediate);
        return intermediate;
    }



    private String quitarComodin(String exp) {
        if (tieneComodin(exp)) {
            return exp.substring(0,exp.length() - 1);
        }
        else {
            return exp;
        }
    }

}
