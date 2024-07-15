package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * a class to represent a directional light
 */
public class DirectionalLight extends Light implements LightSource {

    //the direction of the light
    private Vector direction;

    /**
     * a constructor that gets only the lights intensity
     *
     * @param intensity the intensity of the light
     */
    public DirectionalLight(Color intensity) {

        super(intensity);
    }

    /**
     * a constructor that gets all the parameters
     *
     * @param intensity the intensity of the light
     * @param direction the direction of the light
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    /**
     * a method to get the color's intensity
     *
     * @param point th point i want to get th color of
     * @return the intensity of the color
     */
    @Override
    public Color getIntensity(Point point) {

        return super.getIntensity();//intensity
    }

    /**
     * a method to get the direction of the light
     *
     * @param point the point we want the light's direction in
     * @return the direction of the light
     */
    @Override
    public Vector getL(Point point) {

        return direction;
    }
}
