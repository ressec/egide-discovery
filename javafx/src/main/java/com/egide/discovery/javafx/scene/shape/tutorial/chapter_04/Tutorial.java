package com.egide.discovery.javafx.scene.shape.tutorial.chapter_04;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/**
 * Tutorial #0043 on JavaFX 3D.
 * <p>
 * This tutorial completes the tutorial #003 by:
 * <br/>
 * 1. Controlling objects with the mouse.
 * </p>
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
    private static final float SCENE_WIDTH = 800;

    /**
     * Scene height.
     */
    private static final float SCENE_HEIGHT = 600;

    /**
     * Sphere radius.
     */
    private static final float SPHERE_RADIUS = 50f;

    /**
     * Near clipping.
     */
    private static final int CLIPPING_NEAR = 1;

    /**
     * Far clipping.
     */
    private static final int CLIPPING_FAR = 2000;

    private double anchorX;

    private double anchorY;

    private double anchorAngleX = 0;

    private double anchorAngleY = 0;

    private final DoubleProperty angleX = new SimpleDoubleProperty(0);

    private final DoubleProperty angleY = new SimpleDoubleProperty(0);


    /**
     * Group of main objects in the scene.
     */
    private Group group = new Group();

    /**
     * Sphere shape.
     */
    private Sphere sphere = null;

    /**
     * The perspective camera.
     */
    private Camera camera = new PerspectiveCamera(true);

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
        Box box = new Box(100, 20, 80);

        group.getChildren().add(box);

        Scene scene = new Scene(group, SCENE_WIDTH, SCENE_HEIGHT);

        scene.setFill(Color.BLACK);
        scene.setCamera(camera);

        // Move the camera instead of the sphere object.
        camera.translateXProperty().set(0);
        camera.translateYProperty().set(0);
        camera.translateZProperty().set(-500);

        // Set the clipping area of the camera.
        camera.setNearClip(CLIPPING_NEAR);
        camera.setFarClip(CLIPPING_FAR);

        initializeMouseControl(group, scene, primaryStage);

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> onKeyPressed(event));
        primaryStage.setTitle(APPLICATION_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeMouseControl(Group group, Scene scene, Stage stage)
    {
        Rotate xRotation = new Rotate(0, Rotate.X_AXIS);
        Rotate yRotation = new Rotate(0, Rotate.Y_AXIS);

        group.getTransforms().addAll(xRotation, yRotation);

        xRotation.angleProperty().bind(angleX);
        yRotation.angleProperty().bind(angleY);

        scene.setOnMousePressed(event ->
            {
                anchorX = event.getSceneX();
                anchorY = event.getSceneY();
                anchorAngleX = angleX.get();
                anchorAngleY = angleY.get();
            }
        );

        scene.setOnMouseDragged(event ->
            {
                angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
                angleY.set(anchorAngleY + (anchorX - event.getSceneX()));
            }
        );

        stage.addEventHandler(ScrollEvent.SCROLL, event -> {
            double delta = event.getDeltaY();
            group.translateZProperty().set(group.getTranslateZ() + delta);
        });
    }

    /**
     * Invoked when a key has been pressed on the primary stage.
     *
     * @param event Key event.
     */
    private void onKeyPressed(KeyEvent event)
    {
        switch (event.getCode())
        {
            case RIGHT:
                camera.translateXProperty().set(camera.translateXProperty().get() - 10);
                break;

            case LEFT:
                camera.translateXProperty().set(camera.translateXProperty().get() + 10);
                break;

            case DOWN:
                camera.translateYProperty().set(camera.translateYProperty().get() - 10);
                break;

            case UP:
                camera.translateYProperty().set(camera.translateYProperty().get() + 10);
                break;

            case SUBTRACT:
                camera.translateZProperty().set(camera.translateZProperty().get() - 50);
                break;

            case ADD:
                camera.translateZProperty().set(camera.translateZProperty().get() + 50);
                break;

            case ESCAPE:
                Platform.exit();
                System.exit(0);
                break;

            default:
                System.out.println("Unexpected value: " + event.getCode());
        }
    }
}
