package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.List;

/**
 * This class represents a sphere
 */
public class Sphere extends RadialGeometry {
    /**
     * a point for the center of the sphere
     */
    private final Point center;

    /**
     * Constructor to initialize the sphere
     *
     * @param center the center of the sphere
     * @param radius the radius of the sphere
     */
    public Sphere(Point center, double radius) {
        super(radius);
        this.center = center;
    }

    /**
     * calculates the sphere;s normal in point p
     *
     * @param p the point that the normal is going throw
     * @return the sphere;s normal in point p
     */
    @Override
    public Vector getNormal(Point p) {
        return p.subtract(center).normalize();
    }
 @Override
   public List<Point> findIntersections(Ray ray) {
      return null;
   }
}
