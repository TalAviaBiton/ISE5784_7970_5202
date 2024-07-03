package lighting;

import primitives.Color;
import primitives.Double3;

public class AmbientLight {

    private final Color intensity;
    public final static AmbientLight NONE=new AmbientLight(Color.BLACK,0);

    public AmbientLight(Color iA, double kA) {
        intensity = iA.scale(kA);
    }

    public AmbientLight(Color iA, Double3 kI) {
        intensity = iA.scale(kI);
    }

    public Color getIntensity() {
        return intensity;
    }
}
