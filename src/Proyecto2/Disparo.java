package Proyecto2;

import java.awt.Image;
import javax.swing.ImageIcon;


public class Disparo {

	private int x, y;
    private Image image;
    boolean visible;

    private int velocidad;
    
    //largo y ancho de la image
	
  	protected int height;
  	protected int width;
	private int damage;
	double dx;
	double dy;

    private int maxHeight = -height*2;
    private int minHeight = 600 + height;
	private int maxWidth = 800 + width;
	private int minWidth = -width*2;
    
    
    public Disparo(int x, int y, double dx, double dy, int missileSpeed) {

    	height = 10;
		width = 10;
    	this.dx=dx;
    	this.dy=dy;
    	
        ImageIcon ii = new ImageIcon(Disparo.class.getClassLoader().getResource("img/Disparos/Basico/Basico.png"));
        ImageIcon aux = new ImageIcon(ii.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
        image = aux.getImage();
       
        visible = true;
        this.x = x - width/2;
        this.y = y - height;
        damage=1;
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
       if (y < maxHeight || y >  minHeight || x < minWidth || x > maxWidth)
            setVisible();
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

	public int getDamage() {
		return damage;
	}
	
	public Disparo[] cloneNivel(int power){
		Disparo[] d = new Disparo[ power*2-1 ];
		double n = 0;
		int direccion = 1;
		for(int i = 0;i<power*2-1;i++){
			d[i]=new Disparo(x, y, n*power, direccion*dy, velocidad);
			
			if(n == 0){
				n = -0.3d;
			}

			if(n + 0.6 == -n && i == 2){
				n=0.3d;
				direccion = -1;
				y += 30;
			}
			n = -n;
		}
		return d;
	}
	
	public Explosion newExplosion(){
		return new ExplosionPequena(x + width/2, y + height / 2);
	}
	
	public void setPosicion(int x, int y){
		this.x = x;
		this.y = y;
	}
	
}

