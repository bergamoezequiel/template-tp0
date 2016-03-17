package ar.fiuba.tdd.template.tp0;

/**
 * Created by cbergamo on 16/03/2016.
 */
public class LiteralRegExp {
    public static boolean isSubRegEx(String exp) {
        if (  ( exp == null ) || ( exp.length() == 0 )  ) {

            return false;
        }
        return true;
    }
}
