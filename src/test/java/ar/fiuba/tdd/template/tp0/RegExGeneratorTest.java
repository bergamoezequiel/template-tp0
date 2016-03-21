package ar.fiuba.tdd.template.tp0;

import ar.fiuba.tdd.template.tp0.exceptions.InvalidMaxLengthStringException;
import ar.fiuba.tdd.template.tp0.exceptions.InvalidRegexException;
import org.junit.Test;

import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RegExGeneratorTest {

    private boolean validate(String regEx, int numberOfResults) {
        RegExGenerator generator = new RegExGenerator(15);
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



    @Test(expected = InvalidMaxLengthStringException.class)
    public void testInvalidMaxLengthRegex() {
        RegExGenerator generator = new RegExGenerator(3);
        generator.generate("[ac]+.9\\\\", 10);
    }

    @Test(expected = InvalidRegexException.class)
    public void testInvalidRegEx() {
        assertTrue(validate("[abc[z]", 100));
    }

    @Test(expected = InvalidRegexException.class)
    public void testIncompleteRegex() {
        assertTrue(validate("[abc", 100));
    }

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
        assertFalse(setRegExp.isSubRegEx("["));
        assertFalse(setRegExp.isSubRegEx("[ab"));
        assertFalse(setRegExp.isSubRegEx("[abs[hf]"));
        assertFalse(setRegExp.isSubRegEx("[ac\\]"));
        assertTrue(setRegExp.isSubRegEx("[abc]"));
        assertTrue(setRegExp.isSubRegEx("[\\[abc]"));
        assertTrue(setRegExp.isSubRegEx("[ab\\]c]"));
        assertTrue(setRegExp.isSubRegEx("[abc]?"));
        assertTrue(setRegExp.isSubRegEx("[ab\\]c]*"));
        assertTrue(setRegExp.isSubRegEx("[\\[abc]+"));
        assertTrue(setRegExp.isSubRegEx("[ac\\\\]"));

    }

    @Test
    public void testSubPoint() {

        PointRegExp pointRegExp = new PointRegExp();
        pointRegExp.setExpresion(".*");
        assertFalse(pointRegExp.isSubRegEx(""));
        assertFalse(pointRegExp.isSubRegEx("["));
        assertFalse(pointRegExp.isSubRegEx(".3"));
        assertTrue(pointRegExp.isSubRegEx("."));
        assertTrue(pointRegExp.isSubRegEx(".?"));
        assertTrue(pointRegExp.isSubRegEx(".*"));
        assertTrue(pointRegExp.isSubRegEx(".+"));

    }

    @Test
    public void testMinimumExpresions() {
        LiteralRegExp literalRegExp = new LiteralRegExp();
        literalRegExp.setExpresion("\\[?");
        assertTrue(literalRegExp.minimumExpresion() == 0);
        literalRegExp.setExpresion("a");
        assertTrue(literalRegExp.minimumExpresion() == 1);
        literalRegExp.setExpresion("a*");
        assertTrue(literalRegExp.minimumExpresion() == 0);
        literalRegExp.setExpresion("a?");
        assertTrue(literalRegExp.minimumExpresion() == 0);


    }


    @Test
    public void testLiteralRegExp() {
        System.out.println(Quantifier.minimumOccurrences("?"));
        LiteralRegExp literalRegExp = new LiteralRegExp();
        literalRegExp.setExpresion("a+");
        assertFalse(literalRegExp.isSubRegEx("[?"));
        assertFalse(literalRegExp.isSubRegEx(""));
        assertFalse(literalRegExp.isSubRegEx("d?c"));
        assertTrue(literalRegExp.isSubRegEx("a"));
        assertTrue(literalRegExp.isSubRegEx("c"));
        assertTrue(literalRegExp.isSubRegEx("a*"));
        assertTrue(literalRegExp.isSubRegEx("a?"));
        assertTrue(literalRegExp.isSubRegEx("a+"));
        assertTrue(literalRegExp.isSubRegEx("\\?"));
        assertTrue(literalRegExp.isSubRegEx("\\?*"));


    }

    @Test
    public void testLiteral() {
        assertTrue(validate("\\@", 20));
    }


    @Test
    public void testDot() {
        assertTrue(validate(".", 20));
        assertTrue(validate(".+", 20));
        assertTrue(validate(".?", 20));
        assertTrue(validate(".*", 20));
    }

    @Test
    public void testBasicLiteral() {
        assertTrue(validate("7", 20));
        assertTrue(validate("\\%", 20));
        assertTrue(validate("\\%?", 20));
        assertTrue(validate("\\%*", 20));
        assertTrue(validate("\\%+", 20));
        assertTrue(validate("t*", 20));
        assertTrue(validate("t?", 20));

    }


    @Test
    public void testLiteralDotCharacter() {
        assertTrue(validate("\\@..", 20));
    }

    @Test
    public void testZeroOrOneCharacter() {
        assertTrue(validate("\\@.h?", 20));
    }

    @Test
    public void testCharacterSet() {
        assertTrue(validate("[abc]", 20));
    }

    @Test
    public void testCharacterSetWithQuantifiers() {
        assertTrue(validate("[abc]+", 20));
        assertTrue(validate("[abc]*", 20));
        assertTrue(validate("[abc]?", 20));

    }

    @Test
    public void testComplexRegex() {
        assertTrue(validate(".?.+[ab]*d?c", 50));
    }

    @Test
    public void testComplexRegex2() {
        assertTrue(validate("..+[a\\[]+d?c[df+v]+.\\.", 50));
    }

    @Test
    public void testComplexRegex3() {
        assertTrue(validate("\\\\[\\[\\]]+", 50));
    }

}
