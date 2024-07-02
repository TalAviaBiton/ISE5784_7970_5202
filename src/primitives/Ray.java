package primitives;

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
     * @param points
     * @return
     */
    public Point findClosestPoint(List<Point> points)
    {
        return null;
    }
}
