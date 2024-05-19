package primitives;
/**
 * This class will serve most primitive classes by representing a vector
 */
public class Vector extends Point {
    /**
     * Constructor to initialize vector based object with its three number values
     * @param x first number value
     * @param y second number value
     * @param z third number value
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        //Vector temp = new Vector(Double3.ZERO);
        if(this.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector can't be zero");
    }

    /**
     * Constructor to initialize vector based object with a point
     * @param double3 the point for the initialization
     */
    public Vector(Double3 double3) {
        super(double3);
        if(this.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector can't be zero");
    }

    /**
     * find the length Squared of ש vector
     * @return     result the calculation of the length Squared
     */
    public double lengthSquared() {
        return (int) (xyz.d1*xyz.d1+xyz.d2*xyz.d2+xyz.d3*xyz.d3);

    }

    /**
     * find the length of the vector
     * @return     result the calculation of the length
     */
    public double length() {
        return Math.sqrt(lengthSquared());

    }

    /**
     * calculate the dot product of the two vectors
     *@param v3 the second vector
     * @return     result the calculation of the dot product-int
     */
    public int dotProduct(Vector v3) {
        return (int) (xyz.d1*v3.xyz.d1+xyz.d2*v3.xyz.d2+xyz.d3*v3.xyz.d3);
    }

    /**
     * calculate the cross product of the two vectors
     *@param v2 the second vector
     * @return     A new vector perpendicular to the two existing vectors
     */
    public Vector crossProduct(Vector v2) {
        return new Vector(xyz.d2*v2.xyz.d3-xyz.d3*v2.xyz.d2,xyz.d3*v2.xyz.d1-xyz.d1*v2.xyz.d3,xyz.d1*v2.xyz.d2-xyz.d2*v2.xyz.d1);
    }

    /**
     * calculate the normalization of the vector
     * @return     the vector after normalization
     */
    public Vector normalize() {
        return new Vector(this.scale((1/this.length())).xyz);
    }

    /**
     * calculate the vector multiply the number
     *@param d the scalar
     * @return     the vector multiply d
     */
    public Vector scale(double d) {
        return new Vector(xyz.scale(d));
    }

    /**
     * adds two vectors
     *@param v2 the second vector
     * @return     the vectors sum
     */
    public Vector add(Vector v2) {
        if(this.equals(v2.scale(-1)))
            throw new IllegalArgumentException("You cant do vector+-itself");
        return new Vector(xyz.add(v2.xyz));
    }

     @Override
    public boolean equals(Object obj) {
        if ((this == obj)) return true;
        return ((obj instanceof Vector other)
                && super.equals(other));
    }

    @Override
    public String toString() {
        return "Vector{" +
                "xyz=" + xyz +
                "} " + super.toString();
    }
}