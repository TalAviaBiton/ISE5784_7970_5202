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
    protected Color emission= Color.BLACK;
    //the material the geometry is made of
    private Material material=new Material();
    /**
     *
     * @return the material the geometry is made of
     */
    public Material getMaterial(){
        return material;
    }

    /** a get method for emission
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
    /** a set method for the emission
     *
     * @param emission the emission I want to add
     * @return the geometry
     */

    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }
    public class Builder {
        Geometry geometry;

        public Geometry setLMaterial(Material material) {
            geometry.material = material;
            return geometry;
        }
        public Geometry build(){
            try {
                return (Geometry) geometry.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public Builder getBuilder() { return new Builder(); }
}
