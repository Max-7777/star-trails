import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;

public class FadingLine {

    PVector pos1, pos2;
    float pTime;
    float fadeTime;
    Color color;
    float alpha;
    PApplet sketch;

    public FadingLine(PVector pos1, PVector pos2, float pTime, float fadeTime, Color color, PApplet sketch) {
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.pTime = pTime;
        this.fadeTime = fadeTime;
        this.color = color;
        this.alpha = 255;
        this.sketch = sketch;
    }

    public void update() {
        this.alpha = Math.max(PApplet.map((System.nanoTime()*0.000001f) - pTime, 0, fadeTime, 255, 0), 0);
    }

    public void draw() {
        this.sketch.stroke(this.color.getRGB(), this.alpha);
        this.sketch.line(pos1.x, pos1.y, pos2.x, pos2.y);
    }

}
