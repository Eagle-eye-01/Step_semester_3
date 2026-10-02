package week_7.assigment_problems;

public class SquareShape extends Shape {

    public SquareShape(double side) {
        super(side);
        if (side <= 0) {
            throw new IllegalArgumentException("Side must be positive");
        }
    }

    @Override
    public double calculateArea() {
        return width * height;
    }

    public double getSide() {
        return width;
    }
}
