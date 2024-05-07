package geometries;


import primitives.Point;
import primitives.Vector;

public class  Plane implements Geometry{
    private Point q;
    private Vector normal;
    public Plane(Point x, Point y, Point z)
    {
        q = x;
        normal=null;
    }

    public Plane(Vector normal, Point q) {
        this.normal = getNormal(normal);
        this.q = q;
    }

    public Vector getNormal()
    {
        return normal.normalize();

    }
    @Override
    public Vector getNormal(Point point) {
        return normal.normalize();
    }
}
