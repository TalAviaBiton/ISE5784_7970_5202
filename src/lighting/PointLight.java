package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * a class to represents PointLight
 */
public class PointLight extends Light implements LightSource {

    //The position point of the light source in the space
    private final Point position;

    // size for soft shadow, how soft it will be
    private double size = 0;

    /**
     * kC is The specular attenuation factor, required to ensure that the denominator in getIntensity > 1
     * kL is The light source attenuation factor
     * kQ is The attenuation factor of the energy coming to the point
     * <p>
     * the formula is: Il = I0/(Kc + Ki*d + Kq*d^2);
     */
    private double kC = 1, kL = 0, kQ = 0; // Light factor -> constant, linear and Quadratic


    /**
     * a constructor that gets all the parameters
     *
     * @param intensity the intensity of the light
     * @param position  the point the light come from
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    //***************** getters **************************

    /**
     * gets the constant representing soft shadow for the point light
     *
     * @return size of soft shadow of this point light
     */
    public double getSize() {
        return size;
    }


    /**
     * a method to get the intensity of the light in a specific point
     *
     * @param point the point I want to get the intensity of the light at
     * @return the intensity of the light in point
     */
    @Override
    public Color getIntensity(Point point) {
        // The intensity of the color of the light
        // (the distribution of the light in the surface area)
        // is proportional to squared distance

        //Calculate the denominator of the proportion
        double distance = this.position.distance(point);
        double distanceSquared = distance * distance;
        double factor = this.kC + this.kL * distance + this.kQ * distanceSquared;

        //Return the final intensity
        return getIntensity().scale(1 / factor);
    }

    /**
     * a getter for the position of the point light
     *
     * @return the position
     */
    public Point getPosition() {

        return position;
    }


    //**************** override functions ***************

    /**
     * a method to get the direction of the light in a specific point
     *
     * @param point the point I want to get the direction of the light at
     * @return the direction of the light in point
     */
    @Override
    public Vector getL(Point point) {

        return point.subtract(this.position).normalize();
    }

    /**
     * the distance between the point and the light
     *
     * @param point the point i want to find the distance from
     * @return the distance
     */
    @Override
    public double getDistance(Point point) {

        return position.distance(point);
    }


    //**************** setters *********************

    /**
     * Sets the size of soft shadow of the point light
     *
     * @param size representing the size of soft shadow for this point light
     * @return The updated PointLight object
     */
    public PointLight setSize(double size) {
        this.size = size;
        return this;
    }

    /**
     * setter for kC
     *
     * @param kC The specular attenuation factor to set
     * @return this object
     */
    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * setter for kL
     *
     * @param kL The light source attenuation factor to set
     * @return this object
     */
    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * setter for kQ
     *
     * @param kQ The attenuation factor of the energy coming to the point to set
     * @return this object
     */
    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }


}
