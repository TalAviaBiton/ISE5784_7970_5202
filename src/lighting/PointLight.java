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


    public PointLight(Color intensity,Point position) {
        super(intensity);
        this.position=position;
    }
    public PointLight(Color intensity) {
        super(intensity);
    }

    @Override
    public Color getIntensity(Point point) {
        double d= point.distance(position);
        return intensity.scale(1/(KC+KL*d+KQ*d*d));
    }

    @Override
    public Vector getL(Point point) {
      return position.subtract(point);
    }
    public PointLight setPosition(Point position){
        this.position=position;
        return this;
    }
    public PointLight setKc( double KC){
        this.KC=KC;
        return this;
    }
    public PointLight setKl( double KL){
        this.KL=KL;
        return this;
    }
    public PointLight setKq( double KQ){
        this.KQ=KQ;
        return this;
    }




//    public static class Builder{
//        private final PointLight pointLight=new PointLight(new Color(0,0,0));
//    public Builder setPosition(Point position){
//        pointLight.position=position;
//        return this;
//    }
//    public Builder setKc( double KC){
//        pointLight.KC=KC;
//        return this;
//    }
//    public Builder setKl( double KL){
//        pointLight.KL=KL;
//        return this;
//    }
//    public Builder setKq( double KQ){
//        pointLight.KQ=KQ;
//        return this;
//    }
//        public PointLight build(){
//            try {
//                return (PointLight)pointLight.clone();
//            } catch (CloneNotSupportedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
}
