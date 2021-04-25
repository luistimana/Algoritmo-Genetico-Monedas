package modelo;

import org.jgap.*;
import java.io.File;
import org.jgap.data.*;
import org.jgap.impl.*;
import org.jgap.xml.XMLDocumentBuilder;
import org.jgap.xml.XMLManager;
import org.w3c.dom.Document;

public class CambioMinimo {
    
    private static final int MAX_EXOLUCIONES_PERMITIDAS = 2200;
    public static String cadena ="";
    
    public static void calcularCambioMinimo(int Monto)throws Exception{
            Configuration conf = new DefaultConfiguration();
            
            conf.setPreservFittestIndividual(true);
            
            FitnessFunction myFunc = new CambioMinimoFunctionAptitud(Monto);
            conf.setFitnessFunction(myFunc);
            
            Gene[] sampleGenes = new Gene[6];
            sampleGenes[0] = new IntegerGene(conf, 0, Math.round(CambioMinimoFunctionAptitud.MAX_MONTO/100));
            sampleGenes[1] = new IntegerGene(conf, 0,10);
            sampleGenes[2] = new IntegerGene(conf, 0,10);
            sampleGenes[3] = new IntegerGene(conf, 0,10);
            sampleGenes[4] = new IntegerGene(conf, 0,10);
            sampleGenes[5] = new IntegerGene(conf, 0,10);
            
            IChromosome sampleChromosome = new Chromosome(conf, sampleGenes);
            conf.setSampleChromosome(sampleChromosome);
            
            
            conf.setPopulationSize(200);
            Genotype Poblacion;
            
            Poblacion = Genotype.randomInitialGenotype(conf);
            
            long TiempoComienzo = System.currentTimeMillis();
            for(int i = 0; i<MAX_EXOLUCIONES_PERMITIDAS; i++){
                Poblacion.evolve();
            }
            
            long TiempoFin = System.currentTimeMillis();
            cadena+="Tiempo Total de evolución: " + (TiempoFin-TiempoComienzo) + " ms" + "\n"; guardarPoblacion(Poblacion);
            
            IChromosome cromosomaMasApto = Poblacion.getFittestChromosome();
            cadena+="\n"+ "El cromosoma mas apto encontrado tiene un valor de aptitud de: " + cromosomaMasApto.getFitnessValue()+ "\n";
            cadena+="\n" + "Y esta formado por la siguiente distribución de monedas: ";
            cadena+="\n" + "\t" + CambioMinimoFunctionAptitud.getNumeroDeComendasDeGen(cromosomaMasApto, 0) + " Monedas de 1 Sol";
            cadena+="\n" + "\t" + CambioMinimoFunctionAptitud.getNumeroDeComendasDeGen(cromosomaMasApto, 1) + " Monedas de 50 céntimos";
            cadena+="\n" + "\t" + CambioMinimoFunctionAptitud.getNumeroDeComendasDeGen(cromosomaMasApto, 2) + " Monedas de 20 céntimos";
            cadena+="\n" + "\t" + CambioMinimoFunctionAptitud.getNumeroDeComendasDeGen(cromosomaMasApto, 3) + " Monedas de 10 céntimos" + "\n";
       
            cadena+="\n" + "Para un total de " + CambioMinimoFunctionAptitud.montoCambioMoneda(cromosomaMasApto) + " céntimos en " + CambioMinimoFunctionAptitud.getNumeroTotalMonedas(cromosomaMasApto) + " monedas" ;
            
            cadena+="\n" + "\n"+ "\n"+ "\n" + "Desarrollado por: ";
            cadena+="\n" + "Luis Miguel Timaná Gonzaga ";
            
    }
    
    public static void guardarPoblacion(Genotype Poblacion) throws Exception {
        DataTreeBuilder builder = DataTreeBuilder.getInstance();
        IDataCreators doc2=builder.representGenotypeAsDocument(Poblacion);
        //Creamos el documento XML para generar el Arbol
        XMLDocumentBuilder docbuilder = new XMLDocumentBuilder();
        Document xmlDoc = (Document) docbuilder.buildDocument(doc2);
        XMLManager.writeFile(xmlDoc, new File("PoblacionCambioMinimo.xml"));
        
    }
    
    public static void main(String[] args) {
        
    }
    
}