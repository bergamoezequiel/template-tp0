package ar.fiuba.tdd.template.tp0;

import java.util.Random;


public abstract class AbstractRegularExpresion implements RegularExpresion {
    protected String regularE = null;

    public void setExpresion(String exp) {
        regularE = exp;
    }

    public String getExpresion() {
        return regularE;
    }

    protected String obtainLiteralxTimes(int minimum,int maximum,String literal) {
        Random rand = new Random();
        int quantity = rand.nextInt((maximum - minimum) + 1) + minimum;
        String returnRe = "";
        for (int i = 1;i <= quantity;i++) {
            returnRe = returnRe.concat(literal);
        }
        return returnRe;

    }

    protected int determineMinimum() {
        int minimum = 0;
        if (!hasQuantifier(regularE)) {
            minimum = 1;
        } else {
            int minimumLengthQuantifier = Quantifier.minlenght(regularE.substring(regularE.length() - 1, regularE.length()));
            if (minimum < minimumLengthQuantifier ) {
                minimum = minimumLengthQuantifier;
            }
        }
        return minimum;
    }

    protected int determineMaximum(int maxi) {
        int maximum = maxi;
        if (!hasQuantifier(regularE)) {
            maximum = 1;
        } else {
            int maximumLengthQuantifier = Quantifier.maxlenght(regularE.substring( regularE.length() - 1, regularE.length()));
            if (maximum > maximumLengthQuantifier ) {
                maximum = maximumLengthQuantifier;
            }

        }
        return maximum;
    }

    public int minimumExpresion() {
        String quantifier = regularE.substring(this.regularE.length() - 1, this.regularE.length());
        if (Quantifier.isQuantifier(quantifier)) {
            return Quantifier.minimumOccurrences(quantifier);
        } else {
            return 1;
        }
    }

    public abstract boolean hasQuantifier(String regularE);

}
