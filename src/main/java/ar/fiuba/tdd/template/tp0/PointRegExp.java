package ar.fiuba.tdd.template.tp0;

import java.util.Random;

/**
 * Created by cbergamo on 15/03/2016.
 */
public class PointRegExp extends AbstractRegularExpresion implements RegularExpresion {


    public  boolean isSubRegEx(String exp) {

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
            if (exp.length() == 2 && Comodin.esComodin(exp.substring(1, 2)) && primerChar.equals(charPoint)) {
                return true;

            }
        }
        return false;
    }





    public boolean tieneComodin(String exp) {
        return (exp.length() == 2 && Comodin.esComodin(exp.substring(1,2)));
    }

    public String generateMatchingString(int max) {
        int maximo = determinarMaximo(max);
        int minimo = determinarMinimo();

        System.out.println("La cantidad maxima es " + maximo + " y la minima es " + minimo);
        Random ran = new Random();
        char car = (char)(ran.nextInt(50) + 35);
        String literal = String.valueOf(car);
        return obtenerLiteralxCantidadDeVeces(minimo,maximo,literal);


    }


}
