# Oversikt

Student:
* Navn: Malin Wist Jakobsen
* BrukerNavn: xun007
* Epost: malin@j-j.no   / malin.jakobsen@student.uib.no

## Om funksjonaliteten

## Svar på spørsmål
* Se på Position.move(). Hvordan virker den? Hvordan er den annerledes fra Position-klasse i Lab 5?

Metoden tar inn hvilken retning man skal bevege seg mot og distansen. Forskjellen mellom denne og den i 
lab 5, er man også sjekker om distansen er null i selve position metodene, istedenfor i de metodene man
kaller på Position.move().



* Hva er forskjellen på AbstractSimObject og AbstractMovingObject? (Det er meningen du skal bruke disse som superklasser når du lager ting selv)

Forskjellen på klassene er at man bruker simObject til logikken til alt av objektene i simulatoren. Mens movingObject har logikken til
alle bevegende objekter. Man bruker vanligvis to forskjellige klasser til dette siden det finnes både objekter som beveger seg
og står i ro i simulatoren. 

* Posisjonen er lagret i en private feltvariabel. Hvordan er det meningen at subklasser skal kunne justere posisjonen?
Hva gjør hjelpemetodene distanceTo og directionTo?



* AbstractMovingObject har en metode accelerateTo for å endre på farten. Kunne det være smart å gjøre speed feltvariablen private?

Vi har ikke laget noen hjelpemetoder for å justere på retningen. Hva må du gjøre for å endre retning i en subklasse? Burde vi ha metoder også for å endre retning?
I SimAnimal så bruker jeg turnTowards for å justere retningen min gradvis. Vi kunne laget metoder 

* Vi har ingen public metoder for å endre på posisjon, retning, osv. Hadde det vært lurt å ha det? Hvorfor / hvorfor ikke?



<i>1.7: Noen få ekstra spørsmål: 
Felles for en del av tingene du har gjort her er:
Det er et lite grensesnitt som skal implementeres (en lytter, en komparator, en fabrikk) – ofte vil objektet bare ha én metode.
Du bruker objektet til å utvide eller endre funksjonaliteten til et annet objekt – sorteringsalgoritmen kan nå sortere basert på næringsverdi, SimMain kjenner til nye sim-objekter som kan produseres og legges ut på skjermen.
Klassene som er i bruk blir gjerne bare brukt én gang (det er derfor det er vanlig med anonyme klasser i litt mer avanset Java–bruk).</i>

<p></p>
<b>Spørsmål: </b>


* Hvorfor tror du vi har laget systemet på denne måten? Hadde det vært andre måter å få det til på (ville det i såfall vært like fleksibelt)?

Når man håndterer flere objekter og flere instans av samme objekt, så er dette den mest fleksible måten.

* Kunne vi fått dette til uten å bruke grensesnitt?

Dette er mulig hvis det er satt opp slik at hvis den og den hendelsen skjer, så skrives det ut hva som skjer. For å "se" hva som skjer i simulatoren.
Eller starte simulatoren med å få listet opp et par stats om hva som skjer før det starter, og så la det kjøre så så lenge. For å få listet opp en 
oppsummering om slutten av simulatoren.l
 



## Kilder til media

* Rammeverkkode: © Anya Helene Bagge (basert på tidligere utgaver, laget av Anya Helene Bagge, Anneli Weiss og andre).

* pipp.png, bakgrunn.png © Anya Helene Bagge, This work is licensed under a Creative Commons Attribution-ShareAlike 4.0 International License
