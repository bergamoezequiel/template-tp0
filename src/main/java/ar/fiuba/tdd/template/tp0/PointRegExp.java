package ar.fiuba.tdd.template.tp0;

/**
 * Created by cbergamo on 15/03/2016.
 */
public class PointRegExp {

    public static boolean isSubRegEx(String exp) {
        if (  ( exp == null ) || ( exp.length() == 0 )  ) {

            return false;
        }
        String primerChar = exp.substring(0,1);

        String charPoint = "." ;
        if ( ( exp.length() == 1 ) && ( primerChar.equals(charPoint) ) ) {

            return true;
        }
        else {
            System.out.println("entro");
            if (exp.length() == 2) {
                String segundoChar = exp.substring(1, 2);
                if (Comodin.esComodin(segundoChar) && primerChar.equals(charPoint)) {
                    return true;
                }
            }
        }
        return false;
    }
}
