//package renderer;
//
//import geometries.Triangle;
//import primitives.Color;
//import primitives.*;
//import scene.*;
//import geometries.Intersectable.GeoPoint;
//import lighting.*;
//import java.util.List;
//import static primitives.Util.alignZero;
//import static primitives.Util.isZero;
//
///**
// * the most basic rayTracer
// */
//public class SimpleRayTracer extends RayTracerBase {
//
//    private static final double DELTA = 0.1;
//    private static final int MAX_CALC_COLOR_LEVEL = 10;
//    private static final double MIN_CALC_COLOR_K = 0.001;
//    private static final Double3 INITIAL_K = Double3.ONE;
//    private static final double EPS = 0.1;
//
//
//
//    /**
//     * construct rayTracer with specific scene
//     *
//     * @param scene with the relevant geometries and lighting
//     */
//    public SimpleRayTracer(Scene scene) {
//        super(scene);
//    }
//
////    @Override
////    public Color traceRay(Ray ray) {
////        //List<GeoPoint> geoPoints = scene.geometries.findGeoIntersections(ray);
////        //return (geoPoints == null) ? scene.background : calcColor(ray.findClosestGeoPoint(geoPoints), ray);
////        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
////        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
////        return calcColor(closestPoint, ray);
////    }
//    @Override
//    public Color traceRay(Ray ray) {
//        GeoPoint intersectionPoint = scene.geometries.findClosestIntersection(ray);
//        return (intersectionPoint == null) ? scene.background : calcColor(intersectionPoint, ray);
//    }
//
//    /**
//     * returns the color of a given point
//     *
//     * @param geoPoint the point to calculate
//     * @return the color of the point
//     */
//    private Color calcColor(GeoPoint geoPoint, Ray ray) {
//        return calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
//                .add(scene.ambientLight.getIntensity());
//
//    }
//
//    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
//        Color color = calcLocalEffects(geoPoint, ray,k);
//        return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, ray, level, k));
//
//    }
//
//    private Color calcGlobalEffects(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
//        Material material = geoPoint.geometry.getMaterial();
//        return calcGlobalEffect(
//                constructRefractedRay(ray.getDirection(),geoPoint.point,geoPoint.geometry.getNormal(geoPoint.point)),
//                material.kT, level, k)
//                .add(
//                        calcGlobalEffect(
//                        constructReflectedRay(ray.getDirection(),geoPoint.point,geoPoint.geometry.getNormal(geoPoint.point)),
//                                material.kR, level, k));
//    }
//
//
//    private Color calcGlobalEffect(Ray ray, Double3 kx, int level, Double3 k) {
//        Double3 kkx = kx.product(k);
//        if (kkx.lowerThan(MIN_CALC_COLOR_K))
//            return Color.BLACK;
//        GeoPoint gp = scene.geometries.findClosestIntersection(ray);
//        return (gp == null ? scene.background : calcColor(gp, ray, level-1, kkx))
//                .scale(kx);
//    }
//
//    /**
//     * calculate the effects of the geometry itself without the effect of other
//     * geometries on this object
//     *
//     * @param geoPoint  the point to calculate the effects on
//     * @param ray the ray we intersected with
//     * @return the result color of the local effects
//     */
//    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray,Double3 k) {
//        Color color = geoPoint.geometry.getEmission();
//        Vector v = ray.getDirection();
//        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
//        double nv = alignZero(n.dotProduct(v));
//        if (isZero(nv))
//            return color;
//        Material material = geoPoint.geometry.getMaterial();
//        for (LightSource lightSource : scene.lights) {
//            Vector l = lightSource.getL(geoPoint.point);
//            double nl = alignZero(n.dotProduct(l));
//            if (nl * nv > 0)//&& unshaded(geoPoint, lightSource, l, n, nl))// sign(nl) == sing(nv)
//            {
//                Double3 ktr = transparency(geoPoint, nv, n, l,lightSource);
//                if (!(ktr.product(k).lowerThan(MIN_CALC_COLOR_K))) {
//                    Color iL = lightSource.getIntensity(geoPoint.point).scale(ktr);
//                    color = color.add(iL.scale(calcDiffusive(material, nl)), iL.scale(calcSpecular(material, n, l, nl, v)));
//                }
//
//            }
//        }
//        return color;
//    }
//
//    /**
//     * calculates the diffusive light part of the object
//     *
//     * @param material the material of the object
//     * @param cosAngle the cosine of the angle between the light and the normal to
//     *                 the object
//     * @return the diffusive light color
//     */
//    private Double3 calcDiffusive(Material material, double cosAngle) {
//        return material.kD.scale(cosAngle > 0 ? cosAngle : -cosAngle);
//    }
//
//    /**
//     * calculates the specular light part of the object
//     *
//     * @param material the material of the object
//     * @param normal   the normal to the object
//     * @param lightDir the direction of the light
//     * @param cosAngle the cosine of the angle between the light and the normal to
//     *                 the object
//     * @param rayDir   the direction the camera is pointed to
//     * @return the specular part
//     */
//    private Double3 calcSpecular(Material material, Vector normal, Vector lightDir, double cosAngle, Vector rayDir) {
//        Vector r = lightDir.subtract(normal.scale(2 * cosAngle));
//        double coefficient = -rayDir.dotProduct(r);
//        coefficient = coefficient > 0 ? coefficient : 0;
//        return material.kS.scale(Math.pow(coefficient, material.nShininess));
//    }
//
//    /**
//     * checks if a point on a geometry is shaded by a light source
//     *
//     * @param geoPoint       the point on the geometry
//     * @param l        the direction vector of the light source
//     * @param n        the normal to the geometry
//     * @param nl the cosine of the angle between l and n
//     * @return True if the object is not shaded, False otherwise
//     */
//    private boolean unshaded(GeoPoint geoPoint, LightSource lightSource, Vector l, Vector n, double nl) {
//
//        Vector lightDirection = l.scale(-1); // from point to light source
//        Vector epsVector = n.scale(nl < 0 ? DELTA : -DELTA);
//        Point point = geoPoint.point.add(epsVector);
//        Ray lightRay = new Ray(point, lightDirection);
//        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
//
//        if(intersections==null || intersections.isEmpty())
//            return true;
//        for (GeoPoint intersection : intersections) {
//            if (lightSource.getDistance(intersection.point) > point.distance(intersection.point))
//                return false;
//        }
//        return true;
//    }
//
//
//    /**
//     * calculates the transparency coefficient of a point on a geometry
//     *
//     * @param gp          the point and its geometry
//     * @param nv          the distance between the light source and gp
//     * @param l           the direction vector of the light source
//     * @param n           the normal to the geometry
//     * @param lightSource the light source
//     * @return the transparency coefficient
//     */
//    private Double3 transparency(GeoPoint gp, double nv, Vector l, Vector n, LightSource lightSource) {
//        Vector epsVector = n.scale(nv < 0 ? EPS : -EPS);
//        Point point = gp.point.add(epsVector);
//        Ray lightRay = new Ray(n, point, l.scale(-1));
//        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
//        Double3 ktr = Double3.ONE;
//        if (intersections == null)
//            return ktr;
//        for (GeoPoint intersection : intersections){
//            if (!(lightSource.getDistance(gp.point) > gp.point.distance(intersection.point)))
//                ktr = ktr.product(intersection.geometry.getMaterial().kT);
//        }
//        return ktr;
//    }
//
//    /**
//     * calculates the ray for the refraction
//     *
//     * @param n        the normal vector to the geometry
//     * @param point    the point on our geometry
//     * @param inVector the vector coming in from the light
//     * @return the calculated ray
//     */
//    private Ray constructRefractedRay(Vector n, Point point, Vector inVector) {
//        return new Ray(n, point, inVector);
//    }
//
//    /**
//     * calculates the ray for the reflection
//     *
//     * @param n        the normal vector to the geometry
//     * @param point    the point on our geometry
//     * @param inVector the vector coming in from the light
//     * @return the calculated ray
//     */
//    private Ray constructReflectedRay(Vector n, Point point, Vector inVector) {
//        return new Ray(n, point, inVector.subtract(n.scale(2 * inVector.dotProduct(n))));
//
//    }
//}

package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
        import scene.Scene;

import java.util.List;

import static java.lang.Math.*;
        import static primitives.Util.alignZero;

/**
 * Class SimpleRayTracer is a class of simple ray tracers
 * @author Rachel and Tehila
 */
public class SimpleRayTracer extends RayTracerBase {
    /** the maximum depth of the recursion in the calculation of the global effects */
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    /** the minimal value of the effect the recursion has on the image in the calculation of the global effects */
    private static final double MIN_CALC_COLOR_K = 0.001;
    /** the initial value of k (the level of effects the color calculation has on the image) */
    private static final Double3 INITIAL_K = Double3.ONE;

    private static final double EPS = 0.1;
    /**
     * a constructor for SimpleRayTracer
     * @param scene the scene the rays that the class is tracing go through
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
    }

    /**
     * calculates the color of a GeoPoint in the scene
     * @param gp the intersection GeoPoint between the ray and the scene
     * @param ray the ray from the camera to the point
     * @return the color of the GeoPoint received
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * calculates the color of a GeoPoint in the scene
     * @param gp the intersection GeoPoint between the ray and the scene
     * @param ray the ray from the camera to the point
     * @return the color of the GeoPoint received
     */
    private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(gp, ray, k);
        return 1 == level ? color : color.add(calcGlobalEffects(gp, ray, level, k));
    }

    /**
     * calculates the global effects of the color of the point - the transparency and reflection
     * @param gp the geoPoint
     * @param ray the primary ray that intersects the point
     * @param level the level of the recursion
     * @param k the level of effects the color has on the image
     * @return the color of the global effects of the light on the point
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Material material = gp.geometry.getMaterial();
        return calcGlobalEffect(constructRefractedRay(gp, ray), material.kT, level, k)
                .add(calcGlobalEffect(constructReflectedRay(gp, ray), material.kR, level, k));
    }

    /**
     * calculates the reflected ray, from the point of intersection in the direction of the reflection on the geometry
     * @param geoPoint the geoPoint of intersection
     * @param ray the primary ray to reflect
     * @return the reflected ray
     */
    private Ray constructReflectedRay(GeoPoint geoPoint, Ray ray) {
        Vector r = ray.getDirection();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        r = r.subtract(n.scale(2 * n.dotProduct(r)));
        return new Ray(n,geoPoint.point, r);
    }

    /**
     * calculates the refracted ray, from the point of intersection in the direction of the refraction on the geometry
     * @param geoPoint the geoPoint of intersection
     * @param ray the primary ray to refract
     * @return the refracted ray
     */
    private Ray constructRefractedRay(GeoPoint geoPoint, Ray ray) {
        Point point = geoPoint.point;
        return new Ray( geoPoint.geometry.getNormal(point),point, ray.getDirection());
    }

    /**
     * calculates the transparency or the reflection effects on a point by the secondary ray that comes out of it
     * @param ray the secondary ray of the Refracted or Reflected from the primary one
     * @param kx the attenuation coefficient of the transparency/reflection of the material
     * @param level the level of the recursion
     * @param k the level of effects the color has on the image
     * @return the color of transparency/reflection effects of the light on the point
     */
    private Color calcGlobalEffect(Ray ray, Double3 kx, int level, Double3 k) {
        Double3 kkx = kx.product(k);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) {
            return Color.BLACK;
        }
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx))
                .scale(kx);
    }

    /**
     * finds the closest intersection geoPoint the ray has with the scene
     * @param ray the ray
     * @return the closest intersection geoPoint the ray has with the scene and null if there are no intersections
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        return intersections == null
                ? null
                : ray.findClosestGeoPoint(intersections);
    }

    /**
     * calculates the color of the point by local effects
     * @param geoPoint the geoPoint that it calculates the color for
     * @param ray the ray from the camera to the point
     * @return the color of the local effects of the light on the point
     */
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray, Double3 k) {
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        Vector v = ray.getDirection();
        double nv = n.dotProduct(v);
        Color color = geoPoint.geometry.getEmission();
        if (nv == 0) {
            return color;
        }

        Material material = geoPoint.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(geoPoint.point);
            double nl = n.dotProduct(l);
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Double3 ktr = transparency(geoPoint,nv, l, n,lightSource);
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
                    Color iL = lightSource.getIntensity(geoPoint.point).scale(ktr);
                    color = color.add(
                            iL.scale(calcDiffusive(material, nl)
                                    .add(calcSpecular(material, n, l, nl, v))));
                }
            }
        }
        return color;
    }

    /**
     * calculates the diffusive effects of a light source on the point
     * @param material the material of the point
     * @param nDotProductL the dot product between the normal of the geometry from the point
     *           and the vector from the light source to the point.
     * @return the diffusive effects
     */
    private Double3 calcDiffusive(Material material, double nDotProductL) {
        return material.kD.scale(abs(nDotProductL));
    }

    /**
     * calculates the specular effects of a light source on the point
     * @param material the material of the point
     * @param n the normal from the point of the geometry
     * @param l the vector from the light source to the point
     * @param nDotProductL the dot product between n and l
     * @param rayDirection the direction of the ray
     * @return the specular effects
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nDotProductL, Vector rayDirection) {
        Vector r = l.subtract(n.scale(nDotProductL * 2));
        return material.kS.scale(pow(max(0, - rayDirection.dotProduct(r)), material.nShininess));
    }

    /**
     * calculates the transparency of all the intersection points between the light source and the geoPoint
     * and return how much from the light should affect to the point.
     * @param gp the point
     * @param lightSource the light source
     * @param l  the vector between the light source and the point
     * @param n  the normal of the geometry the point is on at the point
     * @return a Double3 that representing how much from the light should affect to the point.
     */
//
        private Double3 transparency(GeoPoint gp, double nv, Vector l, Vector n, LightSource lightSource) {
        Vector epsVector = n.scale(nv < 0 ? EPS : -EPS);
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(n, point, l.scale(-1));
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        Double3 ktr = Double3.ONE;
        if (intersections == null)
            return ktr;
        for (GeoPoint intersection : intersections){
            if (lightSource.getDistance(gp.point) > intersection.point.distance(gp.point))
                ktr = ktr.product(intersection.geometry.getMaterial().kT);
        }
        return ktr;
    }
}