package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;
import static java.lang.Math.max;

public class SpotLight extends PointLight{
    private Vector direction;

    protected SpotLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction=direction;
    }
    protected SpotLight(Color intensity) {
        super(intensity);
    }
    @Override
    public Color getIntensity(Point p) {
        double d=p.distance(position);
        return intensity.scale(1/(KC+KL*d+KQ*d*d)).scale(max(0,direction.dotProduct(new Vector(position.getXYZ()))));
    }

    @Override
    public Vector getL(Point p) {
        return position.subtract(p);
    }

}
