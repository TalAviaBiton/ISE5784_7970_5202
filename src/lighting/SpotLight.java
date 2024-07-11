package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;
import static java.lang.Math.max;

public class SpotLight extends PointLight{
    private Vector direction;

    public SpotLight(Color intensity,Point position, Vector direction) {
        super(intensity,position);
        this.direction=direction;
    }
    public SpotLight(Color intensity) {
        super(intensity);
    }
    @Override
    public Color getIntensity(Point point) {
        double d= point.distance(position);
        return intensity.scale(1/(KC+KL*d+KQ*d*d)).scale(max(0,direction.dotProduct(new Vector(position.getXYZ()))));
    }

    @Override
    public Vector getL(Point point) {
        return position.subtract(point);
    }

}
