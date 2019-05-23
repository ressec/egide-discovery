package com.egide.discovery.javafx.scene.shape.tutorial.chapter_01;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

/**
 * Tutorial #001 on JavaFX 3D.
 * <p>
 * This tutorial demonstrates how to setup a 3D scene using the JavaFX scene graph and create a sphere shape that
 * is centered on the screen.<p> </p>
 * <p>
 * This tutorial is based on YouTube tutorials serie at: <a href="https://www.youtube.com/watch?v=mbK2xqG2glM&frags=pl%2Cwn">JavaFX 3D Tutorial #0 - Course Introduction</a>
 * </p>
 * <hr>
 * @author  <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Egide Corp.</a>
 * @version 1.0.0
 */
public class Tutorial extends Application
{
    /**
     * Application title.
     */
    private static final String APPLICATION_TITLE = "Egide Discovery on JavaFX - Tutorial on sphere shape";

    /**
     * Scene width.
     */
    private final static int SCENE_WIDTH = 800;

    /**
     * Scene height.
     */
    private final static int SCENE_HEIGHT = 600;

    /**
     * Sphere radius.
     */
    private static final float SPHERE_RADIUS = 50f;

    /**
     * Group of main objects in the scene.
     */
    private Group group = new Group();

    /**
     * Tutorial main entry point.
     *
     * @param arguments Arguments passed on the command line.
     */
    public static void main(String[] arguments)
    {
        launch(arguments);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Sphere sphere = new Sphere(SPHERE_RADIUS);

        group.getChildren().add(sphere);

        Scene scene = new Scene(group, SCENE_WIDTH, SCENE_HEIGHT);

        sphere.translateXProperty().set(SCENE_WIDTH / 2);
        sphere.translateYProperty().set(SCENE_HEIGHT / 2);

        primaryStage.setTitle(APPLICATION_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
