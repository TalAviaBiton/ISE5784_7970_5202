package geometries;
/**
 * This class represents a tube
 */
import primitives.Point;
import primitives.Vector;
import primitives.Ray;
public class Tube extends RadialGeometry {
    /** the ray of the main axis*/
    private final Ray axis;

    /**
     * Constructor to initialize the tube
     * @param axis the ray of the main axis
     * @param radius the radius of the tube
     */
    public Tube(Ray axis, double radius) {
        super(radius);
        this.axis = axis;
    }

    @Override
    public Vector getNormal(Point point) {

        return null;
    }
}
