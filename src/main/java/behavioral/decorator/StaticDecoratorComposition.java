import java.util.function.Supplier;

interface Shape {
    String info();
}

class Circle implements Shape {

    private float radius;

    public Circle(float radius) {
        this.radius = radius;
    }

    public Circle() {
    }

    void resize(float factor) {
        radius *= factor;
    }

    @Override
    public String info() {
        return "A circle of radius " + radius;
    }
}

class Square implements Shape {
    private float side;

    private Square() {
    }

    public Square(float side) {
        this.side = side;
    }

    @Override
    public String info() {
        return null;
    }
}

class ColoredShape<T extends Shape> implements Shape {
    private Shape shape;
    private String color;


    public ColoredShape(Supplier<? extends T> ctor,
                        String color) {
        this.color = color;
        shape = ctor.get();
    }

    @Override
    public String info() {
        return shape.info() + " has the color " + color;
    }
}

class TransparentShape<T extends Shape> implements Shape {
    private Shape shape;
    private int transparency;

    public TransparentShape(Supplier<? extends T> ctor,
                            int transparency) {
        this.transparency = transparency;
        shape = ctor.get();
    }

    @Override
    public String info() {
        return shape.info() + " has transparency " + transparency;
    }
}

public class StaticDecoratorComposition {

    public static void main(String[] args) {
        ColoredShape<Square> blueSquare =
                new ColoredShape<>(
                        () -> new Square(20),
                        "blue"
                );
        System.out.println(blueSquare.info());

        TransparentShape<ColoredShape<Circle>> myCircle =
                new TransparentShape<>(() ->
                        new ColoredShape<>(() -> new Circle(5), "green"),50);
        System.out.println(myCircle.info());
    }
}
