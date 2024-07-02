package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {
    List<Intersectable> geometries = new LinkedList<Intersectable>();

    /**
     * an empty default constructor
     * <p>
     * constructs an empty list of geometries
     */
    public Geometries() {

    }

    /**
     * a constractor to the list
     *
     * @param geometries the geometries i put in the list of geometries
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * adds intersectable to the geometries list
     *
     * @param geometries the geometries i add to the list of geometries
     */
    public void add(Intersectable... geometries) {
        Collections.addAll(this.geometries, geometries);
    }


    /**
     * finds all the intersections of a ray with all the geometries in the list
     *
     * @param ray the ray that we want to check intersections with
     * @return a list of the intersections of ray and the geometries from the list
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> intersections = null;
        for (Intersectable geometry : geometries) {
            List<Point> geometryIntersections = geometry.findIntersections(ray);
            if (geometryIntersections != null && intersections == null)
                intersections = new LinkedList<>(geometryIntersections);
            else if (geometryIntersections != null)
                intersections.addAll(geometryIntersections);
        }
        return intersections;
    }

}
