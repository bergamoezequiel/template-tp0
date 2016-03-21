package ar.fiuba.tdd.template.tp0;


import java.util.LinkedList;



public class Parser {
    private LinkedList<RegularExpresion> listToReturn;

    private String sub = "";
    RegularExpresion regAnt = null;
    RegularExpresion currentReg;

    public  LinkedList<RegularExpresion> parsear(String reg) {
        listToReturn =  new LinkedList<>();
        int iter = 1;
        String previous;
        while ( iter <= reg.length() ) {
            previous = sub ;
            sub = reg.substring(0, iter);
            currentReg = getRegularExpresion(sub);
            if (currentReg == null && regAnt != null) {
                regAnt.setExpresion(previous);
                listToReturn.addLast(regAnt);
                reg = reg.substring(iter - 1);
                iter = 1;
                regAnt = null;
            } else {
                iter++;
                regAnt = currentReg;
            }
        }

        checkLastPiece(sub,regAnt);

        return listToReturn;

    }

    private void checkLastPiece(String sub,RegularExpresion regAnt) {
        RegularExpresion currentReg = getRegularExpresion(sub);

        if (currentReg != null) {
            if (regAnt != null) {
                regAnt.setExpresion(sub);
            }
            listToReturn.addLast(regAnt);

        } else {
            if (!sub.equals("")) {
                throw new InvalidRegexException() ;
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
        LinkedList<RegularExpresion> regExlist = generarRegExList();
        for (RegularExpresion atomicRegEx : regExlist) {
            if (atomicRegEx.isSubRegEx(regex)) {
                return atomicRegEx;
            }
        }
        return null;
    }
}
