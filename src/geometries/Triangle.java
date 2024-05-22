package geometries;
/**
 * This class represents a triangle
 */
import primitives.Point;

import java.util.List;

public class Triangle extends Polygon {

    public Triangle(Point... vertices) {
        super(vertices);
        if(vertices.length!=3)
            throw new IllegalArgumentException("ERROR: in triangle there are only 3 points");
    }
}
