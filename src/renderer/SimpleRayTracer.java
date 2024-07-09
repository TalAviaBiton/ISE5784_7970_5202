package renderer;

import geometries.Geometry;
import geometries.Intersectable;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
import java.util.*;
import geometries.Intersectable.GeoPoint;

/**
 * extends ray tracer base and represent a simple ray
 */
public class SimpleRayTracer extends RayTracerBase {

    /**
     * a constructor for simple ray tracer
     *
     * @param scene the scene of the photo
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * calculate the color of the given point
     *
     * @param geoPoint the point that i want to find the color of her
     * @return the color of the point
     */
    private Color calcColor(GeoPoint geoPoint) {
        return scene.ambientLight.getIntensity().add(geoPoint.geometry.getEmission());
    }

    /**
     * a method that finds the color of the pixel the ray goes through
     *
     * @param ray the ray that goes through the pixel we want to find the color for
     * @return the color of the pixel the ray goes through
     */
    public Color traceRay(Ray ray) {
        var intersections = scene.geometries.findGeoIntersections(ray);
        return intersections == null
                ? scene.background
                : calcColor(ray.findClosestGeoPoint(intersections));
    }

}

