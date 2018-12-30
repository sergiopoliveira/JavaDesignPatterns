package structural.adapter;


class Square {
    public int side;

    public Square(int side) {
        this.side = side;
    }
}

interface Rectangle {
    int getWidth();
    int getHeight();

    default int getArea() {
        return getWidth() * getHeight();
    }
}

class SquareToRectangleAdapter implements Rectangle {
    int width;
    int height;

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public SquareToRectangleAdapter(Square square) {
        this.width = square.side;
        this.height = square.side;
    }

}

public class AdapterExercise {
}
