package Utilities;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;
import java.util.Objects;

public class CheckBox {

    PVector pos;
    boolean value;
    float size;
    PImage sprite, checked, unchecked;
    Color tint;
    String title;
    PVector pScreenSize;

    public CheckBox(PVector screenSize, PApplet sketch, PVector pos, boolean value, float size, Color tint) {
        this.pos = pos;
        this.value = value;
        this.size = size;
        this.checked = sketch.loadImage("src\\Utilities\\CheckBoxSprites\\WhiteChecked.png");
        this.unchecked = sketch.loadImage("src\\Utilities\\CheckBoxSprites\\WhiteUnchecked.png");
        this.sprite = (value) ? checked : unchecked;
        this.tint = tint;
        this.title = "";
        this.pScreenSize = screenSize.copy();
    }

    public CheckBox(PVector screenSize, PApplet sketch, PVector pos, boolean value, float size, Color tint, String title) {
        this.pos = pos;
        this.value = value;
        this.size = size;
        this.checked = sketch.loadImage("src\\Utilities\\CheckBoxSprites\\WhiteChecked.png");
        this.unchecked = sketch.loadImage("src\\Utilities\\CheckBoxSprites\\WhiteUnchecked.png");
        this.sprite = (value) ? checked : unchecked;
        this.tint = tint;
        this.title = title;
        this.pScreenSize = screenSize.copy();
    }


    public void update(PVector screenSize, PVector mousePos, boolean mousePressed, boolean pMousePressed) {
        boolean collision = Collisions.isTouching(mousePos,this.pos,this.size,this.size);
        if (mousePressed && !pMousePressed && collision) this.value = !this.value;
        if (this.value) this.sprite = checked;
        if (!this.value) this.sprite = unchecked;

        this.pos = new PVector(this.pos.x * screenSize.x/pScreenSize.x,this.pos.y * screenSize.y/pScreenSize.y);
        this.size = size * ((screenSize.x+screenSize.y)/(pScreenSize.x+pScreenSize.y));
        pScreenSize = screenSize.copy();
    }

    public void draw(PApplet sketch) {
        sketch.tint(this.tint.getRGB());
        sketch.image(this.sprite, this.pos.x, this.pos.y, this.size, this.size);
        if (!Objects.equals(this.title, "")) {
            sketch.textAlign(sketch.RIGHT, sketch.CENTER);
            sketch.textSize(this.size);
            sketch.fill(this.tint.getRGB());
            sketch.text(this.title,this.pos.x - (this.size*0.6f),this.pos.y + (this.size*0.4f));
            sketch.textAlign(sketch.LEFT,sketch.TOP);
        }
    }
}
