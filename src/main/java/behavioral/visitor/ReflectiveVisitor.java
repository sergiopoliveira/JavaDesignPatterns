package behavioral.visitor;

abstract class ExpressionR {
}

class DoubleExpressionR extends ExpressionR {

    public double value;

    public DoubleExpressionR(double value) {
        this.value = value;
    }
}

class AdditionExpressionR extends ExpressionR {

    public ExpressionR left, right;

    public AdditionExpressionR(ExpressionR left, ExpressionR right) {
        this.left = left;
        this.right = right;
    }
}

class ExpressionPrinter {
    public static void print(ExpressionR e, StringBuilder sb) {
        if (e.getClass() == DoubleExpressionR.class) {
            sb.append(((DoubleExpressionR) e).value);
        } else if (e.getClass() == AdditionExpressionR.class) {
            AdditionExpressionR ae = (AdditionExpressionR) e;
            sb.append("(");
            print(ae.left, sb);
            sb.append("+");
            print(ae.right, sb);
            sb.append(")");
        }
    }
}

public class ReflectiveVisitor {

    public static void main(String[] args) {
        // 1 + (2+3)
        AdditionExpressionR e = new AdditionExpressionR(
                new DoubleExpressionR(1),
                new AdditionExpressionR(
                        new DoubleExpressionR(2),
                        new DoubleExpressionR(3)
                )
        );
        StringBuilder sb = new StringBuilder();
        ExpressionPrinter.print(e, sb);
        System.out.println(sb);
    }
}
