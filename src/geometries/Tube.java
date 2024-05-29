package geometries;
/**
 * This class represents a tube
 */

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

public class Tube extends RadialGeometry {
    /**
     * the ray of the main axis
     */
    protected final Ray axis;

    /**
     * Constructor to initialize the tube
     *
     * @param axis   the ray of the main axis
     * @param radius the radius of the tube
     */
    public Tube(Ray axis, double radius) {
        super(radius);
        this.axis = axis;
    }


    @Override
    public Vector getNormal(Point p) {
        if (p.subtract(axis.getHead()).dotProduct(axis.getDirection()) == 0)
            throw new IllegalArgumentException("ERROR: the two points are vertical to the direction vector of the tube");
        double t = axis.getDirection().dotProduct(p.subtract(axis.getHead()));
        Point o = axis.getHead().add(axis.getDirection().scale(t));
        return p.subtract(o).normalize();
//        return point.subtract(
//                axis.getHead().add(
//                axis.getDirection().scale(
//                axis.getDirection().dotProduct(
//                point.subtract(axis.getHead()))))).
//                normalize();

    }
}
