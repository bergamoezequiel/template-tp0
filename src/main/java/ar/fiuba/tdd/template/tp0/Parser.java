package ar.fiuba.tdd.template.tp0;



import java.util.LinkedList;


/**
 * Created by cbergamo on 17/03/2016.
 */
public class Parser {
    private LinkedList<RegularExpresion> listaADevolver;

    private String sub = "";
    RegularExpresion regAnt = null;
    RegularExpresion regActual;

    public  LinkedList<RegularExpresion> parsear(String reg) {
        listaADevolver =  new LinkedList<>();
        int iter = 1;
        String anterior;
        while ( iter <= reg.length() ) {
            anterior = sub ;
            sub = reg.substring(0, iter);
            regActual = getRegularExpresion(sub);
            if (regActual == null && regAnt != null) {
                regAnt.setExpresion(anterior);
                listaADevolver.addLast(regAnt);
                reg = reg.substring(iter - 1);
                iter = 1;
                regAnt = null;
            }
            else {
                iter++;
                regAnt = regActual;
            }
        }

        revisarUltimoPedazo(sub,regAnt);

        return listaADevolver;

    }

    private void revisarUltimoPedazo(String sub,RegularExpresion regAnt) {
        RegularExpresion regActual = getRegularExpresion(sub);

        if (regActual != null) {
            if (regAnt != null) {
                regAnt.setExpresion(sub);
            }
            listaADevolver.addLast(regAnt);
            System.out.println("falto la ultima:  " + sub);
        }
        else {
            if (!sub.equals("")) {
                System.out.println("errorrrrrrrr " + sub);
            }

        }

    }

    private LinkedList<RegularExpresion> generarRegExList() {
        LinkedList<RegularExpresion> lista = new LinkedList<>();
        lista.addLast(new LiteralRegExp());
        lista.addLast(new SetRegExp());
        lista.addLast(new PointRegExp());
        return lista;
    }

    public RegularExpresion getRegularExpresion(String regex) {
        LinkedList<RegularExpresion> listaRegEx = generarRegExList();
        for (RegularExpresion atomicRegEx : listaRegEx) {
            if (atomicRegEx.isSubRegEx(regex)) {
                return atomicRegEx;
            }
        }
        return null;
    }
}
