package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * This interface will represent all geometries
 */
public interface Geometry {
    /**
     * a method that calculates the normal of the geometry at the point
     *
     * @param point the point that the normal is going throw
     * @return the normal-vector
     */
    public Vector getNormal(Point point);
}
