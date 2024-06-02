package geometries;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import primitives.*;
public class Geomtries implements Intersectable{
    List<Intersectable> geomtries=new LinkedList<Intersectable>();
    public Geomtries(){

    }

    public Geomtries(Intersectable... geomtries) {
        add(geomtries);
    }

    private void add(Intersectable... geomtries) {
        Collections.addAll(this.geomtries,geomtries);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }

}
