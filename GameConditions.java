/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
///the pockets are not well defined//edit

//probably don't need the boolean turn since there is no need for networking, keeping it at the moment
package networkpool;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

/**
 *
 * @author Nafis Mustakin
 */
public class GameConditions {
   
    
    Group root;
    
    private SphereBalls [] allBalls;
    public CueStick cue;
    
    ImageView player1;
    ImageView player2;
    ImageView gameover;
    
    Image player1Off,player1On, player2Off, player2On , player1Won, player2Won;
    
    
    double initialVel;
    
    boolean flag;
    public boolean gameEndingCondition;
    boolean foulCommitted; 
    int turn = 0;
    boolean bound = true;
    boolean velocitySet = false;
    
    public int PlayerBallType[] = new int[2]; 
    ///ballID notation, solids = 0; stripes = 2; cue = 2; black = 8;
    int solidNo, stripeNo;
    
    PottedBalls pottedBalls; 
    
    public GameConditions(Group root)
    {
        cue = new CueStick(root);
        pottedBalls = new PottedBalls(root);
        this.root = root;
        
        allBalls = new SphereBalls[16];
        
        allBalls[0] = new SphereBalls(root,820+(13.5+1)*2*1.732,358,"images/1.png");
        allBalls[1] = new SphereBalls(root,820+(13.5+1)*1.732,358+13.5*3+3,"images/2.png");
        allBalls[2] = new SphereBalls(root,820+(13.5+1)*2*1.732,358-(13.5+1)*2,"images/3.png");
        allBalls[3] = new SphereBalls(root,820-(13.5+1)*2*1.732,358,"images/4.png");
        allBalls[4] = new SphereBalls(root,820+(13.5+1)*1.732,358-13.5-1,"images/5.png");
        allBalls[5] = new SphereBalls(root,820+(13.5+1)*2*1.732,358+(13.5+1)*2,"images/6.png");
        allBalls[6] = new SphereBalls(root,820-(13.5+1)*1.732,358-13.5-1,"images/7.png");
        
        allBalls[7] = new SphereBalls(root,820,358,"images/8.png"); 
        
        allBalls[8] = new SphereBalls(root,820,358-(13.5+1)*2,"images/9.png");
        allBalls[9] = new SphereBalls(root,820+(13.5+1)*2*1.732,358-(13.5+1)*4,"images/10.png");
        allBalls[10] = new SphereBalls(root,820+(13.5+1)*2*1.732,358+(13.5+1)*4,"images/11.png");
        allBalls[11] = new SphereBalls(root,820+(13.5+1)*1.732,358-(13.5+1)*3,"images/12.png");
        allBalls[12] = new SphereBalls(root,820,358+13.5*2+1,"images/13.png" );
        allBalls[13] = new SphereBalls(root,820-(13.5+1)*1.732,358+13.5+1,"images/14.png");
        allBalls[14] = new SphereBalls(root,820+(13.5+1)*1.732,358+13.5+1,"images/15.png");
        
        allBalls[15] = new SphereBalls(root,310,359,"images/cueball.png");
        
        for(int i = 0; i<7; i++) allBalls[i].setBallId(0);
        
        allBalls[7].setBallId(8);
        
        for(int i = 8; i<15;i++) allBalls[i].setBallId(1);
        
        allBalls[15].setBallId(2);
        
        player1Off = new Image("images/player1Off.png");
        player1On = new Image("images/player1On.png");
        player2Off = new Image("images/player2Off.png");
        player2On = new Image("images/player2On.png");
        player1Won = new Image("images/player1Won.png");
        player2Won = new Image("images/player2Won.png");
        
        player1 = new ImageView();
        
        player1.setImage(player1On);
        player1.setLayoutX(150);
        player1.setLayoutY(650);
        
        player2 = new ImageView();
        player2.setImage(player2Off);
        
        player2.setLayoutX(675);
        player2.setLayoutY(650);
        
        gameover = new ImageView();
        gameover.setLayoutX(150);
        gameover.setLayoutY(60);
        
        
        root.getChildren().addAll(player1,player2);
        
       
        
    }
    
    public int thisTurn() {return turn;}
    public void switchTurn() 
    {
        if(turn == 0)
        {
            turn =1;
            player1.setImage(player1Off);
            player2.setImage(player2On);
        }
        else{
            turn = 0;
            player2.setImage(player2Off);
            player1.setImage(player1On);
        }
    }
    
    
    public void setValue(double value) {cue.setVel(value);}//OK
    
    public void setAngle(double angle){//OK
        cue.setAngle(angle);
        cue.RotateCue();
    }
    
    public void setLayout(double x, double y){
        allBalls[15].ball.setLayoutX(x);
        allBalls[15].ball.setLayoutY(y);
        cue.moveCue(getCueBallX(), getCueBallY());
    }
    
    public double getInitialVel() {return initialVel;}
    public void setInitialVel( double inV) {initialVel = inV;}
    
    public double getCueBallX(){return allBalls[15].ball.getLayoutX();}
    public double getCueBallY(){return allBalls[15].ball.getLayoutY();}
    
    
    boolean Bounded()
    {
        if (getCueBallX() > 160 && getCueBallY() > 140 && getCueBallX() < 1038 && getCueBallY() <548) {
                bound = true;
                return bound;
            }
            else {
                bound=false;
                if (getCueBallX()-13.5 <= 110) {
                    allBalls[15].ball.setLayoutX(112);

                }
                if (getCueBallY()-13.5 <= 160) {
                    allBalls[15].ball.setLayoutY(162);
                }
                if (getCueBallX()+13.5 >= 890) {
                    allBalls[15].ball.setLayoutX(888);
                }

                if (getCueBallY() +13.5>= 540) {
                    allBalls[15].ball.setLayoutY(538);
                }
                    return bound;
            }
        
    }
    
    public boolean allStopped()
    {
        for(int i = 0; i<16; i++)
        {
            if(allBalls[i].getV() > 0) return false;
        }
        return true;
    }
    
    public void Cue (Scene scene)
    {
        cue.setCuePos(getCueBallX(), getCueBallY());
        //System.out.println(getCueBallX() + " " + getCueBallY());
        cue.swingCue(getCueBallX(), getCueBallY());
    }
    
    
    public void setVelofCueBall()
    {
       
            
            if((cue.getInitialVel() > 0 && !velocitySet))
            {
                foulCommitted = false; 
                double angle = (180+cue.getAngle())%360;
                //System.out.println(angle);
                allBalls[15].setAngle(Math.toRadians(angle));
                allBalls[15].setV(cue.InitialVel());
               
                
                allBalls[15].setVx(allBalls[15].getV()*Math.cos(allBalls[15].getAngle()));
                allBalls[15].setVy(allBalls[15].getV()*Math.sin(allBalls[15].getAngle()));
                
                allBalls[15].setfricX(allBalls[15].getA()*Math.cos(allBalls[15].getAngle()));
                allBalls[15].setfricY(allBalls[15].getV()*Math.sin(allBalls[15].getAngle()));
                
                velocitySet =true;
                
                solidNo = pottedBalls.solidNo;
                stripeNo = pottedBalls.stripeNo;
                
            }   
        
       
        
            if ((getInitialVel()>0 && !velocitySet)) {
                foulCommitted = false;
                double angle = (cue.getAngle() + 180) % 360;
                allBalls[15].setAngle(Math.toRadians(angle));
                allBalls[15].setV(getInitialVel());
                
                allBalls[15].setVx((allBalls[15].getV() * Math.cos((allBalls[15].getAngle()))));
                allBalls[15].setVy((allBalls[15].getV() * Math.sin((allBalls[15].getAngle()))));
                allBalls[15].setfricX((allBalls[15].getA() * Math.cos((allBalls[15].getAngle()))));
                allBalls[15].setfricY(allBalls[15].getA() * Math.sin((allBalls[15].getAngle())));
                velocitySet = true;
               
                //setInitialVel(0);
            }
            
        
        //System.out.println(allBalls[15].getV()+ " " + allBalls[15].getAngle());
       // System.out.println(velocitySet);
    }
    
    public void controlBallSpeed()
    {
        if (velocitySet) {
            for (int i = 0; i < 16; i++) {

                root.getChildren().remove(cue.getCue());

                allBalls[i].setV(Math.sqrt(Math.pow(allBalls[i].getVx(), 2) + Math.pow(allBalls[i].getVy(), 2)));

                if (allBalls[i].getV() > 0.07) {
                    allBalls[i].setVx(allBalls[i].getVx() - (allBalls[i].getfricX()* allBalls[i].getTime()));
                    allBalls[i].setVy(allBalls[i].getVy() - (allBalls[i].getfricY()* allBalls[i].getTime()));

                    allBalls[i].setTime(allBalls[i].getTime() + .00005);

                    allBalls[i].ball.setLayoutX(allBalls[i].ball.getLayoutX() + allBalls[i].getVx());
                    allBalls[i].ball.setLayoutY(allBalls[i].ball.getLayoutY() + allBalls[i].getVy());
                }

                if (allBalls[i].getV() <= 0.07) {
                    allBalls[i].setTime(0);
                    allBalls[i].setAngle(0);
                    allBalls[i].setVx(0);
                    allBalls[i].setVy(0);
                }
            }


            if (allStopped()) {
                velocitySet = false;
                flag = true;
                ////cue.setInitialVel(0);

                if(PlayerBallType[turn]==0 && pottedBalls.solidNo==solidNo){
                    switchTurn();

                }
                else if(PlayerBallType[turn]==1 && pottedBalls.stripeNo==stripeNo){
                    switchTurn();
                }

            }
        }
    }
    
    
    public void UpdateAngle(SphereBalls ball) {
        double t = Math.toDegrees(Math.atan2(ball.getVy(), ball.getVx()));
        if (t < 0) {
            t += 360;
        }
        ball.setAngle(Math.toRadians(t));
    }
    
    
    public void detectWallCollision()
    {
        for(int i = 0; i<16; i++)
        {
            //top left side wall
            if (allBalls[i].ball.getLayoutX() >= 197 && allBalls[i].ball.getLayoutX() <= 574) {
                if ((allBalls[i].ball.getLayoutY() -14 <= 141)) {
                    allBalls[i].setVy(-(allBalls[i].getVy())/1.1);
                    allBalls[i].setfricY(-(allBalls[i].getfricY()));
                    UpdateAngle(allBalls[i]);
                }
            }

            //top right side wall
            if (allBalls[i].ball.getLayoutX() >= 624 && allBalls[i].ball.getLayoutX() <= 1037) {
                if ((allBalls[i].ball.getLayoutY() + 14 <= 141)) {
                    allBalls[i].setVy(-(allBalls[i].getVy())/1.1);
                    allBalls[i].setfricY(-(allBalls[i].getfricY()));
                    UpdateAngle(allBalls[i]);
                }
            }

            //bottom left side wall
            if (allBalls[i].ball.getLayoutX() >= 197 && allBalls[i].ball.getLayoutX() <= 574) {
                if ((allBalls[i].ball.getLayoutY() + 14 <= 550)) {
                    allBalls[i].setVy(-(allBalls[i].getVy())/1.1);
                    allBalls[i].setfricY(-(allBalls[i].getfricY()));
                    UpdateAngle(allBalls[i]);
                }
            }

            //bottom right side wall
            if (allBalls[i].ball.getLayoutX() >= 624 && allBalls[i].ball.getLayoutX() <= 1037) {
                if ((allBalls[i].ball.getLayoutY() + 14 <= 550)) {
                    allBalls[i].setVy(-(allBalls[i].getVy())/1.1);
                    allBalls[i].setfricY(-(allBalls[i].getfricY()));
                    UpdateAngle(allBalls[i]);
                }
            }

           
            //left side wall
            if (allBalls[i].ball.getLayoutY() > 141 && allBalls[i].ball.getLayoutY() < 543) {
                if ((allBalls[i].ball.getLayoutX() -14) <=162) {
                    allBalls[i].setVx(-(allBalls[i].getVx())/1.1);
                    allBalls[i].setfricX(-(allBalls[i].getfricX()));
                    UpdateAngle(allBalls[i]);
                }
            }

           
            //right side wall
            if (allBalls[i].ball.getLayoutY() > 141 && allBalls[i].ball.getLayoutY() <= 525) {
                if ((allBalls[i].ball.getLayoutX() +14) >= 1037) {
                    allBalls[i].setVx(-(allBalls[i].getVx()));
                    allBalls[i].setfricX(-(allBalls[i].getfricX()));
                    UpdateAngle(allBalls[i]);
                }
            }
        }
    }
    
    /*

    public void detectEdgeOfPockets() {

        for (int i = 0; i < 16; i++) {
            ///1///
            if (allBalls[i].ball.getLayoutX() >= 98 && allBalls[i].ball.getLayoutX() < 125) {
                if (isTouch(15, -20, 1125, allBalls[i].ball.getLayoutX(), allBalls[i].ball.getLayoutY())) {
                    handleCollisionsInWallPlus(90 - 36.86, allBalls[i]);
                }
            }

            //2//
            if (allBalls[i].ball.getLayoutX() > 475 && allBalls[i].ball.getLayoutY() > 135 && allBalls[i].ball.getLayoutY() < 162) {
                if (isTouch(-15, -10, 8625, allBalls[i].ball.getLayoutX(), allBalls[i].ball.getLayoutY())) {
                    // handleCollisionsInWall(303.6900675-20,ball[i]);
                    handleCollisionsInWallMinus(33.7, allBalls[i]);

                }
            }

            //3//
            if (allBalls[i].ball.getLayoutX() < 525 && allBalls[i].ball.getLayoutY() > 135 && allBalls[i].ball.getLayoutY() < 162) {
                if (isTouch(15, -10, -6375, allBalls[i].ball.getLayoutX(), allBalls[i].ball.getLayoutY())) {
                    handleCollisionsInWallPlus(33.7, allBalls[i]);
                }
            }

            //4//
            if (allBalls[i].ball.getLayoutX() > 875 && allBalls[i].ball.getLayoutX() < 902) {
                if (isTouch(-15, -20, 16125, allBalls[i].ball.getLayoutX(), allBalls[i].ball.getLayoutY())) {
                    handleCollisionsInWallMinus(90 - 36.86, allBalls[i]);
                }
            }

            //5//
            if (allBalls[i].ball.getLayoutX() >= 98 && allBalls[i].ball.getLayoutX() < 125) {
                if (isTouch(15, 20, -12875, allBalls[i].ball.getLayoutX(), allBalls[i].ball.getLayoutY())) {
                    handleCollisionsInWallMinus(90 - 36.86, allBalls[i]);
                }
            }

            //6//
            if (allBalls[i].ball.getLayoutX() > 474 && allBalls[i].ball.getLayoutY() > 540 && allBalls[i].ball.getLayoutY() <= 565) {
                if (isTouch(15, -10, -1625, allBalls[i].ball.getLayoutX(), allBalls[i].ball.getLayoutY())) {
                    handleCollisionsInWallPlus(33.7, allBalls[i]);
                }
            }

            //7//
            if (allBalls[i].ball.getLayoutX() < 525 && allBalls[i].ball.getLayoutY() < 565 && allBalls[i].ball.getLayoutY() >= 540) {
                if (isTouch(15, 10, -13375, allBalls[i].ball.getLayoutX(), allBalls[i].ball.getLayoutY())) {
                    handleCollisionsInWallMinus(33.7, allBalls[i]);
                }
            }
            //8//
            if (allBalls[i].ball.getLayoutX() > 875 && allBalls[i].ball.getLayoutX() < 902) {
                if (isTouch(15, -20, -2125, ball[i].sphere.getLayoutX(), ball[i].sphere.getLayoutY())) {
                    handleCollisionsInWallPlus(90 - 36.86, ball[i]);
                }
            }

            //9//
            if (ball[i].sphere.getLayoutY() > 149 && ball[i].sphere.getLayoutY() < 175) {
                if (isTouch(20, -15, 625, ball[i].sphere.getLayoutX(), ball[i].sphere.getLayoutY())) {
                    handleCollisionsInWallMinus1(90 - 36.86, ball[i]);
                }
            }

            //10//
            if (ball[i].sphere.getLayoutY() > 525 && ball[i].sphere.getLayoutY() < 551) {
                if (isTouch(20, 15, -9875, ball[i].sphere.getLayoutX(), ball[i].sphere.getLayoutY())) {
                    handleCollisionsInWallPlus1(90 - 36, ball[i]);
                }
            }

            //11//
            if (ball[i].sphere.getLayoutY() > 149 && ball[i].sphere.getLayoutY() < 175) {
                if (isTouch(20, 15, -20625, ball[i].sphere.getLayoutX(), ball[i].sphere.getLayoutY())) {
                    handleCollisionsInWallPlus1(90 - 36, ball[i]);
                }
            }

            //12//
            if (ball[i].sphere.getLayoutY() > 525 && ball[i].sphere.getLayoutY() < 551) {
                if (isTouch(20, -15, -10125, ball[i].sphere.getLayoutX(), ball[i].sphere.getLayoutY())) {
                    handleCollisionsInWallMinus1(90 - 36.86, ball[i]);
                }
            }
        }
    }

    

    boolean isTouch(double a, double b, double c, double x, double y) {

        double temp = Math.abs(a * x + b * y + c);
        double temp2 = Math.sqrt(a * a + b * b);
        double distance = temp / temp2;

        if (distance <= 10) {
            return true;
        }
        return false;
    }

    
    public void handleCollisionsInWallMinus(double angle, DrawBall a) {

        UpdateAngle(a);
        UpdateAngle(a, -angle);

        a.setVx((a.getV() * Math.cos((a.getAngle()))));
        a.setVy((a.getV() * Math.sin((a.getAngle()))));
        a.setAx(a.getA() * Math.cos((a.getAngle())));
        a.setAy(a.getA() * Math.sin((a.getAngle())));

        a.setVx(-a.getVx());
        a.setAx(-a.getAx());
        UpdateAngle(a);

        UpdateAngle(a, +angle);

        a.setVx((a.getV() * Math.cos((a.getAngle()))));
        a.setVy((a.getV() * Math.sin((a.getAngle()))));
        a.setAx(a.getA() * Math.cos((a.getAngle())));
        a.setAy(a.getA() * Math.sin((a.getAngle())));
    }

    public void handleCollisionsInWallPlus(double angle, DrawBall a) {

        UpdateAngle(a);
        UpdateAngle(a, angle);
        a.setVx((a.getV() * Math.cos((a.getAngle()))));
        a.setVy((a.getV() * Math.sin((a.getAngle()))));
        a.setAx(a.getA() * Math.cos((a.getAngle())));
        a.setAy(a.getA() * Math.sin((a.getAngle())));

        a.setVx(-a.getVx());
        a.setAx(-a.getAx());
        UpdateAngle(a);
        UpdateAngle(a, -angle);

        a.setVx((a.getV() * Math.cos((a.getAngle()))));
        a.setVy((a.getV() * Math.sin((a.getAngle()))));
        a.setAx(a.getA() * Math.cos((a.getAngle())));
        a.setAy(a.getA() * Math.sin((a.getAngle())));
    }

    
    
    public void handleCollisionsInWallMinus1(double angle, DrawBall a) {

        UpdateAngle(a);
        UpdateAngle(a, -angle);
        a.setVx((a.getV() * Math.cos((a.getAngle()))));
        a.setVy((a.getV() * Math.sin((a.getAngle()))));
        a.setAx(a.getA() * Math.cos((a.getAngle())));
        a.setAy(a.getA() * Math.sin((a.getAngle())));

        a.setVy(-a.getVy());
        a.setAy(-a.getAy());
        UpdateAngle(a);

        UpdateAngle(a, +angle);

        a.setVx((a.getV() * Math.cos((a.getAngle()))));
        a.setVy((a.getV() * Math.sin((a.getAngle()))));
        a.setAx(a.getA() * Math.cos((a.getAngle())));
        a.setAy(a.getA() * Math.sin((a.getAngle())));
    }


    public void handleCollisionsInWallPlus1(double angle, DrawBall a) {

        UpdateAngle(a);

        UpdateAngle(a, angle);


        a.setVx((a.getV() * Math.cos((a.getAngle()))));
        a.setVy((a.getV() * Math.sin((a.getAngle()))));
        a.setAx(a.getA() * Math.cos((a.getAngle())));
        a.setAy(a.getA() * Math.sin((a.getAngle())));

        a.setVy(-a.getVy());
        a.setAy(-a.getAy());
        UpdateAngle(a);


        UpdateAngle(a, -angle);
        a.setVx((a.getV() * Math.cos((a.getAngle()))));
        a.setVy((a.getV() * Math.sin((a.getAngle()))));
        a.setAx(a.getA() * Math.cos((a.getAngle())));
        a.setAy(a.getA() * Math.sin((a.getAngle())));
    }
    */
    public void BalltoBallCollision()
    {
        double xDist, yDist;
        for (int i = 0; i < 16; i++) {
            SphereBalls A = allBalls[i];
            for (int j = i + 1; j < 16; j++) {
                SphereBalls B = allBalls[j];
                xDist = A.ball.getLayoutX() - B.ball.getLayoutX();
                yDist = A.ball.getLayoutY() - B.ball.getLayoutY();
                double distSquared = xDist * xDist + yDist * yDist;
                //Check the squared distances instead of the the distances, same result, but avoids a square root.
                if (distSquared <= 4*13.5*13.5) {
                    double xVelocity = B.getVx() - A.getVx();
                    double yVelocity = B.getVy() - A.getVy();
                    double dotProduct = xDist * xVelocity + yDist * yVelocity;
                    //Neat vector maths, used for checking if the objects moves towards one another.
                    if (dotProduct > 0) {
                        double collisionScale = dotProduct / distSquared;
                        double xCollision = xDist * collisionScale;
                        double yCollision = yDist * collisionScale;
                        //The Collision vector is the speed difference projected on the Dist vector,
                        //thus it is the component of the speed difference needed for the collision.
                        //double combinedMass = 10;                 // A.mass + B.mass;
                        double collisionWeightA = 1;                //2 * B.mass / combinedMass;
                        double collisionWeightB = 1;                  // 2 * A.mass / combinedMass;

                        A.setVx((collisionWeightA * xCollision) + A.getVx());
                        A.setVy(collisionWeightA * yCollision + A.getVy());
                        B.setVx(B.getVx() - collisionWeightB * xCollision);
                        B.setVy(B.getVy() - collisionWeightB * yCollision);

                        A.setAngle(Math.toDegrees(Math.atan2(A.getVy(), A.getVx())));
                        B.setAngle(Math.toDegrees(Math.atan2(B.getVy(), B.getVx())));

                        if (A.getAngle() < 0) {
                            A.setAngle((A.getAngle() + 360));
                        }
                        if (B.getAngle() < 0) {
                            B.setAngle((B.getAngle() + 360));
                        }


                        A.setfricX(A.getA() * Math.cos(Math.toRadians(A.getAngle())));
                        A.setfricY(A.getA() * Math.sin(Math.toRadians(A.getAngle())));

                        B.setfricX(B.getA() * Math.cos(Math.toRadians(B.getAngle())));
                        B.setfricY(B.getA() * Math.sin(Math.toRadians(B.getAngle())));

                    }
                }
            }
        }
    }
    
    public boolean detectPocket(SphereBalls ball)
    {
        if(Math.sqrt((158-ball.ball.getLayoutX())*(158-ball.ball.getLayoutX())+(137-ball.ball.getLayoutY())*(137-ball.ball.getLayoutX())) <= 13.5+ 26) return true;
        if(Math.sqrt((158-ball.ball.getLayoutX())*(158-ball.ball.getLayoutX())+(581-ball.ball.getLayoutY())*(581-ball.ball.getLayoutX())) <= 13.5+ 26) return true;
        if(Math.sqrt((600-ball.ball.getLayoutX())*(600-ball.ball.getLayoutX())+(126-ball.ball.getLayoutY())*(126-ball.ball.getLayoutX())) <= 13.5+ 20) return true;
        if(Math.sqrt((1041-ball.ball.getLayoutX())*(1041-ball.ball.getLayoutX())+(137-ball.ball.getLayoutY())*(137-ball.ball.getLayoutX())) <= 13.5+ 26) return true;
        if(Math.sqrt((1041-ball.ball.getLayoutX())*(1040-ball.ball.getLayoutX())+(581-ball.ball.getLayoutY())*(581-ball.ball.getLayoutX())) <= 13.5+ 26) return true;
        if(Math.sqrt((600-ball.ball.getLayoutX())*(600-ball.ball.getLayoutX())+(594-ball.ball.getLayoutY())*(594-ball.ball.getLayoutX())) <= 13.5+ 26) return true;
        else return false;
        
    }
    public void Pocket()
    {
        for(int i = 0; i<16; i++)
        {
            if(detectPocket(allBalls[i]))
            {
                if(pottedBalls.solidNo == 0 && pottedBalls.stripeNo == 0){
                    if(i<7) PlayerBallType[turn] = 0; //for solids
                    else if(i>7 && i<15)PlayerBallType[turn] = 1;
                }
                if(i<7) 
                {
                    pottedBalls.potSolidBall(allBalls[i]);
                    if(PlayerBallType[turn] == 1) 
                    {
                        switchTurn();
                    }
                }
                
                else if(i ==15)
                {
                    allBalls[15].setVx(0);
                    allBalls[15].setVy(0);
                    allBalls[15].ball.setLayoutX(271);
                    allBalls[15].ball.setLayoutY(359);
                    
                }
                
                else if(i ==7)
                {
                    if(PlayerBallType[turn] ==0 && pottedBalls.solidNo<7)
                    {
                        pottedBalls.potSolidBall(allBalls[i]);
                        if(turn ==0)
                        {
                            gameover.setImage(player2Won);
                        }
                        else
                        {
                            gameover.setImage(player1Won);
                            
                        }
                        gameEndingCondition = true;
                    }
                    
                    if(PlayerBallType[turn] ==1 && pottedBalls.stripeNo<7)
                    {
                        pottedBalls.potStripeBall(allBalls[i]);
                        if(turn ==0)
                        {
                            gameover.setImage(player2Won);
                        }
                        else
                        {
                            gameover.setImage(player1Won);
                            
                        }
                        gameEndingCondition = true;
                    }
                    
                    if(PlayerBallType[turn] ==0 && pottedBalls.solidNo ==7)
                    {
                        pottedBalls.potSolidBall(allBalls[i]);
                        if(turn ==0)
                        {
                            gameover.setImage(player1Won);
                        }
                        else
                        {
                            gameover.setImage(player2Won);
                            
                        }
                        gameEndingCondition = true;
                    }
                    
                    if(PlayerBallType[turn] ==0 && pottedBalls.stripeNo==7)
                    {
                        pottedBalls.potStripeBall(allBalls[i]);
                        if(turn ==0)
                        {
                            gameover.setImage(player1Won);
                        }
                        else
                        {
                            gameover.setImage(player2Won);
                            
                        }
                        gameEndingCondition = true;
                    }
                    
                    else if(i >7 && i<15)
                    {
                        pottedBalls.potStripeBall(allBalls[i]);
                        if(PlayerBallType[turn] == 0)
                        {
                            switchTurn();
                        }
                    }
                    
                }
                
            }
        }
    }
    
    public void UpdateGameState(Scene scene) {


        
            allBalls[15].ball.setOnMouseDragged(event ->
            {
                if (Bounded() && !foulCommitted) {
                    flag = false;
                    root.getChildren().remove(cue.getCue());
                    allBalls[15].ball.setCursor(Cursor.CLOSED_HAND);
                    allBalls[15].ball.setLayoutX(event.getSceneX());
                    allBalls[15].ball.setLayoutY(event.getSceneY());
                    Bounded();
                }

                allBalls[15].ball.setOnMouseReleased(event1 -> {
                    root.getChildren().remove(cue.getCue());
                    flag = true;
                    Bounded();
                });
            });
        
            
        if (flag == true) {
            cue = new CueStick(root);
            setCuePos(scene);
            flag = false;
        }

        
            cue.moveCue(allBalls[15].ball.getLayoutX(), allBalls[15].ball.getLayoutY());
        
    }
    
    public void setCuePos(Scene scene)
    {
        cue.setCuePos(allBalls[15].ball.getLayoutX(), allBalls[15].ball.getLayoutY());
    }
    
    
    void rollBall()
    {
        for(int i =0; i<16; i++)
        {
            Rotate rx = new Rotate(0, 0, 0, 0, Rotate.X_AXIS);
            Rotate ry = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS);
            rx.setAngle(Math.toDegrees(allBalls[i].getVy() / 10)/4);
            ry.setAngle(-Math.toDegrees(allBalls[i].getVx() / 10));


            allBalls[i].ball.getTransforms().addAll(rx, ry);
            
        }
    }
    
    void EndGame()
    {
        if(gameEndingCondition)
        {
            root.getChildren().add(gameover);
        }
    }
}
