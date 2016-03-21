package ar.fiuba.tdd.template.tp0;


public class LiteralRegExp extends AbstractRegularExpresion  {


    public boolean isSubRegEx(String exp) {
        if (  ( exp == null ) || ( exp.length() == 0 )  ) {
            return false;
        }
        if (exp.length() == 3 && hasQuantifier(exp) && isEscaped(exp)) {
            return true;
        }

        boolean hasValidLong2 = has2ValidStructure(exp);
        return (hasValidLong2 || (exp.length() == 1 && notIsReservedCaracter(exp)));
    }

    public boolean has2ValidStructure(String exp) {
        if (exp.length() == 2) {
            if ( isEscaped(exp) ) {
                return true;
            } else {
                if ( hasQuantifier(exp) ) {
                    String firstChar = exp.substring(0,1);
                    return notIsReservedCaracter(firstChar);
                }
            }
        }
        return false;
    }

    public  boolean hasQuantifier(String exp) {
        return ( Quantifier.isQuantifier(exp.substring( exp.length() - 1,exp.length())) );
    }

    private static boolean isEscaped(String exp) {
        String scape = exp.substring(0,1);
        return (scape.equals("\\"));

    }

    private static boolean notIsReservedCaracter(String exp) {
        String specialCarater = ".$[]+-*?";
        return (!specialCarater.contains(exp));
    }



    public String generateMatchingString(int maxi) {

        int maximum = determineMaximum(maxi);
        int minimum = determineMinimum();
        String literal = obtainLiteral();

        return obtainLiteralxTimes(minimum,maximum,literal);

    }



    private String obtainLiteral() {
        String literal ;
        if (regularE.length() == 1 ) {
            literal = regularE.substring(0,1);
            return literal;
        }
        if (regularE.length() == 3 ) {
            literal = regularE.substring(1,2);
            return literal;
        }
        return obtainlong2Literal(regularE);

    }

    public String obtainlong2Literal(String regularE) {
        String literal = "";
        if ( isEscaped(regularE) ) {
            literal = regularE.substring(1,2);
        } else {
            if ( hasQuantifier(regularE) ) {
                literal = regularE.substring(0,1);

            }
        }
        return literal;
    }


}

