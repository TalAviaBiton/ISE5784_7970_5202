package renderer;

import geometries.Triangle;
import primitives.Color;
import primitives.*;
import scene.*;
import geometries.Intersectable.GeoPoint;
import lighting.*;
import java.util.List;

/**
 * the most basic rayTracer
 *
 * @author Shulman and Yonatan
 *
 */
public class SimpleRayTracer extends RayTracerBase {

    //fixed for ray head offset size for shading rays
    private static final double DELTA = 0.1;

    //private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final Double3 INITIAL_K = Double3.ONE;
    private static final int MAX_CALC_COLOR_LEVEL = 3;


    /**
     * construct rayTracer with specific scene
     *
     * @param scene with the relevant geometries and lighting
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        //List<GeoPoint> geoPoints = scene.geometries.findGeoIntersections(ray);
        //return (geoPoints == null) ? scene.background : calcColor(ray.findClosestGeoPoint(geoPoints), ray);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return calcColor(closestPoint, ray);
    }

    /**
     * returns the color of a given point
     *
     * @param geoPoint the point to calculate
     * @return the color of the point
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());

    }

    private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(gp, ray);
        return 1 == level ? color : color.add(calcGlobalEffects(gp, ray, level, k));

    }

    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Material material = gp.geometry.getMaterial();
        return calcGlobalEffect(constructRefractedRay(gp, ray), material.kT, level, k)
                .add(calcGlobalEffect(constructReflectedRay(gp, ray), material.kR, level, k));
    }


    private Color calcGlobalEffect(Ray ray, Double3 kx, int level, Double3 k) {
        Double3 kkx = kx.product(k);
        if (kkx.lowerThan(MIN_CALC_COLOR_K))
            return Color.BLACK;
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? scene.background : calcColor(gp, ray, level-1, kkx))
                .scale(kx);
    }

    private GeoPoint findClosestIntersection(Ray ray) {
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
        Color color = geoPoint.geometry.getEmission();
        Vector v = ray.getDirection();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        double nv = Util.alignZero(n.dotProduct(v));
        if (nv == 0)
            return color;
        Material material = geoPoint.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(geoPoint.point);
            double nl = Util.alignZero(n.dotProduct(l));
            if ((nl * nv > 0) && unshaded(geoPoint, lightSource, l, n, nl)) {
                Color iL = lightSource.getIntensity(geoPoint.point);
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
     * @return the specular part
     */
    private Double3 calcSpecular(Material material, Vector normal, Vector lightDir, double cosAngle, Vector rayDir) {
        Vector r = lightDir.subtract(normal.scale(2 * cosAngle));
        double coefficient = -rayDir.dotProduct(r);
        coefficient = coefficient > 0 ? coefficient : 0;
        return material.kS.scale(Math.pow(coefficient, material.nShininess));
    }

    /**
     * checks if a point on a geometry is shaded by a light source
     *
     * @param geoPoint       the point on the geometry
     * @param l        the direction vector of the light source
     * @param n        the normal to the geometry
     * @param nl the cosine of the angle between l and n
     * @return True if the object is not shaded, False otherwise
     */
    private boolean unshaded(GeoPoint geoPoint, LightSource lightSource, Vector l, Vector n, double nl) {

        Vector lightDirection = l.scale(-1); // from point to light source
        Vector epsVector = n.scale(nl < 0 ? DELTA : -DELTA);
        Point point = geoPoint.point.add(epsVector);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

        if(intersections==null || intersections.isEmpty())
            return true;
        for (GeoPoint intersection : intersections) {
            if (lightSource.getDistance(intersection.point) > point.distance(intersection.point))
                return false;
        }
        return true;
    }
}