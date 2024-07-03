package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
import java.util.*;
public class SimpleRayTracer extends RayTracerBase{

    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    private Color calcColor(Point point)
    {
        return scene.ambientLight.getIntensity();
    }

    /**
     * a method that finds the color of the pixel the ray goes through
     * @param ray the ray that goes through the pixel we want to find the color for
     * @return the color of the pixel the ray goes through
     */
    @Override
    public Color traceRay(Ray ray) {
        List<Point> intersections= scene.geometries.findIntersections(ray);
        if(intersections==null)
            return scene.background;
        return calcColor(ray.findClosestPoint(intersections));
    }
}
