package ProyectoX.PowerUps;

import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.Multiplicador.MultiplicadorLVI;
import ProyectoX.Disparos.Wave.DisparoWave;
import ProyectoX.Disparos.Wave.WaveLVI;
import ProyectoX.Naves.Jugador.Jugador;

public class Wave extends PowerUp {
	

protected static final URL url = (PowerUp.class.getClassLoader().getResource("ProyectoX/img/PUP/wave.png"));

protected String power = "wav";
	
	public Wave(int x, int y) {
		super(x, y, new ImageIcon(url));
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void efecto(Jugador jugador) {
		if(jugador.getPower().equals(power)){
			jugador.setNewDisparo(jugador.getDisparo().nextLevel());
		}
		else{
			jugador.setNewDisparo(new WaveLVI(x, y , 0, 1));
			jugador.setPower(power);
		}
		
	}
		
	
}
