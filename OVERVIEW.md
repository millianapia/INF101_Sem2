# Oversikt

<b>Student:</b>

* Navn: Malin Wist Jakobsen
* BrukerNavn: xun007
* Epost: malin@j-j.no   / malin.jakobsen@student.uib.no

## Om funksjonaliteten
<b>Oversikt over oppførsel over objektene:</b>

- SimAnimal


Har bildet som en gul fisk, den har alle implementasjonene fra del 1. Den ser på SimRepellant som en fare, og SimFeed som mat

- SimFeed


  Har bildet som sjøgress. 

- SimRepellant


Har bilde som en manet. 
- simShark


Har bildet som en hai, den spiser sjøgress og SimSmallerAnimal(røde fisken). Hvis den har fått mat over normalvekten så blir den sakte og roper "I'm fat". Hvis den er under halvparten a normalvekten så blir den ekstremt fort. Hvis den er under 1/10 av normalvekten så sier den "i'm dying" og så forsvinner den
Den har en  
- SimSmallerAnimal


Har bildet som en rød fisk. Er redd for haien og SimRepellant og spiser sjøgress. Har samme logikk som haien når det gjelder vekt. Når den er for stor, for liten osv. Forskjellen er at den ikke leter etter beste mat. Den spiser alt som er rundt seg (i synsvinkelen).  Den er litt fortere enn haien


<b>Oversikt over oppførsel over klassene:</b>

- SimAnimal:

Klassen extender og AbstractMovingObject for dette er et objekt som skal bevege seg. Den implementerer også ISimListener for å kunne bruke lyttere. I draw metoden så sjekker jeg om bildet er rett vei i forhold til retningen til fisken. Den snur seg hvis den ser mot retningen mellom 90 og -90 grader. Jeg har gjort det litt annerledes i getBestFood metoden, der jeg heller sjekker to objekter opp mot hverandre. Istedet for å legge det i en liste.  På step metoden har jeg lagt til en if som senker vekten for hvert step og den dør hvis det er under så så  mye i vekt. Har og lagt til en for løkke som finner alle objektene i nærheten på en viss radius. Og har en if og en else if som sjekker om objektet er enten spiselig eller farlig. Da har de ulike oppgaver som de skal gjøres.
Lagt til fabrikken og eventhappend metodene nederst. 

- simShark:

Samme som i begynnelsen av koden av SimAnimal, men er forskjellig i metoden step. Her har jeg lagt til flere muligheter når det gjelder vekten til haien. Den går ned hvert steg med 0.005 "kg" og hvis vekten er under 40 så skal den si at den er sulten og blir raskere. Hvis den er under 10 så skal den si den dør og så blir den ødelagt med super.destroy. Hvis vekten er over normalvekten så sier den at den er feit, og blir saktere. Har og lagt til i for løkken at hvis den finner et objekt av instansen SimSmallerAnimal så skal det "spise det". 


- SimSmallerAnimal: 

Mye samme som i simShark, eneste er at den løper fra haien som den løper fra SimRepellant. 


<b>Ekstra kommentarer (feil, endringer): </b>

Endret litt på testen på SimAnimalAvoidingTest, test nr 2 gikk alt for mange steps, så gjorde det mindre. La til en test som sjekket om SimSmallerAnimal unngikk simShark

Endret også på getBestFood, hvor jeg ikke brukte collections, men heller bare sammenlignet to objekter og returnerte det beste. 

La til bakgrunn i SimMain metoden og flippet bildet opp ned for å få det rett vei. 

En ting som jeg hadde håpet på å fikse var at alle objektene sa det samme når noe skjedde
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


Man kan justere posisjonen enten i Setup klassen der man legger alle objektene til i simulatoren. Eller bruke turnTowards for å gå mot ønsket posisjon. 
Hjelpemetoden distanceTo brukes til å finne distansen til objektet man har i parantesene, eller distansen til en plassering. Som returnerer en double verdi. 
Hjelpemetoden directionTo er for å finne retningen til enten et objekt eller en posisjon. Returnerer en retning.


* AbstractMovingObject har en metode accelerateTo for å endre på farten. Kunne det være smart å gjøre speed feltvariablen private?

Er en mer fleksibel måte å ha det som en metode. 

* Vi har ikke laget noen hjelpemetoder for å justere på retningen. Hva må du gjøre for å endre retning i en subklasse? Burde vi ha metoder også for å endre retning?

I SimAnimal så bruker jeg turnTowards for å justere retningen min gradvis. Dette er en fin måte å endre retningen så man kan justere vinkelen i forhold til andre objekter. 
For å justere retningen så man bruke metoden turnTowards og velge den retningen man vil gå mot. Er ikke nødvendig med en metode for å endre retning. I hvertfall ikke sånn det er nå, er ingen objekter som skal endre retningen uten å gå mot den retningen. 



* Vi har ingen public metoder for å endre på posisjon, retning, osv. Hadde det vært lurt å ha det? Hvorfor / hvorfor ikke?

For forskjellige objekter har forskjellige holdninger til når de bare farer rundt og hvordan de skal oppføre seg i forhold til hverandre. 

-------------------------------------------------------------------------------

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

* underwater.png. https://opengameart.org/content/fish-pack This work is licensed under CC BY 3.0

* seaweed.png, redfish.png, shark.png, yellowfish.png, jellyfish.png https://opengameart.org/content/fish-pack This work is licensed under Public domain
