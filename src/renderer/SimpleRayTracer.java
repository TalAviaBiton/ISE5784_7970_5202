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

    /**
     * calculate the effects of the geometry itself without the effect of other
     * geometries on this object
     *
     * @param gp  the point to calculate the effects on
     * @param ray the ray we intersected with
     * @return the result color of the local effects
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDirection();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = Util.alignZero(n.dotProduct(v));
        if (nv == 0)
            return color;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = Util.alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color iL = lightSource.getIntensity(gp.point);
                color = color.add(iL.scale(calcDiffusive(material, nl)), iL.scale(calcSpecular(material, n, l, nl, v)));
            }
        }
        return color;
    }

    /**
     * calculates the diffusive light part of the object
     *
     * @param material the material of the object
     * @param cosAngle the cosine of the angle between the light and the normal to
     *                 the object
     * @return the diffusive light color
     */
    private Double3 calcDiffusive(Material material, double cosAngle) {
        return material.kD.scale(cosAngle > 0 ? cosAngle : -cosAngle);
    }

    /**
     * calculates the specular light part of the object
     *
     * @param material the material of the object
     * @param normal   the normal to the object
     * @param lightDir the direction of the light
     * @param cosAngle the cosine of the angle between the light and the normal to
     *                 the object
     * @param rayDir   the direction the camera is pointed to
     * @return
     */
    private Double3 calcSpecular(Material material, Vector normal, Vector lightDir, double cosAngle, Vector rayDir) {
        Vector r = lightDir.subtract(normal.scale(2 * cosAngle));
        double coefficient = -rayDir.dotProduct(r);
        coefficient = coefficient > 0 ? coefficient : 0;
        return material.kS.scale(Math.pow(coefficient, material.nShininess));
    }

//    private double calcSpecular(mat,Vector n, Vector l, nl, v)
//    {
//
//    }
//
//    private double calcDiffusive(mat, nl)
//
//    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
//        Vector n=gp.geometry.getNormal(gp.point);
//        Vector v = ray.getDirection();
//        double nv = alignZero(n.dotProduct(v));
//        Color color = gp.geometry.getEmission();
//        if (nv == 0) return color;
//        Material material = gp.geometry.getMaterial();
//        for (LightSource lightSource : scene.lights) {
//            Vector l = lightSource.getL(gp.point);
//            double nl = alignZero(n.dotProduct(l));
//
//            if (nl * nv > 0) { // sign(nl) == sing(nv)
//                Color iL = lightSource.getIntensity(gp.point);
//                color = color.add(
//                        iL.scale(calcDiffusive(mat, nl)
//                                .add(calcSpecular(mat, n, l, nl, v))));
//            }
//        }
//        return color;
//    }

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

