package primitives;

/**
 * This class will serve all primitive classes by representing a point
 */
public class Point {

    // a static zero point for calculations
    public static final Point ZERO = new Point(Double3.ZERO);


    //parameter to represent the point
    final Double3 xyz;

    /**
     * Constructor to initialize Double3 based object with its three number values
     *
     * @param x first number value
     * @param y second number value
     * @param z third number value
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * Constructor to initialize point based object with its Double3
     *
     * @param xyz for the three values of the point
     */
    public Point(Double3 xyz) {

        this.xyz = xyz;
    }


    /**
     * Subtract two points
     *
     * @param p right hand side operand for subtraction
     * @return result of subtraction
     */
    public Vector subtract(Point p) {

        return new Vector(xyz.subtract(p.xyz));
    }

    /**
     * Add a vector to the point
     *
     * @param v right hand side operand for addition
     * @return result of add a point
     */
    public Point add(Vector v) {

        return new Point(xyz.add(v.xyz));
    }

    /**
     * calculate the distance squared between two points
     *
     * @param p the point I want to find the distance squared from
     * @return result the calculation of the distance squared
     */
    public double distanceSquared(Point p) {
        return
                (this.xyz.d1 - p.xyz.d1) * (this.xyz.d1 - p.xyz.d1) +
                        (this.xyz.d2 - p.xyz.d2) * (this.xyz.d2 - p.xyz.d2) +
                        (this.xyz.d3 - p.xyz.d3) * (this.xyz.d3 - p.xyz.d3);
    }

    /**
     * calculate the distance between two points
     *
     * @param p the point I want to find the distance from
     * @return result the calculation of the distance
     */
    public double distance(Point p) {

        return Math.sqrt(distanceSquared(p));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Point other)
                && xyz.equals(other.xyz);

    }

    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }

    /**
     * a getter for x
     *
     * @return index x of the point
     */
    public double getX() {
        return this.xyz.d1;
    }

    /**
     * a getter for y
     *
     * @return index y of the point
     */
    public double getY() {
        return this.xyz.d2;
    }

    /**
     * a getter for z
     *
     * @return index z of the point
     */
    public double getZ() {
        return this.xyz.d3;
    }
}
