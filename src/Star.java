import Utilities.Time;
import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Star {

    PVector pos, pPos;
    PApplet sketch;
    List<FadingLine> fadingLines;
    Color color;
    float fadeTime;
    Time time;
    float bufferTime;
    float pTime;

    public Star(PVector pos, PVector center, Color color, float fadeTime, Time time, PApplet sketch) {
        this.pos = pos;
        this.pPos = pos.copy();
        this.sketch = sketch;
        this.fadingLines = new ArrayList<>();
        this.color = color;
        this.fadeTime = fadeTime;
        this.time = time;

        PVector radius = new PVector(pos.x - center.x, pos.y - center.y);
        this.bufferTime = (15000/(radius.mag() + 100)) + 10;

        this.pTime = System.nanoTime()*0.000001f;
    }

    public void update(PVector center, float angle, float fadeTime) {
        PVector radius = pos.sub(center);

        this.fadeTime = fadeTime;

        radius.rotate((float) (angle*time.getDeltaTime()));
        this.pos.set(new PVector(center.x + radius.x, center.y + radius.y));

        if (System.nanoTime()*0.000001f - pTime >= bufferTime) {
            this.fadingLines.add(new FadingLine(pPos, this.pos.copy(), System.nanoTime()*0.000001f, this.fadeTime, this.color, this.sketch));
            this.pPos = this.pos.copy();
            pTime = System.nanoTime()*0.000001f;
        }

        updateFadingLines();
    }

    private void updateFadingLines() {
        List<FadingLine> toRemove = new ArrayList<>();

        for (FadingLine l : this.fadingLines) {
            l.update();
            if (l.alpha <= 0) toRemove.add(l);
        }

        for (FadingLine l : toRemove) {
            this.fadingLines.remove(l);
        }
    }

    public void draw() {
        drawFadingLines();
    }

    private void drawFadingLines() {
        for (FadingLine l : this.fadingLines) {
            l.draw();
        }
    }

    public PVector getPos() {
        return pos;
    }

    public void setPos(PVector pos) {
        this.pos = pos;
    }

    public PVector getpPos() {
        return pPos;
    }

    public void setpPos(PVector pPos) {
        this.pPos = pPos;
    }

    public PApplet getSketch() {
        return sketch;
    }

    public void setSketch(PApplet sketch) {
        this.sketch = sketch;
    }

    public List<FadingLine> getFadingLines() {
        return fadingLines;
    }

    public void setFadingLines(List<FadingLine> fadingLines) {
        this.fadingLines = fadingLines;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getFadeTime() {
        return fadeTime;
    }

    public void setFadeTime(float fadeTime) {
        this.fadeTime = fadeTime;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public float getBufferTime() {
        return bufferTime;
    }

    public void setBufferTime(float bufferTime) {
        this.bufferTime = bufferTime;
    }

    public float getpTime() {
        return pTime;
    }

    public void setpTime(float pTime) {
        this.pTime = pTime;
    }
}
