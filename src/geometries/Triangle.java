package geometries;
/**
 * This class represents a triangle
 */

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import static primitives.Util.*;


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

    /**
     * finds the intersection of a ray and the triangle
     * @param ray the ray that we want to check intersections with
     * if ray has an intersection with the triangle: @return a list with this intersection, if not: @return null
     */
     @Override
   public List<Point> findIntersections(Ray ray) {

         Point p1=vertices.get(0);
         Point p2=vertices.get(1);
         Point p3=vertices.get(2);
         if(ray.getHead().equals(p1) || ray.getHead().equals(p2) || ray.getHead().equals(p3))
             return null;
        Plane plane =new Plane(p1,p2,p3);
         var intersectionPlane=plane.findIntersections(ray);

         Vector v1= p1.subtract(ray.getHead()).normalize();
         Vector v2= p2.subtract(ray.getHead()).normalize();
         Vector v3= p3.subtract(ray.getHead()).normalize();

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
