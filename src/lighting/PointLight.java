package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * a class to represents PointLight
 */
public class PointLight extends Light implements LightSource {
    protected Point position;
    protected double kC = 1;
    protected double kL = 0;
    protected double kQ = 0;

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

    /**
     * a constructor that gets only intensity
     *
     * @param intensity the intensity of the light
     */
    public PointLight(Color intensity) {
        super(intensity);
    }

    /**
     * a method to get the intensity of the light in a specific point
     *
     * @param point the point I want to get the intensity of the light at
     * @return the intensity of the light in point
     */
    @Override
    public Color getIntensity(Point point) {
        double d = position.distance(point);
        return intensity.scale(1d / (kC + kL * d + kQ * d * d));
    }

    /**
     * a method to get the direction of the light in a specific point
     *
     * @param point the point I want to get the direction of the light at
     * @return the direction of the light in point
     */
    @Override
    public Vector getL(Point point) {

        return point.subtract(position).normalize();
    }

    /**
     * setter for position
     *
     * @param position the position to set
     * @return this object
     */
    public PointLight setPosition(Point position) {
        this.position = position;
        return this;
    }

    /**
     * setter for kC
     *
     * @param kC the kc to set
     * @return this object
     */
    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * setter for kL
     *
     * @param kL the kL to set
     * @return this object
     */
    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * setter for kQ
     *
     * @param kQ the kQ to set
     * @return this object
     */
    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }

    /**
     *
     * @param point
     * @return
     */
    @Override
    public double getDistance(Point point) {
        return position.distance(point);
    }
}
