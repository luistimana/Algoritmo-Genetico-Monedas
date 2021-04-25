# Algoritmo-Genetico-Monedas
 Algoritmo Genético para realizar un cambio de un monto de dinero en monedas
 
#CASO PROPUESTO

Supongamos que es necesario descomponer un cierto monto de dinero en la menor cantidad posible de monedas. Por ejemplo, si se tienen 2.70 S/. (270 céntimos) puede descomponerse de la siguiente forma:
-	2 monedas de un sol 
-	3 monedas de 20 céntimos 
-	1 moneda de 10 céntimos
3 monedas en total

Pero también puede descomponerse de la siguiente forma: 
-	27 monedas de 10 céntimos. 
27 monedas en total.

Hay muchas formas de descomponer este monto en monedas cada una de ellas es una solución posible al problema (cromosoma) y tiene un valor de aptitud asociado, que deberá depender de la cantidad de monedas totales de ese cromosoma. Cuantas menos monedas se necesiten más apta será la solución ya que lo que se busca es lograr la menor cantidad de monedas posibles.

Cada cromosoma tendrá 4 genes. Los genes en este problema son números enteros que representan la cantidad de monedas de cada tipo

-	Moneda de un sol (100 céntimos) 
-	Moneda de 50 céntimos
-	Moneda de 20 céntimos
-	Moneda de 10 céntimos


