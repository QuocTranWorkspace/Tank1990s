package main.java.model.powerups;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Shovel {
    public static final Image image = new ImageIcon(Objects.requireNonNull(Shovel.class.getResource("../../../resource/img/bonus/bonus_shovel.png"))).getImage();
    public void activate() {

    }

    public Image getImage() {
        return image;
    }
}
