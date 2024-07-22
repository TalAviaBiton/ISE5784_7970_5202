package renderer;

import primitives.*;
import primitives.Material;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;
import lighting.*;
import geometries.Intersectable.*;

import static java.lang.Math.*;
import static primitives.Util.*;

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
     * @param geoPoint  the point to calculate the effects on
     * @param ray the ray we intersected with
     * @return the result color of the local effects
     */
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray) {
        Vector normal = geoPoint.geometry.getNormal(geoPoint.point);
        Vector rayDirection = ray.getDirection();
        double nv = alignZero(normal.dotProduct(rayDirection));
        if (nv == 0) return geoPoint.geometry.getEmission();
        Material material = geoPoint.geometry.getMaterial();
        Color color = geoPoint.geometry.getEmission();
        for (LightSource lightSource : scene.lights) {
            Vector lightDirection = lightSource.getL(geoPoint.point);
            double cosAngle = alignZero(normal.dotProduct(lightDirection));
            if (cosAngle * nv > 0) { // sign(nl) == sing(nv)
                Color iL = lightSource.getIntensity(geoPoint.point);
                color = color.add(
                        iL.scale(calcDiffusive(material, cosAngle)
                                .add(calcSpecular(material, normal, lightDirection, cosAngle, rayDirection))));
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
     * @param lightDirection the direction of the light
     * @param cosAngle the cosine of the angle between the light and the normal to
     *                 the object
     * @param rayDirection   the direction the camera is pointed to
     * @return the specular light color
     */
    private Double3 calcSpecular(Material material, Vector normal, Vector lightDirection, double cosAngle, Vector rayDirection) {
        Vector r = lightDirection.subtract(normal.scale(2 * cosAngle));
        double coefficient = -rayDirection.dotProduct(r);
        coefficient = coefficient > 0 ? coefficient : 0;
        return material.kS.scale(Math.pow(coefficient, material.nShininess));
    }


    /**
     * calculate the color of the given point
     *
     * @param geoPoint the point that i want to find the color of her
     * @return the color of the point
     */
    private Color calcColor(GeoPoint geoPoint,Ray ray) {
        Color localEffects = calcLocalEffects(geoPoint, ray);
        return scene.ambientLight.getIntensity().add(localEffects);
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


