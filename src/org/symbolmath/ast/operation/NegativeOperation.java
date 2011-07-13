package org.symbolmath.ast.operation;

import org.symbolmath.ast.ASTElement;
import org.symbolmath.exception.TransformException;

import java.util.Arrays;
import java.util.List;

public class NegativeOperation extends ASTOperation {
  private ASTElement expression;

  public NegativeOperation(ASTElement expression) {
    this.expression = expression;
  }

  @Override
  public String toString() {
    return "-" + expression;
  }

  @Override
  public List<? extends ASTElement> getChildren() {
    return Arrays.asList(expression);
  }

  @Override
  public void replaceChild(ASTElement element, ASTElement newElement) {
    if (expression == element) {
      expression = newElement;
      newElement.setParent(this);
    } else {
      throw new TransformException("No such child: " + element);
    }
  }
}
