package Proyecto2;

import java.awt.Image;

import javax.swing.ImageIcon;


public class Disparo {

	private int x, y;
    private Image image;
    boolean visible;

    private final int BOARD_WIDTH = 39;
    private final int MISSILE_SPEED = 20;
    
    //largo y ancho de la image
	
  	protected int height;
  	protected int width;
    
    public Disparo(int x, int y) {

        ImageIcon ii = new ImageIcon(Disparo.class.getClassLoader().getResource("img/Disparos/Basico/Basico.png"));
        height = ii.getIconHeight();
		width = ii.getIconWidth();
        image = ii.getImage();
        visible = true;
        this.x = x - width/2;
        this.y = y - height;
    }


    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void move() {
        y -= MISSILE_SPEED;
        if (y < BOARD_WIDTH)
            visible = false;
    }
}
