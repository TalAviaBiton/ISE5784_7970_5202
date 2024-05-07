
package geometries;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import java.awt.*;

public  class Cylinder extends Tube
{
    private final double height;

    public Cylinder(double height, double radius, Ray axis ) {
        super(axis,radius);
        this.height = height;
    }


    public Vector getNormal(Point point) {

        return null;
    }
}