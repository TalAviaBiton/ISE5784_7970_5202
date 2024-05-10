package geometries;

import primitives.Point;
import primitives.Vector;
/**
 * This class represents a sphere
 */
public class Sphere extends RadialGeometry {
    /** a point for the center of the sphere */
    private final Point center;

    /**
     * Constructor to initialize the sphere
     * @param center the center of the sphere
     * @param radius the radius of the sphere
     */
    public Sphere(Point center,double radius) {
        super(radius);
        this.center = center;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }

}
