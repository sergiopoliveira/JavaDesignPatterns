package visitor;

interface ExpressionVisitor {

    void visit(DoubleExpressionDD e);

    void visit(AdditionExpressionDD e);
}

abstract class ExpressionDD {
    public abstract void accept(ExpressionVisitor visitor);
}

class DoubleExpressionDD extends ExpressionDD {
    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }

    public double value;

    public DoubleExpressionDD(double value) {
        this.value = value;
    }
}

class AdditionExpressionDD extends ExpressionDD {

    public ExpressionDD left, right;

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }


    public AdditionExpressionDD(ExpressionDD left, ExpressionDD right) {
        this.left = left;
        this.right = right;
    }
}

class ExpressionPrinterDD implements ExpressionVisitor {

    private StringBuilder sb = new StringBuilder();

    @Override
    public void visit(DoubleExpressionDD e) {
        sb.append(e.value);
    }

    @Override
    public void visit(AdditionExpressionDD e) {
        sb.append("(");
        e.left.accept(this);
        sb.append("+");
        e.right.accept(this);
        sb.append(")");
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}

class ExpressionCalculator implements ExpressionVisitor {

    public double result;

    @Override
    public void visit(DoubleExpressionDD e) {
        result = e.value;
    }

    @Override
    public void visit(AdditionExpressionDD e) {
        e.left.accept(this);
        double a = result;
        e.right.accept(this);
        double b = result;
        result = a + b;
    }
}

public class DoubleDispatcher {

    public static void main(String[] args) {
        // 1 + (2+3)
        AdditionExpressionDD e = new AdditionExpressionDD(
                new DoubleExpressionDD(1),
                new AdditionExpressionDD(
                        new DoubleExpressionDD(2),
                        new DoubleExpressionDD(3)
                )
        );
        ExpressionPrinterDD ep = new ExpressionPrinterDD();
        ep.visit(e);
        System.out.println(ep);

        ExpressionCalculator ec = new ExpressionCalculator();
        ec.visit(e);
        System.out.println(ep + " = " + ec.result);
    }
}
