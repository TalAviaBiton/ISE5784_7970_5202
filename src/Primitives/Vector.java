package primitives;

public class Vector extends Point {
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if(this.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector can't be zero");
    }

    public Vector(Double3 double3) {
        super(double3);
    }

    public double lengthSquared() {
        return (int) (xyz.d1*xyz.d1+xyz.d2*xyz.d2+xyz.d3*xyz.d3);

    }

    public double length() {
        return Math.sqrt(lengthSquared());

    }

    public int dotProduct(Vector v3) {
        return (int) (xyz.d1*v3.xyz.d1+xyz.d2*v3.xyz.d2+xyz.d3*v3.xyz.d3);
    }

    public Vector crossProduct(Vector v2) {
        return new Vector(xyz.d2*v2.xyz.d3-xyz.d3*v2.xyz.d2,xyz.d3*v2.xyz.d1-xyz.d1*v2.xyz.d3,xyz.d1*v2.xyz.d2-xyz.d2*v2.xyz.d1);
    }

    public Vector normalize() {
        return new Vector(this.scale((1/this.length())).xyz);
    }

    public Vector scale(double d) {
        return new Vector(xyz.scale(d));
    }
    public Vector add(Vector v2) {
        return new Vector(xyz.add(v2.xyz));
    }

     @Override
 public boolean equals(Object obj) {
 if (this == obj) return true;
 return (obj instanceof Vector other);
 }

    @Override
    public String toString() {
        return "Vector{" +
                "xyz=" + xyz +
                "} " + super.toString();
    }
}
