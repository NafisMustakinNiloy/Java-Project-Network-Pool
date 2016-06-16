/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkpool;

import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.*;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

/**
 *
 * @author Nafis Mustakin
 */
public class SphereBalls{
    
    Sphere ball = new Sphere(13.5, 256);
    private double veloctiy;
    private double velX;
    private double velY;
    private double angle;
    private double time; 
    private double fricX;   //friction X component
    private double fricY;   ///Y component
    private double friction = 5; ////base value
    private int BallID;
    
    
    SphereBalls(Group root, double x, double y, String imageName)
    {
        
        
        Image ballImage = new Image(imageName);
        
        PhongMaterial material = new PhongMaterial();
        material.setSpecularColor(WHITE);
        material.setDiffuseMap(ballImage);
        material.setSpecularPower(30);
        ball.setMaterial(material);
        
        ball.setLayoutX(x);
        ball.setLayoutY(y);
        ball.setRadius(13.5);
        
        root.getChildren().addAll(ball);
        
        DropShadow shadow = new DropShadow();
        shadow.setSpread(64);
        shadow.setOffsetX(13.5);
        shadow.setOffsetY(13.5);
        ball.setEffect(shadow);
        
        velX = 0;
        velY = 0;
        angle = 0;
        time = 0;
        
        friction = 0;
         
    }
    
    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setVx(double vx) {
        this.velX = vx;
    }

    public void setVy(double vy) {
        this.velX = vy;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getAngle() {
        return angle;
    }

    public double getTime() {
        return time;
    }

    public double getVx() {
        return velX;
    }

    public double getVy() {
        return velY;
    }

    public void setfricX(double fx) {
        this.fricX = fx;
    }

    public double getfricX() {
        return fricX;
    }

    public void setfricY(double fy) {
        this.fricY = fy;
    }

    public double getfricY() {
        return fricY;
    }

    public void setA(double a) {
        this.friction = a;
    }

    public double getA() {
        return friction;
    }

    public void setV(double v) {
        this.veloctiy = v;
    }

    public double getV() {
        return veloctiy;
    }

    public void setBallId(int ballid) {
        this.BallID = ballid;
    }

    public int getBallid() {
        return BallID;
    }
    
    
}
