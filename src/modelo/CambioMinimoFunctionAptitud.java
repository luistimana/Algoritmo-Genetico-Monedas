package modelo;
import org.jgap.*;
public class CambioMinimoFunctionAptitud extends FitnessFunction{
    private final int montoObjetivo;
        public static final int MAX_MONTO=5000;  
        public static final int MAX_CANT_MONEDAS = MAX_MONTO;
        public CambioMinimoFunctionAptitud(int monto) {
            if (monto<1 || monto>=MAX_MONTO) {
                throw new IllegalArgumentException("El monto debe ser un número entre 1 y " + MAX_MONTO + "céntimos");
            }
            montoObjetivo = monto;
        }
        public double evaluate(IChromosome cromosoma) {
            boolean evaluadorEstandard = cromosoma.getConfiguration().getFitnessEvaluator().isFitter(2, 1);
            int montoCambioMonedas = montoCambioMoneda(cromosoma);
            int totalMonedas = getNumeroTotalMonedas(cromosoma);
            int diferenciaMonto = Math.abs(montoObjetivo - montoCambioMonedas);
            
            if (evaluadorEstandard) {
                if (diferenciaMonto != 0) 
                    return 0.0d;
            }else{
                if (diferenciaMonto != 0) 
                    return MAX_CANT_MONEDAS;
            }
            if (evaluadorEstandard) {
                return Math.max(0.0d, MAX_CANT_MONEDAS - totalMonedas);
            }else{
                return Math.max(0.0d, totalMonedas);
            }         
        }
        public static int montoCambioMoneda(IChromosome cromosoma) {
        int Moneda1Sol = getNumeroDeComendasDeGen(cromosoma, 0);
        int Moneda50Centimos = getNumeroDeComendasDeGen(cromosoma, 1);
        int Moneda20Centimos = getNumeroDeComendasDeGen(cromosoma, 2);
        int Moneda10Centimos = getNumeroDeComendasDeGen(cromosoma, 3);
            
        return (Moneda1Sol*100) + (Moneda50Centimos*50) + (Moneda20Centimos*20) + (Moneda10Centimos*10);
        }
        public static int getNumeroDeComendasDeGen(IChromosome cromosoma, int numeroGen) {
            Integer numMonedas = (Integer) cromosoma.getGene(numeroGen).getAllele();
            return numMonedas.intValue();
    }
    public static int getNumeroTotalMonedas(IChromosome cromosoma) {
        int totalMonedas=0;
        int numberOfGenes = cromosoma.size();
        for (int i = 0; i < numberOfGenes; i++) {
            totalMonedas += getNumeroDeComendasDeGen(cromosoma, i);
        }
        return totalMonedas;
    }
    public static void main(String[] args) {
    }
}