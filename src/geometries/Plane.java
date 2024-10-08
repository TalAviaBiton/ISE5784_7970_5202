package geometries;

import java.util.List;

import primitives.*;

/**
 * 2D plane in a 3D world
 *
 * @author Shulman and Yonatan
 */
public class Plane extends Geometry {

    private final Point p0;
    private final Vector normal;

    /**
     * create a plane using 3 points on the plane
     *
     * @param p1 1st point on the plane
     * @param p2 2nd point on the plane
     * @param p3 3rd point on the plane
     */
    public Plane(Point p1, Point p2, Point p3) {
        this.p0 = p1;
        this.normal = (p2.subtract(p1).crossProduct(p3.subtract(p1))).normalize();
    }

    /**
     * create plane using point in the plane and normal to the plane
     *
     * @param p0     a point on the plane
     * @param normal to the plane
     */
    public Plane(Point p0, Vector normal) {
        this.p0 = p0;
        this.normal = normal.normalize();
    }

    //********************** getters ********************

    /**
     * getter for the normal to the Plane
     *
     * @return the normal
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * getter for the point in the Plane
     *
     * @return the point
     */
    public Point getP0() {
        return p0;
    }

    @Override
    public Vector getNormal(Point point) {
        return normal;
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point rayP0 = ray.getHead();
        if (rayP0.equals(p0))
            return null;
        double denom = normal.dotProduct(ray.getDirection());
        if (Util.isZero(denom))
            return null;
        double t = Util.alignZero(normal.dotProduct(p0.subtract(rayP0)) / denom);
        return t <= 0 ? null : List.of(new GeoPoint(this, ray.getPoint(t)));
    }

    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    @Override
    public void createBoundingBox() {
        //not implemented because plane is never ending so can't be bound in box
    }
}