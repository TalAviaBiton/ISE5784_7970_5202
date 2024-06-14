package geometries;
/**
 * This class represents a triangle
 */

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

public class Triangle extends Polygon {
    /**
     * a constractor to create a new triangle
     *
     * @param p1 first vertex of the triangle
     * @param p2 second vertex of the triangle
     * @param p3 third vertex of the triangle
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }
     @Override
   public List<Point> findIntersections(Ray ray) {

        Plane plane =new Plane(this.vertices.get(0),this.vertices.get(1),this.vertices.get(2));
         var intersectionPlane=plane.findIntersections(ray);

         Vector v1= this.vertices.get(0).subtract(ray.getHead());
         Vector v2= this.vertices.get(1).subtract(ray.getHead());
         Vector v3= this.vertices.get(2).subtract(ray.getHead());

         Vector n1=v1.crossProduct(v2).normalize();
         Vector n2=v2.crossProduct(v3).normalize();
         Vector n3=v3.crossProduct(v1).normalize();

         if((ray.getDirection().dotProduct(n1)>0
                 && ray.getDirection().dotProduct(n2)>0
                 && ray.getDirection().dotProduct(n3)>0) ||
                        (ray.getDirection().dotProduct(n1)<0
                         && ray.getDirection().dotProduct(n2)<0
                         && ray.getDirection().dotProduct(n3)<0))
            return intersectionPlane;
        return null;
   }
}
