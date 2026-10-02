package week_7.assigment_problems;

public abstract class Shape {
    private static int counter = 1000;
    private final String shapeId;
    protected double width;
    protected double height;

    public Shape(double width, double height) {
        this.shapeId = "SHAPE-" + (++counter);
        this.width = width;
        this.height = height;
    }

    public Shape(double dimension) {
        this(dimension, dimension);
    }

    public abstract double calculateArea();

    public void scale(double factor) {
        scale(factor, factor);
    }

    public void scale(double xFactor, double yFactor) {
        this.width *= xFactor;
        this.height *= yFactor;
    }

    public String getShapeId() {
        return shapeId;
    }

    public static void printArea(Shape s) {
        System.out.println(s.calculateArea());
    }
}
