# Ensimag 2A POO - TP 2018/19
# ============================
#
# Ce Makefile permet de compiler le test de l'ihm en ligne de commande.
# Alternative (recommandee?): utiliser un IDE (eclipse, netbeans, ...)
# Le but est ici d'illustrer les notions de "classpath", a vous de l'adapter
# a votre projet.
#
# Organisation:
#  1) Les sources (*.java) se trouvent dans le repertoire src
#     Les classes d'un package toto sont dans src/toto
#     Les classes du package par defaut sont dans src
#
#  2) Les bytecodes (*.class) se trouvent dans le repertoire bin
#     La hierarchie des sources (par package) est conservee.
#     L'archive bin/gui.jar contient les classes de l'interface graphique
#
# Compilation:
#  Options de javac:
#   -d : repertoire dans lequel sont places les .class compiles
#   -classpath : repertoire dans lequel sont cherches les .class deja compiles
#   -sourcepath : repertoire dans lequel sont cherches les .java (dependances)


testInvader:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/test/TestInvader.java

testLecture:
	javac -d bin -sourcepath src src/test/TestLecteurDonnees.java


	# Execution:
	# on peut taper directement la ligne de commande :
	#   > java -classpath bin:bin/gui.jar TestInvader
	# ou bien lancer l'execution en passant par ce Makefile:
	#   > make exeInvader
exeInvader:
	java -classpath bin:bin/gui.jar TestInvader

compileTests:
	javac -d bin -classpath bin/junit-4.12.jar:bin/gui.jar:bin/hamcrest-all-1.3.jar:bin/easymock-4.0.1.jar -sourcepath src src/test/AllTests.java

exeUnitTests:
	java -classpath bin/junit-4.12.jar:bin:bin/hamcrest-all-1.3.jar:bin/gui.jar org.junit.runner.JUnitCore test.AllTests

exeLecture:
	java -classpath bin TestLecteurDonnees cartes/carteSujet.map

testMain:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/Programm.java

exeCarteSujet:
	java -classpath bin:bin/gui.jar Programm cartes/carteSujet.map

exeDesert:
	java -classpath bin:bin/gui.jar Programm cartes/desertOfDeath-20x20.map


exeMushroom:
	java -classpath bin:bin/gui.jar Programm cartes/mushroomOfHell-20x20.map

exeSpiral:
	java -classpath bin:bin/gui.jar Programm cartes/spiralOfMadness-50x50.map

exeMaze:
	java -classpath bin:bin/gui.jar Programm cartes/mazeOfFate-11x11.map

exeImag:
	java -classpath bin:bin/gui.jar Programm cartes/imagOfDespair-20x20.map

#tests élémentaires

exeLigneDroite:
	java -classpath bin:bin/gui.jar Programm cartes/testLigneDroite.map

exeDetour:
	java -classpath bin:bin/gui.jar Programm cartes/testDetour.map

exeIncendies:
	java -classpath bin:bin/gui.jar Programm cartes/testIncendies.map

exeObstacles:
	java -classpath bin:bin/gui.jar Programm cartes/testObstacles.map

exeAllerRetour:
	java -classpath bin:bin/gui.jar Programm cartes/testAllerRetour.map

clean:
	rm -rf bin/*/*.class
