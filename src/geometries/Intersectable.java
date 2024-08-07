package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * the intersectable class for all the intersectable
 */
public abstract class Intersectable {

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
         * @param geometry the geometry
         * @param point the point
         */
        public GeoPoint(Geometry geometry, Point point)
        {
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
    }

    /** the method that every interactable implements and finds the
     * intersections of the shape with the ray and returns a geoPoint
     *
     * @param ray the ray i want to find intersections with
     * @return a list of the GeoPoints that have intersection with the ray
     */
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray){return null;}

    /** a method that calls for findGeoIntersectionsHelper
     *
     * @param ray the ray i want to find intersections with
     * @return a list of the GeoPoints that have intersection with the ray
     */
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    /** a method to find the intersections between the ray and the shape
     *
     * @param ray the ray i want to find intersections with
     * @return a list of the points that have intersection with the ray
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }



}