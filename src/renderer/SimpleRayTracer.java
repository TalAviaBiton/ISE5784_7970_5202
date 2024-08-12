package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.DirectionalLight;
import lighting.LightSource;
import lighting.PointLight;
import primitives.*;
import scene.Scene;
import renderer.BlackBoard;
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
     //The number of rays to be used for ray tracing.
    private double numOfRays;
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
                Double3 ktr ;//= transparency(geoPoint,nv, l, n,lightSource);
                // Determine the type of light source and calculate transparency accordingly
                if (lightSource instanceof DirectionalLight || (lightSource instanceof PointLight && ((PointLight) lightSource).getSize() == 0)) {
                    ktr = transparency(geoPoint,nv, l, n,lightSource);
                } else {
                    ktr = calcLocalEffectsSoftShadows((PointLight) lightSource, geoPoint, n, nv, v, material);
                }
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
     * Calculates the local effects of soft shadows for a given point on a surface.
     * Soft shadows are created by sampling multiple rays from the light source to determine the shadow intensity.
     * The number of rays used is determined by the value of {@code numOfRays}.
     *
     * @param lightSource the light source casting the shadows
     * @param gp          the geometric point on the surface
     * @param n           the surface normal at the point
     * @param nv          the dot product of the surface normal and the view direction
     * @param v           the view direction
     * @param material    the material of the surface
     * @return the calculated local effects as a Double3 object representing the combined transparency values
     */
    private Double3 calcLocalEffectsSoftShadows(PointLight lightSource, GeoPoint gp, Vector n, double nv, Vector v, Material material) {
        Vector l = lightSource.getL(gp.point);
        BlackBoard blackBoard = new BlackBoard((int) Math.round(Math.sqrt(numOfRays)),
                lightSource.getSize(),
                l,
                new Vector(0, -l.getZ(), l.getY()),
                lightSource.getPosition());
        Ray ray;
        int rays = 0;
        Double3 ktr = Double3.ZERO;

        // Iterate over the rays for soft shadows
        for (int i = 1; i < Math.round(Math.sqrt(numOfRays)); i++) {
            for (int j = 1; j < Math.round(Math.sqrt(numOfRays)); j++) {
                // Construct a ray from the blackboard for the current iteration
                ray = blackBoard.constructRay(j, i, gp.point);
                if (ray != null) {
                    rays++;
                    // Add the transparency value of the intersection point to the accumulated ktr value
                    ktr = ktr.add(transparency(gp, nv,ray.getDirection(), n,lightSource));
                }
            }
        }

        // Reduce the combined transparency values by the number of rays to obtain the average transparency
        ktr = ktr.reduce(rays);

        return ktr;

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
        Point point = gp.point;//.add(epsVector);
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

    /**
     * Sets the number of rays to be used for ray tracing.
     *
     * @param numOfRays the number of rays to be used
     * @return the updated RayTracerBasic object
     */
    public SimpleRayTracer setNumOfRays(double numOfRays) {
        this.numOfRays = numOfRays;
        return this;
    }
}