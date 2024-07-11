package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * a class that represents the ambient light
 */
public class AmbientLight extends Light{

    //the ambient light with default
    public final static AmbientLight NONE = new AmbientLight(Color.BLACK, 0);

    /**
     * a constructor for bantamweight
     *
     * @param iA the color
     * @param kA the coefficient
     */
    public AmbientLight(Color iA, double kA) {
        super(iA.scale(kA));
    }

    /**
     * a constructor for bantamweight
     *
     * @param iA the color
     * @param kI the coefficient
     */
    public AmbientLight(Color iA, Double3 kI) {
        super(iA.scale(kI));
    }

}
