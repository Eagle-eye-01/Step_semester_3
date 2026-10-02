package week_7.assigment_problems;

public class CircleShape extends Shape {

    public CircleShape(double radius) {
        super(radius);
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive");
        }
    }

    @Override
    public double calculateArea() {
        return Math.PI * width * height;
    }

    public double getRadius() {
        return width;
    }
}
