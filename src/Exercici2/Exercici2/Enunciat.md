# Exercici 2: Restaurant “Passaplats”
## Enunciat
### En un restaurant tenim:
### 1. Cuiners (productors): S’encarreguen de cuinar plats i deixar-los a la “barra passaplats”.
### 2. Cambrers (consumidors): Recullen els plats de la barra passaplats i els serveixen als clients.
#### Tenim un espai físic limitat per deixar plats, que anomenarem Passaplats. Aquest Passaplats té capacitat màxima (MAX_CAP) de plats alhora, el valor de MAX_CAP s’ha de poder variar, pot ser 4, 6, 10 etc...
#### Si un cuiner vol deixar un plat al Passaplats però aquest està ple, el cuiner haurà d’esperar (wait()) fins que hi hagi espai.
#### Si un cambrer vol agafar un plat però el Passaplats està buit, s’ha d’esperar (wait()) fins que hi hagi almenys un plat.
#### Quan un cuiner posa un plat, o un cambrer n’agafa un, s’ha de notificar (notifyAll()) perquè l’altre tipus de fil (cambrers o cuiners) es desperti, si estava esperant.
## Requeriments
### 1. Crea la classe Plat (només per donar informació bàsica: per exemple, nom de plat, un identificador i si et cal els atributs que considereu oportuns).
### 2. Crea la classe Passaplats que actuarà com a buffer entre productors i consumidors:
#### ○ Emmagatzema els plats en un array, List, o Queue de mida fixa (MAX_CAP).
#### ○ Implementa dos mètodes sincronitzats:
#### ■ posarPlat(Plat p): el cuiner hi deixa un nou plat (si hi ha espai).
#### ■ agafarPlat(): el cambrer n’agafa un (si n’hi ha).
#### ○ Fes servir wait() i notifyAll() per bloquejar i desbloquejar fils quan cal.
### 3. Crea 2 cuiners (productors) i 2 cambrers (consumidors) com a mínim, cadascun en un fil diferent.
### Cada cuiner, en un bucle, genera diferents plats (amb noms concrets: “Pasta”, “Amanida”, “Pizza”...).
#### Cada cambrer, en un bucle, agafa plats i simula el servei als clients.
### 4. Al final, el fil principal (main) espera que tots acabin, o bé, pots simular una execució indefinida i fer que els fils acabin després de X plats cuinats.