package ar.fiuba.tdd.template.tp0;

import java.util.Random;

/**
 * Created by cbergamo on 20/03/2016.
 */
public abstract class AbstractRegularExpresion {
    protected String regularE = null;

    public void setExpresion(String exp) {
        regularE = exp;
    }

    public String getExpresion() {
        return regularE;
    }

    protected String obtenerLiteralxCantidadDeVeces(int minimo,int maximo,String literal) {
        Random rand = new Random();
        int cantidad = rand.nextInt((maximo - minimo) + 1) + minimo;
        System.out.println(cantidad);
        String returnRe = "";
        for (int i = 1;i <= cantidad;i++) {
            System.out.println("entro");
            returnRe = returnRe.concat(literal);
        }
        return returnRe;

    }

    protected int determinarMinimo() {
        int minimo = 0;
        if (!tieneComodin(regularE)) {
            minimo = 1;
        } else {
            int longitudMinimaDeComodin = Comodin.minlenght(regularE.substring(regularE.length() - 1, regularE.length()));
            if (minimo < longitudMinimaDeComodin ) {
                minimo = longitudMinimaDeComodin;
            }
        }
        return minimo;
    }

    protected int determinarMaximo(int maxi) {
        int maximo = maxi;
        if (!tieneComodin(regularE)) {
            maximo = 1;
        }
        else {
            int longitudMaximaDeUnComodin = Comodin.maxlenght(regularE.substring( regularE.length() - 1, regularE.length()));
            if (maximo > longitudMaximaDeUnComodin ) {
                maximo = longitudMaximaDeUnComodin;
            }

        }
        return maximo;
    }

    public int minimaExpresion() {
        String comodin = regularE.substring(this.regularE.length() - 1, this.regularE.length());
        if (Comodin.esComodin(comodin)) {
            return Comodin.minimaCantidadDeApariciones(comodin);
        } else {
            return 1;
        }
    }

    public abstract boolean tieneComodin(String regularE);

}
