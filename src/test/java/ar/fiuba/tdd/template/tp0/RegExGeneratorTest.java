package ar.fiuba.tdd.template.tp0;

import org.junit.Test;

import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RegExGeneratorTest {

    private boolean validate(String regEx, int numberOfResults) {
        RegExGenerator generator = new RegExGenerator(15);
        // TODO: Uncomment parameters
        List<String> results = generator.generate(regEx, numberOfResults);
        // force matching the beginning and the end of the strings
        Pattern pattern = Pattern.compile("^" + regEx + "$");
        return results
                .stream()
                .reduce(true,
                    (acc, item) -> {
                        Matcher matcher = pattern.matcher(item);
                        return acc && matcher.find();
                    },
                    (item1, item2) -> item1 && item2);
    }

    //TODO: Uncomment these tests

    @Test
    public void testAnyCharacter() {
        assertTrue(validate(".", 100));
    }

    @Test
    public void testMultipleCharacters() {
        assertTrue(validate("...", 1));
    }

    @Test
    public void testSubSets() {
        SetRegExp setRegExp = new SetRegExp();
        setRegExp.setExpresion("[\\\\asbiop]");
       // System.out.println("finalmente"+setRegExp.generateMatchingString());
        assertFalse(setRegExp.isSubRegEx("["));
        assertFalse(setRegExp.isSubRegEx("[ab"));
        assertFalse(setRegExp.isSubRegEx("[abs[hf]"));
        assertTrue(setRegExp.isSubRegEx("[abc]"));
        assertTrue(setRegExp.isSubRegEx("[\\[abc]"));
        assertTrue(setRegExp.isSubRegEx("[ab\\]c]"));
        assertFalse(setRegExp.isSubRegEx("[ac\\]"));
        assertTrue(setRegExp.isSubRegEx("[abc]?"));
        assertTrue(setRegExp.isSubRegEx("[ab\\]c]*"));
        assertTrue(setRegExp.isSubRegEx("[\\[abc]+"));
        assertTrue(setRegExp.isSubRegEx("[ac\\\\]"));

    }

    @Test
    public void testSubPoint() {

        PointRegExp pointRegExp = new PointRegExp();
        pointRegExp.setExpresion(".*");
        //System.out.println(pointRegExp.generateMatchingString());
        assertFalse(pointRegExp.isSubRegEx(""));
        assertFalse(pointRegExp.isSubRegEx("["));
        assertTrue(pointRegExp.isSubRegEx("."));
        assertTrue(pointRegExp.isSubRegEx(".?"));
        assertTrue(pointRegExp.isSubRegEx(".*"));
        assertTrue(pointRegExp.isSubRegEx(".+"));
        assertFalse(pointRegExp.isSubRegEx(".3"));
    }

    @Test
    public void testMinimumExpresions() {
        LiteralRegExp literalRegExp = new LiteralRegExp();
        literalRegExp.setExpresion("\\[?");
        assertTrue(literalRegExp.minimaExpresion() == 0);
        literalRegExp.setExpresion("a");
        assertTrue(literalRegExp.minimaExpresion() == 1);
        literalRegExp.setExpresion("a*");
        assertTrue(literalRegExp.minimaExpresion() == 0);
        literalRegExp.setExpresion("a?");
        assertTrue(literalRegExp.minimaExpresion() == 0);




    }


    @Test
    public void testLiteralRegExp() {
        System.out.println(Comodin.minimaCantidadDeApariciones("?"));
        LiteralRegExp literalRegExp = new LiteralRegExp();
        literalRegExp.setExpresion("a+");
        System.out.println("la cantidad minima es : " + literalRegExp.minimaExpresion());
        assertFalse(literalRegExp.isSubRegEx(""));
        assertTrue(literalRegExp.isSubRegEx("a"));
        assertTrue(literalRegExp.isSubRegEx("c"));
        assertTrue(literalRegExp.isSubRegEx("a*"));
        assertTrue(literalRegExp.isSubRegEx("a?"));
        assertTrue(literalRegExp.isSubRegEx("a+"));
        assertTrue(literalRegExp.isSubRegEx("\\?"));
        assertFalse(literalRegExp.isSubRegEx("[?"));
        assertTrue(literalRegExp.isSubRegEx("\\?*"));
        assertFalse(literalRegExp.isSubRegEx("d?c"));

    }

    @Test
    public void testLiteral() {
      //esta expresion regular es \@
        assertTrue(validate("\\@", 100));
    }


    @Test
    public void testDot() {
        assertTrue(validate(".", 200));
        assertTrue(validate(".+", 150));
        assertTrue(validate(".?", 100));
        assertTrue(validate(".*", 100));
    }

    @Test
    public void testBasicLiteral() {
        assertTrue(validate("7", 200));
        assertTrue(validate("\\%", 150));
        assertTrue(validate("\\%?", 150));
        assertTrue(validate("\\%*", 150));
        assertTrue(validate("\\%+", 150));
        assertTrue(validate("t*", 150));
        assertTrue(validate("t?", 150));

    }


    @Test
    public void testLiteralDotCharacter() {
        assertTrue(validate("\\@..", 100));
    }

    @Test
    public void testZeroOrOneCharacter() {
        assertTrue(validate("\\@.h?", 50));
    }

    @Test
    public void testCharacterSet() {
        assertTrue(validate("[abc]", 100));
    }

    @Test
    public void testCharacterSetWithQuantifiers() {
        assertTrue(validate("[abc]+", 100));
        assertTrue(validate("[abc]*", 100));
        assertTrue(validate("[abc]?", 100));

    }

    @Test
    public void testComplexRegex() {
        assertTrue(validate(".?.+[ab]*d?c", 50));
    }

    @Test
    public void testComplexRegex2() {
        assertTrue(validate("..+[a\\[]+d?c", 50));
    }

    @Test
    public void testComplexRegex3() {
        assertTrue(validate("\\\\[\\[\\]]+", 50));
    }

}
