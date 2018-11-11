# Project Title

TP JAVA 2A POO 2018/2019
groupe Sebastien Herbreteau, Alexandre Donne, Victor Bros

## Getting Started

Commencez avec `make clean`
Ensuite pour compiler le scénario principal `make compileMain`
Pour lancer un des scénarios `make`+un des scénarios dans le Makefile
exemple: `make exeImag`

## Running the map tests

Tests sur des maps/scénarios élémentaires:
par exemple,
```
make compileMain
make exeLigneDroite
```

### Break down into end to end tests

Ces tests sont utilisés pour vérifier la bonne démarche de lecture des données et des décisions prises sur des scénarios simples.

```
make exeDetour
```
-->Le robot doit faire un détour pour ne pas passer sur les roches

###Unit test

Ce test unitaire sert à tester si le lecteurDonnees instancie bien les bons objets.
On pourrait en ajouter d'autres et tous les lancer via la classe AllTests.
Pour le lancer :

```
make compileTests
make exeUnitTests
```

## Deployment

Pour l'utilisation dans le GUI généré, après le starting screen appuyer sur "lecture", vous pouvez gérer la vitesse des actions en modifiant l'échelle de temps.
N'oubliez pas de mettre en plein écran ;)

## Versioning

On a utilisé http://github.com/ pour le versioning du projet.

## Authors

* **Victor BROS**
* **Alexandre DONNE**
* **Sebastien HERBRETEAU**
