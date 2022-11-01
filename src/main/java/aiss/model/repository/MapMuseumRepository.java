package aiss.model.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import aiss.model.Museum;
import aiss.model.Painting;
import aiss.model.Sculpture;


public class MapMuseumRepository implements MuseumRepository{

	Map<String, Museum> museumMap;
	Map<String, Painting> paintingMap;
	Map<String, Sculpture> sculptureMap;
	private static MapMuseumRepository instance=null;
	private int indexM=1;			// Index to create museums identifiers.
	private int indexP=1;			// Index to create paintings identifiers.
	private int indexS=1;			// Index to create sculptures identifiers.


	public static MapMuseumRepository getInstance() {

		if (instance==null) {
			instance = new MapMuseumRepository();
			instance.init();
		}

		return instance;
	}

	public void init() {

		museumMap = new HashMap<String,Museum>();
		paintingMap = new HashMap<String,Painting>();
		sculptureMap = new HashMap<String,Sculpture>();

		// Create paintings

		Painting laGioconda = new Painting();
		laGioconda.setName("La Gioconda");
		laGioconda.setArtist("Leonardo da Vinci");
		laGioconda.setYear("1503");
		laGioconda.setTechnique("Pintura al Óleo");
		laGioconda.setStyle("Renacimiento");
		addPainting(laGioconda);

		Painting laNocheEstrellada = new Painting();
		laNocheEstrellada.setName("La noche estrellada");
		laNocheEstrellada.setArtist("Vincent van Gogh");
		laNocheEstrellada.setYear("1886");
		laNocheEstrellada.setTechnique("Pintura al Óleo");
		laNocheEstrellada.setStyle("Postimpresionismo");
		addPainting(laNocheEstrellada);

		Painting guernica = new Painting();
		guernica.setName("Guernica");
		guernica.setArtist("Pablo Picasso");
		guernica.setYear("1937");
		guernica.setTechnique("Óleo sobre lienzo");
		guernica.setStyle("Cubismo");
		addPainting(guernica);

		Painting lasMeninas = new Painting();
		lasMeninas.setName("Las Meninas");
		lasMeninas.setArtist("Diego Velázquez");
		lasMeninas.setYear("1656");
		lasMeninas.setTechnique("Óleo sobre lienzo");
		lasMeninas.setStyle("Barroco");
		addPainting(lasMeninas);

		Painting góticoEstadounidense = new Painting();
		góticoEstadounidense.setName("Gótico Estadounidense");
		góticoEstadounidense.setArtist("Grant Wood");
		góticoEstadounidense.setYear("1930");
		góticoEstadounidense.setTechnique("Pintura al Óleo");
		góticoEstadounidense.setStyle("Modernismo");
		addPainting(góticoEstadounidense);

		Painting laÚltimaCena=new Painting();
		laÚltimaCena.setName("La Última Cena");
		laÚltimaCena.setArtist("Leonardo da Vinci");
		laÚltimaCena.setYear("1489");
		laÚltimaCena.setTechnique("Pintura al temple");
		laÚltimaCena.setStyle("Renacentista");
		addPainting(laÚltimaCena);

		Painting laJovenDeLaPerla=new Painting();
		laJovenDeLaPerla.setName("La joven de la perla");
		laJovenDeLaPerla.setArtist("Johannes Vermeer");
		laJovenDeLaPerla.setYear("1665");
		laJovenDeLaPerla.setTechnique("Óleo sobre tela");
		laJovenDeLaPerla.setStyle("Barroco");
		addPainting(laJovenDeLaPerla);

		Painting solNaciente=new Painting();
		solNaciente.setName("Impresión, sol naciente");
		solNaciente.setArtist("Claude Monet");
		solNaciente.setYear("1872");
		solNaciente.setTechnique("Óleo sobre lienzo");
		solNaciente.setStyle("Impresionismo");
		addPainting(solNaciente);

		Painting elJardínDeLasDelicias=new Painting();
		elJardínDeLasDelicias.setName("El jardín de las delicias");
		elJardínDeLasDelicias.setArtist("El Bosco");
		elJardínDeLasDelicias.setYear("1515");
		elJardínDeLasDelicias.setTechnique("Pintura al óleo sobre tabla");
		elJardínDeLasDelicias.setStyle("Primitivo flamenco");
		addPainting(elJardínDeLasDelicias);

		Painting elGrito = new Painting();
		elGrito.setName("El Grito");
		elGrito.setArtist("Edvard Munch");
		elGrito.setYear("1893");
		elGrito.setTechnique("Óleo y Temple");
		elGrito.setStyle("Expresionismo");
		addPainting(elGrito);
		
		Painting enanoConUnPerro = new Painting();
		enanoConUnPerro.setName("Enano con un perro");
		enanoConUnPerro.setArtist("Desconocido");
		enanoConUnPerro.setYear("1650");
		enanoConUnPerro.setTechnique("Óleo sobre lienzo");
		enanoConUnPerro.setStyle("Barroco");
		addPainting(enanoConUnPerro);

		// Create sculptures

		Sculpture david = new Sculpture();
		david.setName("David");
		david.setArtist("Miguel Ángel");
		david.setYear("1501");
		david.setMaterial("Mármol Blanco");
		addSculpture(david);

		Sculpture elPensador = new Sculpture();
		elPensador.setName("El Pensador");
		elPensador.setArtist("Auguste Rodin");
		elPensador.setYear("1904");
		elPensador.setMaterial("Bronce");
		addSculpture(elPensador);

		Sculpture venusDeMilo = new Sculpture();
		venusDeMilo.setName("Venus De Milo");
		venusDeMilo.setArtist("Alejandro de Antioquía");
		venusDeMilo.setYear("130");
		venusDeMilo.setMaterial("Mármol blanco");
		addSculpture(venusDeMilo);

		Sculpture piedadDelVaticano = new Sculpture();
		piedadDelVaticano.setName("Piedad Del Vaticano");
		piedadDelVaticano.setArtist("Miguel Ángel Buonarroti");
		piedadDelVaticano.setYear("1498");
		piedadDelVaticano.setMaterial("Mármol");
		addSculpture(piedadDelVaticano);

		Sculpture moises = new Sculpture();
		moises.setName("Moisés");
		moises.setArtist("Miguel Ángel");
		moises.setYear("1513");
		moises.setMaterial("Mármol Blanco");
		addSculpture(moises);

		Sculpture elRaptoDeProsérpina = new Sculpture();
		elRaptoDeProsérpina.setName("El rapto de Proserpina");
		elRaptoDeProsérpina.setArtist("Gian Lorenzo Bernini");
		elRaptoDeProsérpina.setYear("1621");
		elRaptoDeProsérpina.setMaterial("Mármol Blanco");
		addSculpture(elRaptoDeProsérpina);

		Sculpture laVictoriaDeSamotracia = new Sculpture();
		laVictoriaDeSamotracia.setName("La Victoria de Samotracia");
		laVictoriaDeSamotracia.setArtist("Desconocido");
		laVictoriaDeSamotracia.setYear("1863");
		laVictoriaDeSamotracia.setMaterial("Mármol Blanco");
		addSculpture(laVictoriaDeSamotracia);

		Sculpture perseoConLaCabezaDeMedusa = new Sculpture();
		perseoConLaCabezaDeMedusa.setName("Perseo con la cabeza de Medusa");
		perseoConLaCabezaDeMedusa.setArtist("Benvenuto Cellini");
		perseoConLaCabezaDeMedusa.setYear("1545");
		perseoConLaCabezaDeMedusa.setMaterial("Bronce");
		addSculpture(perseoConLaCabezaDeMedusa);


		Sculpture elHombreQueCamina=new Sculpture();
		elHombreQueCamina.setName("El hombre que camina");
		elHombreQueCamina.setArtist("Alberto Giacometti");
		elHombreQueCamina.setYear("1961");
		elHombreQueCamina.setMaterial("Bronce");
		addSculpture(elHombreQueCamina);

		Sculpture esclavoRebelde = new Sculpture();
		esclavoRebelde.setName("Esclavo rebelde");
		esclavoRebelde.setArtist("Miguel Ángel");
		esclavoRebelde.setYear("1513");
		esclavoRebelde.setMaterial("Mármol blanco");
		addSculpture(esclavoRebelde);

		// Create museums

		Museum louvre=new Museum();
		louvre.setName("Louvre");
		louvre.setCountry("Francia");
		louvre.setCity("París");
		louvre.setOpeningYear("1793");
		addMuseum(louvre);

		Museum metropolitanoDeArte =new Museum();
		metropolitanoDeArte.setName("Metropolitano De Arte");
		metropolitanoDeArte.setCountry("Estados Unidos");
		metropolitanoDeArte.setCity("Nueva York");
		metropolitanoDeArte.setOpeningYear("1870");
		addMuseum(metropolitanoDeArte);

		Museum prado=new Museum();
		prado.setName("El Prado");
		prado.setCountry("España");
		prado.setCity("Madrid");
		prado.setOpeningYear("1819");
		addMuseum(prado);

		Museum bellasArtesDeSevilla=new Museum();
		bellasArtesDeSevilla.setName("Museo de Bellas Artes de Sevilla");
		bellasArtesDeSevilla.setCountry("España");
		bellasArtesDeSevilla.setCity("Sevilla");
		bellasArtesDeSevilla.setOpeningYear("1835");
		addMuseum(bellasArtesDeSevilla);

		Museum museoBritánico=new Museum();
		museoBritánico.setName("Museo Británico");
		museoBritánico.setCountry("Reino Unido");
		museoBritánico.setCity("Londres");
		museoBritánico.setOpeningYear("1759");
		addMuseum(museoBritánico);

		Museum aiss = new Museum();
		aiss.setName("Museo Aiss");
		aiss.setCountry("España");
		aiss.setCity("Sevilla");
		aiss.setOpeningYear("2021");
		addMuseum(aiss);

		// Add paintings to museums

		addPainting(louvre.getMuseumId(), laGioconda.getPaintingId());
		addPainting(louvre.getMuseumId(), laNocheEstrellada.getPaintingId());
		addPainting(prado.getMuseumId(), guernica.getPaintingId());
		addPainting(prado.getMuseumId(), lasMeninas.getPaintingId());
		addPainting(prado.getMuseumId(), enanoConUnPerro.getPaintingId());
		addPainting(metropolitanoDeArte.getMuseumId(), góticoEstadounidense.getPaintingId());
		addPainting(metropolitanoDeArte.getMuseumId(), laÚltimaCena.getPaintingId());
		addPainting(museoBritánico.getMuseumId(), laJovenDeLaPerla.getPaintingId());
		addPainting(museoBritánico.getMuseumId(), solNaciente.getPaintingId());
		addPainting(bellasArtesDeSevilla.getMuseumId(), elJardínDeLasDelicias.getPaintingId());
		addPainting(bellasArtesDeSevilla.getMuseumId(), elGrito.getPaintingId());
		
		// Add sculptures to museums

		addSculpture(louvre.getMuseumId(), david.getSculptureId());
		addSculpture(louvre.getMuseumId(), elPensador.getSculptureId());
		addSculpture(prado.getMuseumId(), venusDeMilo.getSculptureId());
		addSculpture(prado.getMuseumId(), piedadDelVaticano.getSculptureId());
		addSculpture(metropolitanoDeArte.getMuseumId(), moises.getSculptureId());
		addSculpture(metropolitanoDeArte.getMuseumId(), elRaptoDeProsérpina.getSculptureId());
		addSculpture(museoBritánico.getMuseumId(), laVictoriaDeSamotracia.getSculptureId());
		addSculpture(museoBritánico.getMuseumId(), perseoConLaCabezaDeMedusa.getSculptureId());
		addSculpture(bellasArtesDeSevilla.getMuseumId(), elHombreQueCamina.getSculptureId());
		addSculpture(bellasArtesDeSevilla.getMuseumId(), esclavoRebelde.getSculptureId());
	}

	// Museum related operations
	@Override
	public void addMuseum(Museum m) {
		String id = "m" + indexM++;	
		m.setMuseumId(id);
		museumMap.put(id,m);
	}

	@Override
	public Collection<Museum> getAllMuseums() {
		return museumMap.values();
	}

	@Override
	public Museum getMuseum(String museumId) {
		return museumMap.get(museumId);
	}

	@Override
	public void updateMuseum(Museum m) {
		museumMap.put(m.getMuseumId(),m);
	}

	@Override
	public void deleteMuseum(String museumId) {	
		museumMap.remove(museumId);
	}

	@Override
	public Collection<Painting> getAllPaintingsByMuseum(String museumId) {
		return getMuseum(museumId).getPaintings();
	}

	@Override
	public void addPainting(String museumId, String paintingId) {
		Museum museum = getMuseum(museumId);
		museum.addPainting(paintingMap.get(paintingId));
	}

	@Override
	public void removePainting(String museumId, String paintingId) {
		getMuseum(museumId).deletePainting(paintingId);
	}

	@Override
	public Collection<Sculpture> getAllSculpturesByMuseum(String museumId) {
		return getMuseum(museumId).getSculptures();
	}

	@Override
	public void addSculpture(String museumId, String sculptureId) {
		Museum museum = getMuseum(museumId);
		museum.addSculpture(sculptureMap.get(sculptureId));
	}

	@Override
	public void removeSculpture(String museumId, String sculptureId) {
		getMuseum(museumId).deleteSculpture(sculptureId);
	}


	// Painting related operations

	@Override
	public void addPainting(Painting p) {
		String id = "p" + indexP++;
		p.setPaintingId(id);
		paintingMap.put(id, p);
	}

	@Override
	public Collection<Painting> getAllPaintings() {
		return paintingMap.values();
	}

	@Override
	public Painting getPainting(String paintingId) {
		return paintingMap.get(paintingId);
	}

	@Override
	public void updatePainting(Painting p) {
		Painting painting = paintingMap.get(p.getPaintingId());
		painting.setArtist(p.getArtist());
		painting.setName(p.getName());
		painting.setStyle(p.getStyle());
		painting.setTechnique(p.getTechnique());
		painting.setYear(p.getYear());
	}

	@Override
	public void deletePainting(String paintingId) {
		paintingMap.remove(paintingId);
	}

	// Sculpture related operations

	@Override
	public void addSculpture(Sculpture s) {
		String id = "s" + indexS++;
		s.setSculptureId(id);
		sculptureMap.put(id, s);
	}

	@Override
	public Collection<Sculpture> getAllSculptures() {
		return sculptureMap.values();
	}

	@Override
	public Sculpture getSculpture(String sculptureId) {
		return sculptureMap.get(sculptureId);
	}

	@Override
	public void updateSculpture(Sculpture s) {
		Sculpture sculpture = sculptureMap.get(s.getSculptureId());
		sculpture.setArtist(s.getArtist());
		sculpture.setName(s.getName());
		sculpture.setMaterial(s.getMaterial());
		sculpture.setYear(s.getYear());
	}

	@Override
	public void deleteSculpture(String sculptureId) {
		sculptureMap.remove(sculptureId);
	}

}