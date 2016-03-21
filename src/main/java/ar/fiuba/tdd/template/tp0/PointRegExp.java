package ar.fiuba.tdd.template.tp0;

import java.util.Random;


public class PointRegExp extends AbstractRegularExpresion implements RegularExpresion {


    public  boolean isSubRegEx(String exp) {

        if (  ( exp == null ) || ( exp.length() == 0 )  ) {

            return false;
        }
        String firstChar = exp.substring(0,1);

        String charPoint = "." ;
        if ( ( exp.length() == 1 ) && ( firstChar.equals(charPoint) ) ) {
            return true;
        } else {
            if (exp.length() == 2 && Comodin.esComodin(exp.substring(1, 2)) && firstChar.equals(charPoint)) {
                return true;

            }
        }
        return false;
    }





    public boolean hasComodin(String exp) {
        return (exp.length() == 2 && Comodin.esComodin(exp.substring(1,2)));
    }

    public String generateMatchingString(int max) {
        int maximo = determineMaximum(max);
        int minimo = determineMinimum();
        Random ran = new Random();
        char car = (char)(ran.nextInt(50) + 35);
        String literal = String.valueOf(car);
        return obtainLiteralxTimes(minimo,maximo,literal);


    }


}
