package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;
import static java.lang.Math.max;

/**
 * a class to represents Spot-Light
 */
public class SpotLight extends PointLight{

    //the direction of the light
    private Vector direction;

    /**
     * a constructor that gets all the parameters
     * @param intensity the intensity of the light
     * @param position  the point the light come from
     * @param direction the direction of the light
     */
    public SpotLight(Color intensity,Point position, Vector direction) {
        super(intensity,position);
        this.direction=direction.normalize();
    }

    /**
     * a constructor that gets only  the intensity of the light
     * @param intensity the intensity of the light
     */
    public SpotLight(Color intensity) {

        super(intensity);
    }

    /**
     * a method to get the intensity of the light
     * @param point the point I want to get the color of
     * @return the intensity of the light
     */
    @Override
    public Color getIntensity(Point point) {
        return super.getIntensity(point).scale(max(0,direction.dotProduct(new Vector(point.getXYZ().subtract(position.getXYZ())).normalize())));
    }

    /**
     * a method to get the direction of the light in point
     * @param point the point I want to get the direction of the light at
     * @return the direction of the light in point
     */
    @Override
    public Vector getL(Point point) {

        return super.getL(point);
    }


}
