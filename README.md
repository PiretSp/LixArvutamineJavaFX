#i200 Loetavusindeksi LIX arvutamine

Loetavusindeks LIX on välja töötatud rootslase Björnssoni poolt 1968. aastal. Sellise programmi eesmärgiks on välja arvutada teksti keerukus. See on vajalik, kuna lugemis- ja kirjutamisraskustega õpilaste puhul on vaja tekste lihtsustada - pikad sõnad (7 või rohkem tähte) asendada lühematega ja laused teha võimalikult lühikeseks. Lugemisraskustega õpilastel on pikki sõnu ja lauseid keeruline mõista.



**************** PROGRAMMI KIRJELDUS ****************

Programmiga on võimalik analüüsida loetavusindeksit LIX ja lisada lihtsustatud tekst andmebaasi. 

1) Loetavusindeksi LIX arvutamisel:
* kasutaja sisestab teksti, mida on vaja analüüsida (kopeerib, kirjutab ümber vms).
* programm:
- loeb kokku kõik tekstis olevad sõnad (A),
- loeb kokku tekstis olevad laused (B),
- arvutab lause keskmise pikkuse ehk LKP (C=A/B),
- leiab tekstis pikad sõnad ehk sõnad, mis on 7 tähte või rohkem (D),
- leiab keeruliste sõnade osakaalu tekstis (E=D*100/A),
- arvutab loetavusindeksi (LIX=C+E).
* Programm kuvab kasutajale vastuse, kas milline on teksti raskusaste (sõnade arv, lausete arv, LKP, pikkade sõnade arv, LIX).

Teksti keerukus (orienteeruvad näitajad): 
* LIX 20 = väga lihtne (lausepikkus 9, pikki sõnu 11%) 
* LIX 30 = lihtne (lausepikkus 13, pikki sõnu 17%) 
* LIX 40 = keskmine (lausepikkus 16, pikki sõnu 24%) 
* LIX 50 = raske (lausepikkus 19, pikki sõnu 31%) 
* LIX 60 = väga raske (lausepikkus 22, pikki sõnu 38%)

NB!
* LIXi arvutamisel tuleb arvestada, et sellega saab kontrollida ainult seotud teksti, st mitte luuletusi, näidendeid, sisukordi, sõnaloendeid, pealkirju ja piltide allkirju. 
* Arve, lühendeid ja sümboleid arvestatakse kui sõnu, kirjavahemärkide kohta see ei kehti. 
* Sõnu eraldatakse tühikute ja kirjavahemärkidega.
* Lauseid eraldatakse lauselõpumärkide, järgmise rea alguse (enter tühik) või jutumärkidega.
* Programm loeb eraldi lauseteks jutumärkidega eraldatud lauseosad (st saatelause ja otsekõne on eraldi laused. Näiteks: Mikk ütles: "Mine poodi, Mari. Meil ei ole süüa." Näite puhul on tegemist kolme lausega. 


2) Peale teksti analüüsimist on kasutajal võimalik tekst lisada andmebaasi. Selleks:
* Kasutaja sisestab vastavatesse lahtritesse autori, pealkirja, kommentaari ja teksti.



**************** KASUTUSJUHEND ****************

Programmi kasutamine on lihtne, midagi keerulist kasutaja tegema ei pea.

1. AKEN - kasutaja peab valima, mida ta soovib teha:
* loetavusindeksi LIX arvutamine;
* teksti andmebaasi lisamine;
* programmi sulgemine.

2. AKEN ehk LIX arvutamine - kasutaja sisestab ettantud kasti teksti, mida soovib analüüsida. Kui ta ei sisesta teksti ja vajutab analüüsimise nuppu, siis annab programm teate, et tuleb sisestada tekst. Selles aknas on kasutaal võimalik valida, kas ta:
* soovib arvutada teksti keerukust;
* liigub esimesse aknasse;
* sulgeb programmi.

3. AKEN ehk analüüsi tulemuste kuvamine - kasutajale kuvatakse analüüsi tulemused (sõnade lausete arv, LKP, pikkade sõnade arv, LIX). Kasutajal on võimalik valida, mida soovib teha:
* liigub tagasi esimesse aknasse;
* liigub neljandasse aknasse;
* sulgeb programmi.

4. AKEN ehk teksti sisestamine andmebaasi - kasutaja sisestab etteantud lahtritesse autori, pealkirja, kommentaari ja teksti. Kui kasutaja ei täida kõiki väljasid, siis tuleb errori teade. Kasutajal on selles aknas võimalik valida, mida ta soovib selles aknas teha:
* salvestab teksti andmebaasi;
* liigub esimesele lehele;
* sulgeb programmi.

5. AKEN ehk tänusõnum - kui kasutaja on lisanud teksti andmebaasi, siis kuvatakse tänusõnum. Selles aknas on kasutajal võimalik valida, kas ta:
* liigub tagasi esimesse aknasse;
* lisab veel ühe teksti andmebaasi;
* sulgeb programmi.



**************** MIS ON VEEL PROGRAMMIST PUUDU ****************

* Koos numbriliste analüüsitulemustega väljastab programm analüüsitud teksti, kus on markeeritud keerulised sõnad (7 ja rohkem tähte).

* Programm loeb otsekõne ja saatelause eraldi lauseteks vaid siis, kui saatelause eelneb otsekõnele. Näiteks: Mari ütles: "Peeter, mine poodi!" Näite puhul on tegemist kahe eraldi lausega. Kui otsekõnele järgneb saatelause, siis arvestatakse see ühe lausena. Näiteks: "Peeter, mine poodi!" ütles Mari. Selleks peaks programm lausete eraldamisel arvestama üksteisele järgnevat koolonit, tühikut ja jutumärkide algust (: "), kuna see on näitab, et saatelause on esimesel kohal. 

* Programm arvestab lausete eraldamisel lisaks lauselõpumärkidele ka suurt algustähte. Kui lause sees on punktid (nt järgarvude kasutamisel), siis loeb programm need eraldi lauseteks. Üldiselt on sel juhul järgnev sõna väikese tähega (Nt Täna on 28. september), kuid kui järgnev sõna peaks olema suure algustähega, siis on olukord programmi jaoks keerulisem (Nt Ta on enda suguvõsa 17. Alexander). Samas see ei muudaks teksti keerukust kuigi palju, kui programm need ka eraldi lauseteks arvutaks, sest sellise lause esinemise tõenäosus on küllaltki väike.

* Kasutajal on võimalus andmebaasis olevaid tekste otsida ja väljastada.




