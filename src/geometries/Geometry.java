package geometries;

import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

/**
 * This interface will represent all geometries
 */
public abstract class Geometry extends Intersectable {

    //the emission of the geometry
    protected Color emission = Color.BLACK;
    //the material the geometry is made of
    private Material material = new Material();

    //**************** getters ***************

    /**
     * a get method for material
     *
     * @return the material the geometry is made of
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * a get method for emission
     *
     * @return the emission
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * a method that calculates the normal of the geometry at the point
     *
     * @param point the point that the normal is going throw
     * @return the normal-vector
     */
    public abstract Vector getNormal(Point point);


    //*********** setters **********************

    /**
     * a set method for the emission
     *
     * @param emission the emission I want to add
     * @return the geometry
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public Geometry setBVH(boolean bvh) {
        BVH = bvh;
        return this;
    }
}
