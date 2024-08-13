package geometries;

import primitives.*;
import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * the intersectable class for all the intersectable
 */
public abstract class Intersectable {

    // for bvh use
    public static boolean BVH = true;//
    //bounding box for intersectable
    public BoundingBox box;

    /**
     * class representing boundary box for BVH
     */
    public class BoundingBox {
        public Point _minimums;
        public Point _maximums;

        /**
         * Constructs a bounding box with the specified minimum and maximum points.
         *
         * @param minimums The minimum point of the bounding box.
         * @param maximums The maximum point of the bounding box.
         */
        public BoundingBox(Point minimums, Point maximums) {
            _minimums = minimums;
            _maximums = maximums;
        }
    }


    /**
     * return true if ray intersects object
     *
     * @param ray ray to check
     * @return whether ray intersects box
     * code taken from scratchapixel.com
     * https://www.scratchapixel.com/lessons/3d-basic-rendering
     * /introductionacceleration-structure/bounding-volume-hierarchy-BVH-part1
     */
    public boolean intersectingBoundingBox(Ray ray) {
        if (!BVH || box == null)
            return true;
        Vector dir = ray.getDirection();
        Point p0 = ray.getHead();

        // Calculate the intersection intervals on the x-axis
        double xMin = (box._minimums.getX() - p0.getX()) / dir.getX();
        double xMax = (box._maximums.getX() - p0.getX()) / dir.getX();

        // Ensure xMin is smaller than xMax
        if (xMin > xMax) {
            double temp = xMin;
            xMin = xMax;
            xMax = temp;
        }

        // Calculate the intersection intervals on the y-axis
        double yMin = (box._minimums.getY() - p0.getY()) / dir.getY();
        double yMax = (box._maximums.getY() - p0.getY()) / dir.getY();

        // Ensure yMin is smaller than yMax
        if (yMin > yMax) {
            double temp = yMin;
            yMin = yMax;
            yMax = temp;
        }

        // Check for non-overlapping intervals on the x-axis and y-axis
        if ((xMin > yMax) || (yMin > xMax))
            return false;

        // Update xMin to the maximum of yMin and xMin
        if (yMin > xMin)
            xMin = yMin;

        // Update xMax to the minimum of yMax and xMax
        if (yMax < xMax)
            xMax = yMax;

        // Calculate the intersection intervals on the z-axis
        double zMin = (box._minimums.getZ() - p0.getZ()) / dir.getZ();
        double zMax = (box._maximums.getZ() - p0.getZ()) / dir.getZ();

        // Ensure zMin is smaller than zMax
        if (zMin > zMax) {
            double temp = zMin;
            zMin = zMax;
            zMax = temp;
        }

        // Check for non-overlapping intervals on the x-axis and z-axis
        if ((xMin > zMax) || (zMin > xMax))
            return false;

        // Update xMin to the maximum of zMin and xMin
        if (zMin > xMin)
            xMin = zMin;

        // Update xMax to the minimum of zMax and xMax
        if (zMax < xMax)
            xMax = zMax;

        return true;
    }

    /**
     * create the boundary box for the objects
     */
    public abstract void createBoundingBox();


    /**
     * a class to represent a geometry point
     */
    public static class GeoPoint {
        //the geometry
        public Geometry geometry;
        //the point
        public Point point;

        /**
         * a constructor for geoPoint
         *
         * @param geometry the geometry
         * @param point    the point
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) obj;
            return Objects.equals(geometry, geoPoint.geometry) && Objects.equals(point, geoPoint.point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }

        public Geometry setBVH(boolean bvh) {
            BVH = bvh;
            return this.geometry;
        }
    }


    //*********** find intersections functions *******************

    /**
     * finds the closest intersection point to a given ray
     *
     * @param ray the given ray
     * @return the point and its geometry, null if there is no such point
     */
    public GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = findGeoIntersections(ray);
        return intersections == null ? null : ray.findClosestGeoPoint(intersections);
    }

    /**
     * the method that every interactable implements and finds the
     * intersections of the shape with the ray and returns a geoPoint
     *
     * @param ray the ray i want to find intersections with
     * @return a list of the GeoPoints that have intersection with the ray
     */
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        return null;
    }

    /**
     * a method that calls for findGeoIntersectionsHelper
     *
     * @param ray the ray i want to find intersections with
     * @return a list of the GeoPoints that have intersection with the ray
     */
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        if (BVH && !intersectingBoundingBox(ray)) {
            return null;
        }
        return findGeoIntersectionsHelper(ray);
    }

    /**
     * a method to find the intersections between the ray and the shape
     *
     * @param ray the ray i want to find intersections with
     * @return a list of the points that have intersection with the ray
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }


}