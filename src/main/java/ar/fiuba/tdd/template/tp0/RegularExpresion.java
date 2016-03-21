package ar.fiuba.tdd.template.tp0;

/**
 * Created by cbergamo on 17/03/2016.
 */
public interface RegularExpresion {

    boolean isSubRegEx(String exp);

    void setExpresion(String exp);

    String getExpresion();

    int minimaExpresion();

    String generateMatchingString(int max);

    boolean hasComodin(String exp);

}
