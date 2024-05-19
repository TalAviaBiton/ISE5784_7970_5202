package primitives;

/**
 * This class will serve all primitive classes by representing a point
 */
public class Point {

    /** a static zero point for calculations */
    public static final Point ZERO=new Point(Double3.ZERO);

    /** parameter to represent the point */
    final Double3 xyz;

    /**
     * Constructor to initialize Double3 based object with its three number values
     * @param x first number value
     * @param y second number value
     * @param z third number value
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x,y,z);
    }

    /**
     * Constructor to initialize point based object with its Double3
     * @param xyz for the three values of the point
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    public Double3 getXYZ(){return xyz;}
    /**
     * Subtract two points triads
     * @param  p1 right hand side operand for subtraction
     * @return     result of subtraction
     */
    public Vector subtract(Point p1) {
        if(p1.equals(this))
        {
            throw new IllegalArgumentException("You cant subtract the point-itself");
        }
        return new Vector(xyz.subtract(p1.xyz));
    }
    /**
     * Add a vector to the point
     * @param  v1 right hand side operand for addition
     * @return     result of add a point
     */
    public Point add(Vector v1) {
        return new Point(xyz.add(v1.xyz));
    }

    /**
     * find the distance squared between two points
     * @param  point the point I want to find the distance squared from
     * @return     result the calculation of the distance squared
     */
    public double distanceSquared(Point point) {
        return (this.xyz.d1-point.xyz.d1)*(this.xyz.d1-point.xyz.d1)+(this.xyz.d2-point.xyz.d2)*(this.xyz.d2-point.xyz.d2)+(this.xyz.d3-point.xyz.d3)*(this.xyz.d3-point.xyz.d3);
    }

    /**
     * find the distance between two points
     * @param  point the point I want to find the distance from
     * @return     result the calculation of the distance
     */
    public double distance(Point point) {
        return Math.sqrt(distanceSquared(point));
    }

    @Override
    public boolean equals(Object obj) {
         if (this == obj) return true;
     return (obj instanceof Point other)
             && super.equals(other);

    }
    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }
    public boolean isOnSameLine(Point other){
      return (xyz.d1/ other.xyz.d1==xyz.d2/other.xyz.d2&&xyz.d1/ other.xyz.d1==xyz.d3/other.xyz.d3&&xyz.d2/other.xyz.d2==xyz.d3/other.xyz.d3);
   }
}
