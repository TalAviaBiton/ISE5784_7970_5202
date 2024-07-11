package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource{
    private Vector direction;
    protected DirectionalLight(Color intensity) {
        super(intensity);
    }
    protected DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction=direction;
    }
    @Override
    public Color getIntensity(Point p) {
        return intensity;
    }

    @Override
    public Vector getL(Point p) {
      return direction;
    }
}
