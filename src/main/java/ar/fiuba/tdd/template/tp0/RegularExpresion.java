package ar.fiuba.tdd.template.tp0;

public interface RegularExpresion {

    boolean isSubRegEx(String exp);

    void setExpresion(String exp);

    String getExpresion();

    int minimumExpresion();

    String generateMatchingString(int max);

    boolean hasQuantifier(String exp);

}
