package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * This abstract class is for all geometries that has radius
 */
public abstract class RadialGeometry extends Geometry {
    /**
     * the radius of the geometry
     */
    protected final double radius;

    /**
     * sets the radius
     *
     * @param radius the wanted radius
     */
    protected RadialGeometry(double radius) {
        this.radius = radius;
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        return null;
    }
}
