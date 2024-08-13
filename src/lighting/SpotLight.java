package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static primitives.Util.alignZero;

/**
 * a class to represents Spot-Light
 */
public class SpotLight extends PointLight {

    //the direction of the light
    private Vector direction;

    // the narrowness of the beam
    private double NarrowBeam = 1;

    /**
     * a constructor that gets all the parameters
     *
     * @param color     the intensity of the light
     * @param position  the point the light come from
     * @param direction the direction of the light
     */
    public SpotLight(Color color, Point position, Vector direction) {
        super(color, position);
        this.direction = direction.normalize();
    }

    //*************** setters ***************

    /**
     * sets the kC
     *
     * @param kC The specular attenuation factor
     * @return the spotlight
     */
    public SpotLight setKc(double kC) {
        super.setKc(kC);
        return this;
    }

    /**
     * sets the kL
     *
     * @param kL The light source attenuation factor
     * @return the spotlight
     */
    public SpotLight setKl(double kL) {
        super.setKl(kL);
        return this;
    }

    /**
     * sets the kQ
     *
     * @param kQ The attenuation factor of the energy coming to the point
     * @return the spotlight
     */
    public SpotLight setKq(double kQ) {
        super.setKq(kQ);
        return this;
    }

    /**
     * sets the NarrowBeam
     *
     * @param narrowBeam the narrowness of the light
     * @return the spotlight
     */
    public SpotLight setNarrowBeam(double narrowBeam) {
        this.NarrowBeam = narrowBeam;
        return this;
    }

    /**
     * a method to get the intensity of the light
     *
     * @param point the point I want to get the color of
     * @return the intensity of the light
     */
    @Override
    public Color getIntensity(Point point) {
        double cos = alignZero(direction.dotProduct(getL(point)));
        return NarrowBeam != 1
                ? super.getIntensity(point).scale(Math.pow(Math.max(0, direction.dotProduct(getL(point))), NarrowBeam))
                : super.getIntensity(point).scale(Math.max(0, direction.dotProduct(getL(point))));
    }
}
