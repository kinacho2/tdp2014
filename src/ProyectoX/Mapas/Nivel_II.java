package ProyectoX.Mapas;

public class Nivel_II extends Mapa{

	@Override
	public Mapa nextMapa() {
		
		Mapa map = new Nivel_III();
		map.setMind(mind);
		map.setMindEnemies(mindEnemies);
		mind.setMapa(map);
		mindEnemies.setMapa(map);
		return map;
	}
	
}
