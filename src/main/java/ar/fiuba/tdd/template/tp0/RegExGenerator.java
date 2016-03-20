package ar.fiuba.tdd.template.tp0;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RegExGenerator {
    // TODO: Uncomment this field
    private int maxLength;
    private LinkedList<RegularExpresion> listaDeRegex;

    public RegExGenerator(int maxLength) {
        this.maxLength = maxLength;
    }

    // TODO: Uncomment parameters
    public List<String> generate(String regEx, int numberOfResults) {


        Parser parser = new Parser();
        listaDeRegex = parser.parsear(regEx);
        int minimaCantidad = obtenerMinimaCantidad(listaDeRegex);
        int cantidadDeComodines = obtenerCantidadDeComodines(listaDeRegex);

        if (minimaCantidad > maxLength) {
            System.out.println("ERRORRRRR MAX ES MENOR Q LA MINiMA CANTIDAD");
        }
        int disponible = maxLength - minimaCantidad;
        int forDistribute = 0;
        if (cantidadDeComodines != 0) {
            forDistribute = disponible / cantidadDeComodines;
        }
        ArrayList<String> returnList = generarLista(numberOfResults,forDistribute);
        System.out.println("Se devolvieron " + returnList.size() + " elementos" );
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
        int cantidadDeComodines = 0;
        for (RegularExpresion reg : listaDeRegex) {
            if (reg.tieneComodin(reg.getExpresion())) {
                cantidadDeComodines = cantidadDeComodines + 1;
            }
        }
        return cantidadDeComodines;
    }

    private ArrayList<String> generarLista(int numberOfResults,int forDistribute) {
        ArrayList<String> returnList = new ArrayList<>();
        for (int i = 1;i <= numberOfResults;i++) {
            System.out.println("ehjjwghw4th4t74t7t5t753hg5gu5hgughrwufh438ughugreu");
            String cadena = "";
            String aleat;
            int assignedMax = 0;
            for (RegularExpresion reg : listaDeRegex) {
                System.out.println("SE ENTRO A PROCESAR UNA EXPRESION");
                assignedMax = assignedMax + reg.minimaExpresion();
                if ( reg.tieneComodin(reg.getExpresion()) ) {
                    assignedMax = assignedMax + forDistribute;
                }
                aleat = reg.generateMatchingString(assignedMax);
                cadena = cadena.concat(aleat);
                System.out.println("LA CADENA GENERADA CON " + reg.getExpresion() + "fue " + aleat);
            }
            System.out.println("LA CADEnA A DEVOLVER ES: " + cadena);
            returnList.add(cadena);
        }
        return returnList;
    }
}