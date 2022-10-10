package Utilities;

import processing.core.PApplet;
import processing.core.PVector;

public class Button {

    PVector pos;
    float width, height;
    boolean hover, clicked;
    PVector pScreenSize;

    public Button(PVector screenSize, PVector pos, float width, float height) {
        this.pos = pos;
        this.width = width;
        this.height = height;
        this.hover = false;
        this.clicked = false;
        this.pScreenSize = screenSize;
    }

    public Button(PVector screenSize, float x, float y, float width, float height) {
        this.pos = new PVector(x,y);
        this.width = width;
        this.height = height;
        this.hover = false;
        this.clicked = false;
        this.pScreenSize = screenSize;
    }


    public void update(PVector screenSize, PVector mousePos, boolean mousePressed, boolean pMousePressed) {
        boolean collision = Collisions.isTouching(mousePos, this.pos, this.width, this.height);
        this.hover = collision;

        if (this.clicked) clicked = false;
        if (!pMousePressed && mousePressed && collision) clicked = true;

        this.pos = new PVector(this.pos.x * screenSize.x/pScreenSize.x,this.pos.y * screenSize.y/pScreenSize.y);
        this.width = width * (screenSize.x/pScreenSize.x);
        this.height = height * (screenSize.y/pScreenSize.y);
        pScreenSize = screenSize.copy();
    }

    public void draw(PApplet s, int style) {
        if (style == 0) draw(s);
        if (style == 1) {
            s.noStroke();
            s.fill(255,200);
            if (this.hover) s.fill(255,120);
            if (this.clicked || (this.hover && s.mousePressed)) s.fill(255,100);
            s.rect(this.pos.x,this.pos.y,this.width,this.height,5);
        }
        else {
            draw(s);
        }
    }

    public void draw(PApplet s) {
        s.noStroke();
        s.fill(255,35);
        if (this.hover) s.fill(255,55);
        if (this.clicked || (this.hover && s.mousePressed)) s.fill(255,200);
        s.rect(this.pos.x,this.pos.y,this.width,this.height,5);
    }

    public PVector getPos() {
        return pos;
    }

    public void setPos(PVector pos) {
        this.pos = pos;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public boolean isHover() {
        return hover;
    }

    public void setHover(boolean hover) {
        this.hover = hover;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
}
