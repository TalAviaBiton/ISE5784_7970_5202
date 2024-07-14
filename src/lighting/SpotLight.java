package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;
import static java.lang.Math.max;
import static primitives.Util.alignZero;

public class SpotLight extends PointLight{
    private Vector direction;

    public SpotLight(Color intensity,Point position, Vector direction) {
        super(intensity,position);
        this.direction=direction.normalize();
    }
    public SpotLight(Color intensity) {

        super(intensity);
    }
    @Override
    public Color getIntensity(Point point) {
        return super.getIntensity(point).scale(max(0,direction.dotProduct(new Vector(point.getXYZ().subtract(position.getXYZ())).normalize())));
    }

    @Override
    public Vector getL(Point point) {

        return super.getL(point);
    }


}
