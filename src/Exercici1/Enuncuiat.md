Exercici 1: Reserva seients teatre

Enunciat
Disposem d’un teatre dividit en 3 zones:
1. Platea: 30 butaques numerades de la 1 a la 30
2. Amfiteatre: 10 butaques numerades de la 1 a la 10
3. Galeria: 15 butaques numerades de la 1 a la 15
   Volem simular la compra d’entrades per part de diversos usuaris (fils) de manera concurrent.
   Cada usuari:
   ● Escull una zona concreta (per exemple, “Platea”) i un nombre de butaques que vol
   reservar.
   ● Ha d’intentar reservar exactament aquestes butaques lliures.
   Objectius
1. Evitar el doble ús d’una mateixa butaca (que dos usuaris reservin la mateixa butaca
   alhora).
2. No permetre overbooking: si un usuari demana més butaques de les que queden
   lliures, la reserva s’ha de rebutjar (o informar que no es pot fer).
3. Sincronització: cal protegir l’accés concurrent a l’array (o estructura) que representa
   les butaques lliures/ocupades a cada zona.
4. Test: llançar diversos fils alhora que intentin fer reserves en diferents zones, per
   comprovar que el sistema funciona sense inconsistències.
   Requeriments
   ● Escriure la classe ZonaTeatre que representi una zona (per exemple Platea) amb un
   nom (String nomZona) i un conjunt de butaques (boolean[] butaques).
   ○ butaques[i] = false indicarà que la butaca i+1 està lliure.
   ○ butaques[i] = true indicarà que la butaca i+1 està ocupada.
   ● Implementar un mètode sincronitzat que permeti la reserva de n butaques si n’hi ha
   prou de disponibles.
   ● Crear fils (clients) que facin servir aquest mètode per provar de reservar butaques en
   paral·lel.
   ● Verificar que no es produeixen errors en les reserves (overbooking, duplicació d’una
   butaca, etc.).