package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * a class that represents the ambient light and extends light class
 */
public class AmbientLight extends Light {

    //the ambient light with default
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, new Double3(0, 0, 0));

    /**
     * a constructor for bantamweight
     *
     * @param iA the intensity
     * @param kA the coefficient
     */
    public AmbientLight(Color iA, double kA) {

        super(iA.scale(kA));
    }

    /**
     * a constructor for bantamweight
     *
     * @param iA the color
     * @param kA the coefficient
     */
    public AmbientLight(Color iA, Double3 kA) {

        super(iA.scale(kA));
    }

    /**
     * default constructor for initialize the background to black
     */
    public AmbientLight() {
        super(Color.BLACK);
    }
}
