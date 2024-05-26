
package geometries;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;
/**
 * This class represents a cylinder
 */
public  class Cylinder extends Tube
{
    /**the height of the cylinder*/
    private final double height;

    /**
     * Constructor to initialize the cylinder
     * @param height the height of the cylinder
     * @param radius the radius of the cylinder
     * @param axis the main axis of the cylinder
     */
    public Cylinder(double height, double radius, Ray axis ) {
        super(axis,radius);
        this.height = height;
    }


    @Override
    public Vector getNormal(Point point) {
        if(axis.getHead()==point||axis.getHead().add(axis.getDirection().scale(height))==point)
            throw new IllegalArgumentException("Error: the point is on one of the bases of the cylinder");
        double t=axis.getDirection().dotProduct(point.subtract(axis.getHead()));
        Point o=axis.getHead().add(axis.getDirection().scale(t));
        return point.subtract(o).normalize();
//        return point.subtract(
//                        axis.getHead().add(
//                                axis.getDirection().scale(
//                                        axis.getDirection().dotProduct(
//                                                point.subtract(axis.getHead()))))).
//                normalize();
    }
}