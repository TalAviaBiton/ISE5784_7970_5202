package geometries;

import primitives.Point;
import primitives.Ray;

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
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
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
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray)
    {

    }

    List<Point> findIntersections(Ray ray);
    List<GeoPoint> findGeoIntersections(Ray ray)
    {
        return findGeoIntersectionsHelper(ray);
    }

}