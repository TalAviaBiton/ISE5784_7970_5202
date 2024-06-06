package geometries;
import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * This abstract class is for all geometries that has radius
 */
public abstract class RadialGeometry implements Geometry {
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
   public List<Point> findIntersections(Ray ray) {
      return null;
   }
}
