package geometries;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import primitives.*;
public class Geometries implements Intersectable{
    List<Intersectable> geometries =new LinkedList<Intersectable>();
    public Geometries(){

    }

    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    private void add(Intersectable... geometries) {
        Collections.addAll(this.geometries,geometries);
    }

    /**
     * finds all the intersections of a ray with all the geometries in the list
     * @param ray the ray that we want to check intersections with
     * @return a list of the intersections of ray and the geometries from the list
     */
    @Override
    public List<Point> findIntersections(Ray ray) {

        int i=0;
        int counter=0; //the number of intersections
        boolean flag=false; //if there are no intersections
        for (Intersectable geometry = geometries.getFirst(); geometry!=null && i<geometries.size(); i++) {
            //calculates how many intersections ray has with the geometries from the list
            geometry=geometries.get(i);
            if(geometry.findIntersections(ray)!=null) {
                flag = true;
                counter++;
            }
                    }
        if(flag) { //creating the list and adding the intersections
            i=0;
//            List <Point> intersectionsPoints=List.of();
//            for (Intersectable geometry = geometries.getFirst(); geometry!=null; geometry=geometries.get(i)){
//                i++;
//                intersectionsPoints.addAll(geometry.findIntersections(ray));
//            }
//            return intersectionsPoints;

        }
        return null;

    }

}
