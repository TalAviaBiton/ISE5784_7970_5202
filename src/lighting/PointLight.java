package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource {
    protected Point position;
    protected double kC = 1;
    protected double kL = 0;
    protected double kQ = 0;


    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    public PointLight(Color intensity) {
        super(intensity);
    }

    @Override
    public Color getIntensity(Point point) {
        double d = position.distance(point);
        return intensity.scale(1 / (kC + kL * d + kQ * d * d));
    }

    @Override
    public Vector getL(Point point) {

        return point.subtract(position).normalize();
    }

    public PointLight setPosition(Point position) {
        this.position = position;
        return this;
    }

    public PointLight setKc(double KC) {
        this.kC = KC;
        return this;
    }

    public PointLight setKl(double KL) {
        this.kL = KL;
        return this;
    }

    public PointLight setKq(double KQ) {
        this.kQ = KQ;
        return this;
    }
}
