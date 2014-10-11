package Proyecto2;

import java.awt.Image;

import javax.swing.ImageIcon;


public class Disparo {

	private int x, y;
    private Image image;
    boolean visible;

    private final int BOARD_WIDTH = 39;
    private int velocidad;
    
    //largo y ancho de la image
	
  	protected int height;
  	protected int width;
	private int daño;
	double dx;
	double dy;
	
    
    public Disparo(int x, int y, double dx, double dy, int missileSpeed) {

    	height = 2;
		width = 2;
    	this.dx=dx;
    	this.dy=dy;
    	
        ImageIcon ii = new ImageIcon(Disparo.class.getClassLoader().getResource("img/Disparos/Basico/Basico.png"));
        ImageIcon aux = new ImageIcon(ii.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
        image = aux.getImage();
       
        visible = true;
        this.x = x - width/2;
        this.y = y - height;
        daño=1;
        velocidad = missileSpeed;
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
        return visible ;
    }

    public void move() {
        y -= dy*velocidad;
        x += dx*velocidad;
       if (y < BOARD_WIDTH)
            visible = false;
    }
    
    public boolean colision(Nave nave) {
		 return (x >= nave.getX()+1 &&  x <= (nave.getX() + nave.getWidth()+1) && y >= nave.getY()+1  && y <= (nave.getY() + nave.getHeight()+1)) ||
				((x + width) >= nave.getX()+1 &&  (x + width) <= (nave.getX() + nave.getWidth()+1) && y >= nave.getY()+1  && y <= (nave.getY() + nave.getHeight()+1)) ||
				(x >= nave.getX()+1 &&  x <= (nave.getX() + nave.getWidth()+1) && (y + height) >= nave.getY()+1  && (y + height) <= (nave.getY() + nave.getHeight()+1)) ||
				((x + width) >= nave.getX()+1 &&  (x + width) <= (nave.getX() + nave.getWidth()+1) && (y + height) >= nave.getY()+1  && (y + height) <= (nave.getY() + nave.getHeight()+1));
	}
    
    public void setVisible() {
    	visible = false;
    }

	public int getDaño() {
		return daño;
	}
	
	public Disparo[] cloneNivel(int power){
		Disparo[] d = new Disparo[ power*2-1 ];
		double n = 0;
		
		for(int i = 0;i<power*2-1;i++){
			d[i]=new Disparo(x,y,n*power,dy,velocidad);
			
			if(n == 0){
				n = -0.1d;
			}

			if(n + 0.2 == -n && i == 2){
				n=0.3d;
			}
			n = -n;
		}
		return d;
	}

	
	
}
