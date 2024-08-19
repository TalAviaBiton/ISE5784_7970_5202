package miniProject1;

import geometries.Triangle;
import lighting.*;
import lighting.DirectionalLight;
import lighting.PointLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.SimpleRayTracer;
import scene.Scene;

import static java.awt.Color.*;

public class Picture2 {
    /**
     * Scene for the tests
     */
    Scene scene = new Scene("Test scene").setAmbientLight(new AmbientLight(new Color(BLUE), new Double3(0.20)));
    /**
     * Camera builder for the tests with triangles
     */
    Camera.Builder cameraBuilder = Camera.getBuilder()
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setLocation(new Point(0, 0, 1000))
            .setVpDistance(1000).setVpSize(200, 200)
            .setRayTracer(new SimpleRayTracer(scene).setNumOfRays(289)).setMultithreading(true);

    boolean bvh = false;

    @Test
    public void ourPicture() {
        scene.geometries.add( //
                new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)).setBVH(bvh), //
                new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKr(new Double3(0.8))).setBVH(bvh));//

        double scalingFactor = 20.0;

        Point A = new Point(0 * scalingFactor, 2 * scalingFactor, 0);
        Point B = new Point(-1.05 * scalingFactor, 1.46 * scalingFactor, 0);
        Point C = new Point(-1.51 * scalingFactor, 0.49 * scalingFactor, 0);
        Point D = new Point(-1.34 * scalingFactor, -0.58 * scalingFactor, 0);
        Point E = new Point(-0.71 * scalingFactor, -1.33 * scalingFactor, 0);
        Point F = new Point(0.39 * scalingFactor, -1.49 * scalingFactor, 0);
        Point G = new Point(1.58 * scalingFactor, -0.91 * scalingFactor, 0);
        Point H = new Point(1.81 * scalingFactor, 0.4 * scalingFactor, 0);
        Point I = new Point(1.25 * scalingFactor, 1.54 * scalingFactor, 0);
        Point J = new Point(0.2 * scalingFactor, 0.96 * scalingFactor, 0.55 * scalingFactor);
        Point K = new Point(-0.27 * scalingFactor, 0.64 * scalingFactor, 0.54 * scalingFactor);
        Point L = new Point(-0.5 * scalingFactor, 0 * scalingFactor, 0.62 * scalingFactor);
        Point M = new Point(0.01 * scalingFactor, -0.52 * scalingFactor, 0.47 * scalingFactor);
        Point N = new Point(0.64 * scalingFactor, -0.43 * scalingFactor, 0.5 * scalingFactor);
        Point O = new Point(0.93 * scalingFactor, 0.21 * scalingFactor, 0.52 * scalingFactor);
        Point P = new Point(0.72 * scalingFactor, 0.67 * scalingFactor, 0.64 * scalingFactor);
        Point Q = new Point(0.65 * scalingFactor, 1.85 * scalingFactor, 0);
        Point R = new Point(0.17 * scalingFactor, -0.15 * scalingFactor, 0);
        Point S = new Point(-0.21 * scalingFactor, -0.3 * scalingFactor, 0.53 * scalingFactor);
        Point T = new Point(0.43 * scalingFactor, -0.1 * scalingFactor, 0);
        Point U = new Point(0.55 * scalingFactor, 0.24 * scalingFactor, 0);
        Point V = new Point(0.29 * scalingFactor, 0.51 * scalingFactor, 0);
        Point W = new Point(-0.07 * scalingFactor, 0.50 * scalingFactor, 0);
        Point Z = new Point(-0.19 * scalingFactor, 0.08 * scalingFactor, 0);
        Point A1 = new Point(-0.14 * scalingFactor, 0.3 * scalingFactor, 0);
        Point B1 = new Point(-0.17 * scalingFactor, 0.28 * scalingFactor, 0.06 * scalingFactor);

        scene.geometries.add( //

                new Triangle(D, L, E) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),

                new Triangle(L, M, E) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),

                new Triangle(E, M, F) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),


                new Triangle(M, N, F) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),


                new Triangle(N, G, F) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),


                new Triangle(N, O, G) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),


                new Triangle(O, H, G) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),

                new Triangle(O, P, H) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),


                new Triangle(P, I, H) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),


                new Triangle(J, I, P) //
                        .setMaterial(new Material().setKs(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),


                new Triangle(Q, I, J) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),


                new Triangle(A, Q, J) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),


                new Triangle(B, A, J) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),


                new Triangle(B, K, J) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),


                new Triangle(B, C, K) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),


                new Triangle(C, D, L) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),


                new Triangle(K, L, C) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),


                new Triangle(O, U, P) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),


                new Triangle(U, V, P) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),


                new Triangle(V, P, J) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),


                new Triangle(V, K, J) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),


                new Triangle(W, V, K) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),


                new Triangle(A1, W, K) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),


                new Triangle(Z, L, A1) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),


                new Triangle(A1, L, K) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),


                new Triangle(Z, S, L) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),


                new Triangle(S, M, R) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),


                new Triangle(Z, S, R) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),


                new Triangle(M, R, N) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),


                new Triangle(R, T, N) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),


                new Triangle(T, N, O) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh),


                new Triangle(T, U, O) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).setEmission(new Color(50, 20, 20)).setBVH(bvh));
        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

        scene.lights.add(new DirectionalLight(new Color(255, 0, 0), new Vector(-5, -5, -5)));

        /**
         * with soft shadows
         */
        scene.lights.add(new PointLight(new Color(100, 200, 200), new Point(60, 50, 100)) //
                .setKl(4E-5).setKq(2E-7).setSize(20));
        scene.lights.add(new PointLight(new Color(YELLOW).reduce(2), new Point(-10, 50, -10)).setKl(0.00003)
                .setKc(1.00001).setKq(0.000001).setSize(20));

        cameraBuilder
                .setImageWriter(new ImageWriter("Minip1DonutSoftShadow", 600, 600)).build().renderImage().writeToImage();

        /**
         * without soft shadows
         */
//        scene.lights.add(new PointLight(new Color(100, 200, 200), new Point(60, 50, 100)) //
//                .setKl(4E-5).setKq(2E-7).setSize(0));
//        scene.lights.add(new PointLight(new Color(YELLOW).reduce(2), new Point(-10, 50, -10)).setKl(0.00003)
//                .setKc(1.00001).setKq(0.000001).setSize(0));
//        cameraBuilder
//                .setImageWriter(new ImageWriter("Minip1DonutNoSoftShadow", 600, 600)).build().renderImage().writeToImage();

    }


}
