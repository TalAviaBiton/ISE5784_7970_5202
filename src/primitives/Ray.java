package primitives;

import geometries.Geometry;
import geometries.Intersectable;

import java.util.List;
import geometries.Intersectable.GeoPoint;
import static primitives.Util.isZero;

/**
 * This class will serve most geometries classes by representing a ray
 */
public class Ray {
    /**
     * parameter to represent the head of the ray
     */
    protected Point head;

    /**
     * parameter to represent the direction of the ray
     */
    protected Vector direction;


    //fixed for ray head offset size for shading rays
    private static final double DELTA = 0.1;

    /**
     * Constructor to initialize the ray
     *
     * @param head      the head of the ray
     * @param direction the direction of the ray
     */
    public Ray(Point head, Vector direction) {
        this.head = head;
        this.direction = direction.normalize();
    }

    public Ray(Vector normal, Point point, Vector direction) {
        this.direction = direction.normalize();
        double dotProduct = this.direction.dotProduct(normal);
        if (Util.isZero(dotProduct))
            this.head = point;
        else
            this.head = point.add(normal.scale(Util.alignZero(dotProduct) < 0 ? -DELTA : DELTA));

    }

    public Vector getDirection() {

        return direction;
    }

    public Point getHead() {

        return head;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Ray other)
                && this.head.equals(other.head)
                && this.direction.equals(other.direction);
    }

    @Override
    public String toString() {
        return "primitives.Ray{" +
                "head=" + head +
                ", direction=" + direction +
                '}';
    }

    /**
     * Calculates a point on the line of the ray, at a distance t
     *
     * @param t the distance between the calculated point and the ray's head
     */
    public Point getPoint(double t) {
        if (isZero(t))
            return head;
        return head.add(direction.scale(t));
    }

    /**
     * @param points the list of points that i check who is the closest to the head of the ray
     * @return the point that is the closest to the head of the ray
     */
    public Point findClosestPoint(List<Point> points) {
        return points==null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream()
                .map(p -> new GeoPoint(null, p)).toList()).point;
    }

    /**
     * @param geoPoints the list of GeoPoints that i check who is the closest to the head of the ray
     * @return the GeoPoint that is the closest to the head of the ray
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPoints)
    {
        if(geoPoints==null ||geoPoints.isEmpty() )
            return null;
        double minDistance=head.distance(geoPoints.getFirst().point);
        GeoPoint minPoint=geoPoints.getFirst();
        for (GeoPoint geoPoint : geoPoints)
        {
            double pointDistance=head.distance(geoPoint.point);
            if(pointDistance<minDistance)
            {
                minPoint=geoPoint;
                minDistance=pointDistance;
            }
        }
        return minPoint;
    }
}
