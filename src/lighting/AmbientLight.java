package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * a class that represents the ambient light
 */
public class AmbientLight {

    //the intensity of the ambient light
    private final Color intensity;
    //the ambient light with default
    public final static AmbientLight NONE = new AmbientLight(Color.BLACK, 0);

    /**
     * a constructor for bantamweight
     *
     * @param iA the color
     * @param kA the coefficient
     */
    public AmbientLight(Color iA, double kA) {
        intensity = iA.scale(kA);
    }

    /**
     * a constructor for bantamweight
     *
     * @param iA the color
     * @param kI the coefficient
     */
    public AmbientLight(Color iA, Double3 kI) {
        intensity = iA.scale(kI);
    }

    /**
     * gets the intensity of the ambient light
     *
     * @return the intensity
     */
    public Color getIntensity() {
        return intensity;
    }
}
