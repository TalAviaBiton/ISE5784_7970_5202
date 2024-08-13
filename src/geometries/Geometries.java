package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * a class to represent a list of geometries
 */
public class Geometries extends Intersectable {
    List<Intersectable> geometries;

    /**
     * an empty default constructor
     * <p>
     * constructs an empty list of geometries
     */
    public Geometries() {
        //if bvh improvement is used
        if (BVH){
            //create bounding box around geometries
            createBoundingBox();
        }
        this.geometries = new LinkedList<Intersectable>();
    }

    /**
     * a constructor to the list
     *
     * @param geometries the geometries i put in the list of geometries
     */
    public Geometries(Intersectable... geometries) {
        //if bvh improvement is used
        if (BVH){
            //create bounding box around geometries
            createBoundingBox();
        }
        this.geometries = new LinkedList<Intersectable>();
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
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> intersections = null;
        for (Intersectable geometry : geometries) {
            List<GeoPoint> geometryIntersections = geometry.findGeoIntersections(ray);
            if (geometryIntersections != null && intersections == null)
                intersections = new LinkedList<>(geometryIntersections);
            else if (geometryIntersections != null)
                intersections.addAll(geometryIntersections);
        }
        return intersections;
    }

    @Override
    public void createBoundingBox() {
        if (geometries == null)
            return;

        // Initialize minimum and maximum coordinates to infinity and negative infinity respectively
        double minX = Double.POSITIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double minZ = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        double maxZ = Double.NEGATIVE_INFINITY;

        // Iterate over the geometries in the list
        for (Intersectable geo : geometries) {
            if (geo.box != null) {
                // Update minimum and maximum coordinates based on the bounding box of each geometry
                minX = Math.min(minX, geo.box._minimums.getX());
                minY = Math.min(minY, geo.box._minimums.getY());
                minZ = Math.min(minZ, geo.box._minimums.getZ());
                maxX = Math.max(maxX, geo.box._maximums.getX());
                maxY = Math.max(maxY, geo.box._maximums.getY());
                maxZ = Math.max(maxZ, geo.box._maximums.getZ());
            }
        }

        // Create a new bounding box using the minimum and maximum coordinates
        box = new BoundingBox(new Point(minX, minY, minZ), new Point(maxX, maxY, maxZ));
    }



}
