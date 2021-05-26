package org.symbolmath.simplifier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimplifierTest {

  @Test
  public void testAddingNumbers() {
    simplify("1 + 1", "2");
  }

  public void testAddingSignedNumbers() {
    simplify("(-1) - (-(2-1))", "0");
  }

  public void testMultiplyingNumbers() {
    simplify("3 * 4", "12");
  }

  public void testMultiplyingSignedNumbers() {
    simplify("3 * (-4)", "-12");
  }

  public void testDividingNumbers() {
    simplify("4 / 2", "2");
  }

  public void testDividingSignedNumbers1() {
    simplify("4 / (-2)", "-2");
  }

  public void testDividingSignedNumbers2() {
    simplify("-4 / -2", "2");
  }

  private void simplify(String expression, String result) {
    assertEquals(result, new Simplifier(expression).simplify().toString());
  }

}
