package primitives;

public class Material {
    Double3 Kd =Double3.ZERO;
    Double3 Ks =Double3.ZERO;
    int nShinynes=0;

        public Material setKd(Double3 KD){
            this.Kd =KD;
            return this;
        }
        public Material setKs(Double3 KS){
            this.Ks =KS;
            return this;
        }
        public Material setKd(double KD){
            this.Kd =new Double3(KD);
            return this;
        }
        public Material setKs(double KS){
            this.Ks =new Double3(KS);
            return this;
        }
        public Material setShininess(int nShinynes){
            this.nShinynes=nShinynes;
            return this;
        }

//    public class Builder {
//        Material material = new Material();
//        public Material setKd(Double3 KD){
//            material.Kd =KD;
//            return material;
//        }
//        public Material setKs(Double3 KS){
//            material.Ks =KS;
//            return material;
//        }
//        public Material setKd(double KD){
//            material.Kd =new Double3(KD);
//            return material;
//        }
//        public Material setKs(double KS){
//            material.Ks =new Double3(KS);
//            return material;
//        }
//        public Material setNShinynes(int nShinynes){
//            material.nShinynes=nShinynes;
//            return material;
//        }
//        public Material build(){
//            try {
//                return (Material) material.clone();
//            } catch (CloneNotSupportedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//    public Builder getBuilder() { return new Builder(); }
}
