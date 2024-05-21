package geometries;
/**
 * This class represents a triangle
 */
import primitives.Point;

import java.util.List;

public class Triangle extends Polygon {

    public Triangle(Point x, Point y, Point z) {
        List<Point> ptsr = new List<Point> (x, y, z ;
        Point[] pts =
                {       new Point(0, 0, 1),
                        new Point(1, 0, 0),
                        new Point(0, 1, 0)
                };
        super(pts);
    }
}
