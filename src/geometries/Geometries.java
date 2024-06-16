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

    @Override
    public List<Point> findIntersections(Ray ray) {

        int i=0;
        int counter=0; //the number of intersections
        boolean flag=false; //if there are no intersections
        for (Intersectable geometry = geometries.get(0); geometry!=null; geometry=geometries.get(i)) {
            i++;
            if(geometry.findIntersections(ray)!=null) {
                flag = true;
                counter++;
            }
        }
        if(flag) {
            i=0;
            List <Point> intersectionsPoints=new LinkedList<>(List.of());
            for (Intersectable geometry = geometries.get(0); geometry!=null; geometry=geometries.get(i)){
                i++;
                intersectionsPoints.addAll(geometry.findIntersections(ray));
            }
            return intersectionsPoints;
        }
        return null;

    }

}
