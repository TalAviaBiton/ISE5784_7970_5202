package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * This interface will represent all geometries
 */
public abstract class Geometry extends Intersectable {

    //the emission of the geometry
    protected Color emission= Color.BLACK;

    /** a set method for the emission
     *
     * @param emission the emission i want to add
     * @return the geometry
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
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
}
