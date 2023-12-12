import processing.core.*;
import Utilities.*;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Main extends PApplet {

    boolean pMousePressed;
    PVector mousePos;
    PVector screenSize;
    List<Star> stars;
    PImage bg;
    Time time;
    float starSpeed;
    float starFadeTime;
    boolean sinSpeed;

    public void settings() {
        smooth(4);
        size(700,700);
    }

    public void setup() {
        surface.setResizable(true);

        pMousePressed = false;
        mousePos = new PVector(0,0);
        screenSize = new PVector(width, height);
        starSpeed = 0.005f;
        starFadeTime = 500;
        sinSpeed = false;
        stars = new ArrayList<>();
        time = new Time();

        setStars();

        bg = loadImage("bg5.png");
        //textFont(createFont("Fonts/Montserrat-Medium.ttf", 10));

        frameRate(999);
    }

    private void setStars() {
        stars = new ArrayList<>();

        for (int i = 0; i < 300; i++) {
            int randomR = (int) Math.floor(Math.random()*80) + 120;
            int randomG = (int) Math.floor(Math.random()*80) + 120;
            int randomB = (int) Math.floor(Math.random()*30) + 225;

            Color randomColor = new Color(randomR, randomG, randomB);

            stars.add(new Star(new PVector((float) (Math.random()*width), (float) (Math.random()*height)),
                    new PVector(width*0.5f, height*0.5f), randomColor.brighter(),
                    starFadeTime, time, this));
        }
    }

    public void draw() {
        preDraw();

        image(bg, 0, 0, width, height);

        for (Star s : stars) {
            if (sinSpeed) starSpeed = sin(System.nanoTime()*0.000000001f)*1.5f;
            s.update(new PVector(width*0.5f, height*0.5f), starSpeed, starFadeTime);
            s.draw();
        }

        fill(0);
        noStroke();
        rect(0,0,50,38);
        fill(255);

        text("Sp: " + starSpeed, 0, 12);
        text("Fd: " + starFadeTime, 0, 24);
        text("Fps: " + frameRate, 0, 36);

        postDraw();
    }

    private void preDraw() {
        mousePos = new PVector(mouseX, mouseY);
        screenSize = new PVector(width, height);
        time.update();
    }

    public void keyPressed() {
        if (key == 'r') setStars();
        if (keyCode == RIGHT) starSpeed += 0.05f;
        if (keyCode == LEFT) starSpeed -= 0.05f;
        if (keyCode == UP) starFadeTime += 500;
        if (keyCode == DOWN) starFadeTime -= 500;
        if (key == 's') sinSpeed = !sinSpeed;
    }

    private void postDraw() {
        pMousePressed = mousePressed;
    }

    public static void main(String[] args) {
        PApplet.main("Main");
    }
}
