package ar.fiuba.tdd.template.tp0;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RegExGenerator {
    private int maxLength;

    private LinkedList<RegularExpresion> regexList;

    public RegExGenerator(int maxLength) {
        this.maxLength = maxLength;
    }

    public List<String> generate(String regEx, int numberOfResults) {


        Parser parser = new Parser();
        regexList = parser.parsear(regEx);
        int minimaCantidad = obtenerMinimaCantidad(regexList);
        int numberOfQualifiers = obtenerCantidadDeComodines(regexList);

        if (minimaCantidad > maxLength) {
            System.out.println("ERRORRRRR MAX ES MENOR Q LA MINiMA CANTIDAD");
        }
        int available = maxLength - minimaCantidad;
        int forDistribute = 0;
        if (numberOfQualifiers != 0) {
            forDistribute = available / numberOfQualifiers;
        }
        ArrayList<String> returnList = generarLista(numberOfResults,forDistribute);

        return returnList;

    }

    private int obtenerMinimaCantidad(LinkedList<RegularExpresion> listaDeRegex) {
        int minimaCantidad = 0;
        for (RegularExpresion reg : listaDeRegex) {
            minimaCantidad = minimaCantidad + reg.minimaExpresion();
        }
        return minimaCantidad;
    }

    private int obtenerCantidadDeComodines(LinkedList<RegularExpresion> listaDeRegex) {
        int numberOfQualifiers = 0;
        for (RegularExpresion reg : listaDeRegex) {
            if (reg.hasComodin(reg.getExpresion())) {
                numberOfQualifiers = numberOfQualifiers + 1;
            }
        }
        return numberOfQualifiers;
    }

    private ArrayList<String> generarLista(int numberOfResults,int forDistribute) {
        ArrayList<String> returnList = new ArrayList<>();
        for (int i = 1;i <= numberOfResults;i++) {
            String cadena = "";
            String aleat;
            int assignedMax = 0;
            for (RegularExpresion reg : regexList) {
                assignedMax = assignedMax + reg.minimaExpresion();
                if ( reg.hasComodin(reg.getExpresion()) ) {
                    assignedMax = assignedMax + forDistribute;
                }
                aleat = reg.generateMatchingString(assignedMax);
                cadena = cadena.concat(aleat);
            }
            returnList.add(cadena);
        }
        return returnList;
    }
}