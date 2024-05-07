package primitives;

public class Point {
    public static final Point ZERO=new Point(Double3.ZERO);
    final Double3 xyz;
    public Point(double x, double y, double z) {
        xyz = new Double3(x,y,z);
    }

    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    public Vector subtract(Point p1) {
        return new Vector(xyz.subtract(p1.xyz));
    }

    public Point add(Vector v1) {
        return new Point(xyz.add(v1.xyz));
    }

      public double distanceSquared(Point point) {
        return (this.xyz.d1-point.xyz.d1)*(this.xyz.d1-point.xyz.d1)+(this.xyz.d2-point.xyz.d2)*(this.xyz.d2-point.xyz.d2)+(this.xyz.d3-point.xyz.d3)*(this.xyz.d3-point.xyz.d3);
    }
    public double distance(Point point) {
        return Math.sqrt(distanceSquared(point));
    }

 @Override
 public boolean equals(Object obj) {
        if (this == obj) return true;
 return (obj instanceof Point other) && this.xyz.equals(other.xyz);

 }
    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }
}
