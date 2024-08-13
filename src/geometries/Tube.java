package geometries;
/**
 * This class represents a tube
 */

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;


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
        Point o = axis.getPoint(t);
        return p.subtract(o).normalize();
    }

    @Override
    public void createBoundingBox() {
        //we never fully implemented tube, so this is never used
    }
}
