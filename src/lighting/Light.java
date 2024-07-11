package lighting;

import primitives.Color;

abstract class Light {
    //the intensity of the ambient light
    protected  Color intensity;

    protected Light(Color intensity) {
        this.intensity = intensity;
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
