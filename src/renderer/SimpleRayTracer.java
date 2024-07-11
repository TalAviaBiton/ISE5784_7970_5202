package renderer;

import geometries.Geometry;
import geometries.Intersectable;
import primitives.*;
import primitives.Material;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;
import java.util.*;
import geometries.*;
import lighting.*;
import geometries.Intersectable.GeoPoint;

import static primitives.Util.alignZero;

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

    private double calcSpecular(mat,Vector n, Vector l, nl, v)
    {

    }

    private double calcDiffusive(mat, nl)

    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Vector n=gp.geometry.getNormal(gp.point);
        Vector v = ray.getDirection();
        double nv = alignZero(n.dotProduct(v));
        Color color = gp.geometry.getEmission();
        if (nv == 0) return color;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));

            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color iL = lightSource.getIntensity(gp.point);
                color = color.add(
                        iL.scale(calcDiffusive(mat, nl)
                                .add(calcSpecular(mat, n, l, nl, v))));
            }
        }
        return color;
    }

    /**
     * calculate the color of the given point
     *
     * @param geoPoint the point that i want to find the color of her
     * @return the color of the point
     */
    private Color calcColor(GeoPoint geoPoint,Ray ray) {
        return scene.ambientLight.getIntensity().add(calcLocalEffects(geoPoint, ray));
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
                : calcColor(ray.findClosestGeoPoint(intersections),ray);
    }

}

