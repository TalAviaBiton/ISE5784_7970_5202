package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 *  a class to represents PointLight
 */
public class PointLight extends Light implements LightSource {
    protected Point position;
    protected double kC = 1;
    protected double kL = 0;
    protected double kQ = 0;

    /**
     *a constructor that gets all the parameters
     * @param intensity the intensity of the light
     * @param position the point the light come from
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     *a constructor that gets only intensity
     * @param intensity the intensity of the light
     */
    public PointLight(Color intensity) {
        super(intensity);
    }

    /**
     * a method to get the intensity of the light in a spesific point
     * @param point the point I want to get the intensity of the light at
     * @return the intensity of the light in point
     */
    @Override
    public Color getIntensity(Point point) {
        double d = position.distance(point);
        return intensity.scale(1 / (kC + kL * d + kQ * d * d));
    }

    /**
     * a method to get the direction of the light in a spesific point
     * @param point the point I want to get the direction of the light at
     * @return the direction of the light in point
     */
    @Override
    public Vector getL(Point point) {

        return point.subtract(position).normalize();
    }

    /**
     *
     * @param position
     * @return
     */
    public PointLight setPosition(Point position) {
        this.position = position;
        return this;
    }

    /**
     *
     * @param KC
     * @return
     */
    public PointLight setKc(double KC) {
        this.kC = KC;
        return this;
    }

    /**
     *
     * @param KL
     * @return
     */
    public PointLight setKl(double KL) {
        this.kL = KL;
        return this;
    }

    /**
     *
     * @param KQ
     * @return
     */
    public PointLight setKq(double KQ) {
        this.kQ = KQ;
        return this;
    }
}
