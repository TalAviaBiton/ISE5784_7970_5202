package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;
import scene.Scene;

public class PointLight extends Light implements LightSource{
    protected Point position;
    protected double KC=1;
    protected double KL=0;
    protected double KQ=0;


    protected PointLight(Color intensity,Point position) {
        super(intensity);
        this.position=position;
    }
    protected PointLight(Color intensity) {
        super(intensity);
    }

    @Override
    public Color getIntensity(Point p) {
        double d=p.distance(position);
        return intensity.scale(1/(KC+KL*d+KQ*d*d));
    }

    @Override
    public Vector getL(Point p) {
      return position.subtract(p);
    }

    public static class Builder{
        private final PointLight pointLight=new PointLight(new Color(0,0,0));
    public Builder setPosition(Point position){
        pointLight.position=position;
        return this;
    }
    public Builder setKC( double KC){
        pointLight.KC=KC;
        return this;
    }
    public Builder setKL( double KL){
        pointLight.KL=KL;
        return this;
    }
    public Builder setKQ( double KQ){
        pointLight.KQ=KQ;
        return this;
    }
        public PointLight build(){
            try {
                return (PointLight)pointLight.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
}
}
