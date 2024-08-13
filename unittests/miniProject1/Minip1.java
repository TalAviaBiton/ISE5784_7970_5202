package miniProject1;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.SimpleRayTracer;
import scene.Scene;

import static java.awt.Color.*;

class Minip1 {


    /**  scene builder for the picture*/
    Scene scene = new Scene.SceneBuilder("Test scene").build()
        .setAmbientLight(new AmbientLight(new Color(WHITE), 0.15))
        .setBackground(new Color(100, 300, 300));

    /** Camera builder of the tests */
    Camera.Builder camera     = Camera.getBuilder()
            .setDirection(new Vector(0,0,-1), new Vector(0,1,0))
            .setLocation(new Point(0, 0, 1000)).setVpDistance(220)
            .setVpSize(200, 200)
            .setRayTracer(new SimpleRayTracer(scene).setNumOfRays(289));

    @Test
    public void ourPicture() {

        //*********** geometries **************

        scene.geometries.add(
                new Plane(new Point(0, -600, 0), new Point(1, -600, 0), new Point(0, -600, 1)) //white
                        .setEmission(new Color(230, 400, 230)).setMaterial(new Material().setkD(0.3).setkS(0.8).setShininess(30)),
                new Plane(new Point(-300, -600, -100), new Point(-290, -600, -600), new Point(-300, -500, -100)) //pink
                        .setEmission(new Color(red).reduce(3)).setMaterial(new Material().setkD(0.3).setkS(0.8).setShininess(30)),

//                new Triangle (new Point (-150,-160,0),new Point (230,-160,0),new Point(40,-160,300))
//                        .setEmission(new Color(123, 63,0)).setMaterial(new Material().setkR(1).setkD(1).setkD(0.2).setShininess(10)),//?
//                new Triangle (new Point (-150,-160,0),new Point (230,-160,0),new Point(40,-400,110))
//                        .setEmission(new Color(123, 63,0)).setMaterial(new Material().setkD(0.3).setkS(0.8).setShininess(30)),
//                new Triangle (new Point (-150,-160,0),new Point (40,-400,110),new Point(40,-160,300))
//                        .setEmission(new Color(123, 63,0)).setMaterial(new Material().setkD(0.3).setkS(0.8).setShininess(30)),
//                new Triangle (new Point (40,-400,110),new Point (230,-160,0),new Point(40,-160,300))
//                        .setEmission(new Color(123, 63,0)).setMaterial(new Material().setkD(0.3).setkS(0.8).setShininess(30)),

                //first floor-
                new Sphere(new Point(-80,-100,0),60).setEmission(new Color(0,30,200))  .setMaterial(new Material().setkD(0.4).setkS(0.8).setShininess(60)),//blue
                new Sphere(new Point(160,-100,0),60).setEmission(new Color(300,0,200)) .setMaterial(new Material().setkD(0.4).setkS(0.8).setShininess(60)),//pink
                new Sphere(new Point(40,-100,0),60).setEmission(new Color(200,0,30)) .setMaterial(new Material().setkD(0.4).setkS(0.8).setShininess(60)),//red

                new Sphere(new Point(-20,-100,110),60).setEmission(new Color(200,200,200)).setMaterial(new Material().setkD(0.4).setkS(0.8).setShininess(60)), //gray
                new Sphere(new Point(100,-100,110),60).setEmission(new Color(100,12,200)).setMaterial(new Material().setkD(0.4).setkS(0.8).setShininess(60)),//purple

                new Sphere(new Point(40,-100,220),60).setEmission(new Color(0,160,0)).setMaterial(new Material().setkD(0.4).setkS(0.8).setShininess(60)),//green

                //second floor-
                new Sphere(new Point(-20,0,55),60).setEmission(new Color(255,165,0)).setMaterial(new Material().setkD(0.4).setkS(0.8).setShininess(60)),//yellow
                new Sphere(new Point(100,0,55),60).setEmission(new Color(255,170,180)).setMaterial(new Material().setkD(0.4).setkS(0.8).setShininess(60)),//pink
                new Sphere(new Point(40,0,165),60).setEmission(new Color(255,100,0)).setMaterial(new Material().setkD(0.4).setkS(0.8).setShininess(60)),//orange

                //third floor-
                new Sphere(new Point(40,100,110),60).setEmission(new Color(255,30,0)).setMaterial(new Material().setkD(0.4).setkS(0.8).setShininess(40))//red
        );

        scene.geometries.add(new Sphere( new Point(30,-190,50),10).
                setEmission(new Color(0,30,200))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30)));


        //************** lights **************

        scene.lights.add(new DirectionalLight(new Color(50,50,50),new Vector(0.5,0.5,-0.5))); // from the top

        /**
         * without soft shadows
         */

        scene.lights.add(new SpotLight(new Color(255, 255, 255),new Point(-300,300,400),new Vector(40,-40,-20)).setSize(0)
                .setkL(1E-5).setkQ(1.5E-7));

        scene.lights.add(new SpotLight(new Color(255, 255, 255),new Point(-20,0,250),new Vector(0,0,-1)).setSize(0)
                .setkL(1E-5).setkQ(1.5E-7));

        scene.lights.add(new PointLight(new Color(180,180,180),new Point(-300,-300,250)).setSize(0));

        camera.setImageWriter(new ImageWriter("Minip1 without soft shadow",500,500))
                .build()
                .renderImage() //
                .writeToImage();

        /**
         * with soft shadows
         */
//        scene.lights.add(new SpotLight(new Color(255, 255, 255),new Point(-300,300,400),new Vector(40,-40,-20)).setSize(13)
//                .setkL(1E-5).setkQ(1.5E-7));
//
//        scene.lights.add(new SpotLight(new Color(255, 255, 255),new Point(-20,0,250),new Vector(0,0,-1)).setSize(30)
//                .setkL(1E-5).setkQ(1.5E-7));
//
//        scene.lights.add(new PointLight(new Color(180,180,180),new Point(-300,-300,250)).setSize(30));
//
//
//        camera.setImageWriter(new ImageWriter("Minip1 with soft shadow",500,500))
//                .build()
//                .renderImage() //
//                .writeToImage();

    }
}


