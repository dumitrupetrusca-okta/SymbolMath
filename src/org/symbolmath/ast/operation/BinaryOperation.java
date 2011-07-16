package org.symbolmath.ast.operation;

import org.symbolmath.ast.ASTElement;
import org.symbolmath.exception.TransformException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BinaryOperation extends ASTOperation {
  protected ASTElement left;
  protected ASTElement right;

  public BinaryOperation(ASTElement left, ASTElement right) {
    this.left = left;
    this.right = right;
  }

  public ASTElement getLeft() {
    return left;
  }

  public ASTElement getRight() {
    return right;
  }

  public void replaceChild(ASTElement element, ASTElement newElement) {
    if (element == left) {
      left = newElement;
      newElement.setParent(this);
    } else if (element == right) {
      right = newElement;
      newElement.setParent(this);
    } else {
      throw new TransformException("No such child: " + element);
    }
  }

  @Override
  public List<ASTElement> getChildren() {
    return Arrays.asList(left, right);
  }
}
