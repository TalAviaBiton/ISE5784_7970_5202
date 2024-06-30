package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * This class represents a cylinder
 */
public class Cylinder extends Tube {
    /**
     * the height of the cylinder
     */
    private final double height;

    /**
     * Constructor to initialize the cylinder
     *
     * @param height the height of the cylinder
     * @param radius the radius of the cylinder
     * @param axis   the main axis of the cylinder
     */
    public Cylinder(double height, double radius, Ray axis) {
        super(axis, radius);
        this.height = height;
    }

    /**
     * calculates the normal to the cylinder in point p
     *
     * @param p the point that the normal is going throw
     * @return the normal to the cylinder in point p
     */
    @Override
    public Vector getNormal(Point p) {
        if (axis.getHead() == p || axis.getHead().add(axis.getDirection().scale(height)) == p)
            throw new IllegalArgumentException("Error: the point is on one of the bases of the cylinder");
        double t = axis.getDirection().dotProduct(p.subtract(axis.getHead()));
        Point o = axis.getHead().add(axis.getDirection().scale(t));
        return p.subtract(o).normalize();
    }
}