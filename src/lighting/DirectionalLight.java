package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * a class to represent a directional light
 */
public class DirectionalLight extends Light implements LightSource{

    //the direction of the light
    private Vector direction;

    public DirectionalLight(Color intensity) {
        super(intensity);
    }
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction=direction.normalize();
    }
    @Override
    public Color getIntensity(Point point) {
        return intensity;
    }

    @Override
    public Vector getL(Point point) {
      return direction;
    }
}
