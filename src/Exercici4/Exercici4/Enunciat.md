# Exercici 2: Sistema Col·laboratiu de Redacció i Lectura d’Articles
## Enunciat
### Imagina que tens un sistema col·laboratiu on múltiples usuaris volen llegir i editar (escriure) articles alhora. Podríem assimilar-ho a una mena de “wiki” on diverses persones poden consultar (lectors) i unes altres poden modificar o ampliar el contingut (escriptors).
### 1. Lectors: Poden accedir a llegir tots els articles de manera concurrent, sense limitar el nombre de lectors simultanis.
### 2. Escriptors: Quan un escriptor està modificant un article concret, cap altre escriptor ni lector pot accedir a aquell mateix article fins que l’escriptor acabi.
### 3. Evitació d’Starvation: Cal evitar que un escriptor es quedi sense poder escriure eternament perquè sempre hi ha lectors que entren abans (o a la inversa).
### 4. Poden existir múltiples articles (p. ex. 2 o 3), i diferents fils que llegeixen o escriuen cadascun d’ells.
### 5. Regles bàsiques del patró Reader-Writer:
#### Varis lectors poden llegir a la vegada sempre que no hi hagi cap escriptor en aquell article.
#### Escriptors necessiten exclusió total (ningú més pot accedir en lectura o escriptura, mentre escriuen).
### Volem un codi on es llancin diversos fils que simulin molts lectors i escriptors, i s’observi com el recurs (els articles) es protegeix de forma coherent.
## Requeriments
### Múltiples articles
### Els lectors (molts) poden llegir a la vegada, mentre no hi hagi escriptors.
### Un escriptor exclou altres escriptors i també tots els lectors sobre aquell article.
### Evitem starvation de l’escriptor utilitzant, controlant l’accés de lectors quan hi ha petició d’escriptura.