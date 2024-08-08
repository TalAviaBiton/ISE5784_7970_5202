package primitives;

public class Material {
    // the Diffuse light factor of the object material type
    public Double3 kD = Double3.ZERO;

    // the specular light factor of the object material type
    public Double3 kS = Double3.ZERO;

    // the shininess factor of the object material type//
    public int nShininess = 0;

    // Attenuation coefficient of transparency
    public Double3 kT = Double3.ZERO;

    // attenuation coefficient of reflection
    public Double3 kR = Double3.ZERO;

    /**
     * set KT function - Attenuation coefficient of transparency
     *
     * @param kT transparency factor (Double3)
     * @return the material
     */
    public Material setkT(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * set KT function - Attenuation coefficient of transparency
     *
     * @param kT transparency factor (double)
     * @return the material
     */
    public Material setkT(double kT) {
        this.kT = new Double3(kT);
        return this;
    }

    /**
     * set KD function - attenuation coefficient of reflection
     *
     * @param kR reflection factor (Double3)
     * @return the material
     */
    public Material setkR(Double3 kR) {
        this.kR = kR;
        return this;
    }

    /**
     * set KD function - attenuation coefficient of reflection
     *
     * @param kR reflection factor (double)
     * @return the material
     */
    public Material setkR(double kR) {
        this.kR = new Double3(kR);
        return this;
    }

    /**
     * set KD function - the diffuse light factor
     *
     * @param kD light factor (Double3)
     * @return the material
     */
    public Material setkD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * set KD function - the diffuse light factor
     *
     * @param kD light factor (double)
     * @return the material
     */
    public Material setkD(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * set kS function - the specular light factor
     *
     * @param kS light factor (Double3)
     * @return the material
     */
    public Material setkS(Double3 kS) {
        this.kS = kS;
        return this;
    }


    /**
     * set kS function the specular light factor
     *
     * @param kS light factor (double)
     * @return the material
     */
    public Material setkS(Double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * Set the shininess factor of the material
     *
     * @param nShininess shininess factor of the material (int)
     * @return this (Material)
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
