package primitives;

import geometries.Intersectable;

import java.util.List;

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
     * @param points the list of point that i check who is the closest to the head of the ray
     * @return the point that is the closest to the head of the ray
     */
    public Point findClosestPoint(List<Point> points)
    {
        if(points.isEmpty())
            return null;
        double minDistance=head.distance(points.getFirst());
        Point minPoint=points.getFirst();
        for (Point point : points)
        {
            double pointDistance=head.distance(point);
            if(pointDistance<minDistance)
            {
                minPoint=point;
                minDistance=pointDistance;
            }
        }
        return minPoint;
    }
}
