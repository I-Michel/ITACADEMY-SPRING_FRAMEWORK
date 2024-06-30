ENUNCIAT DE L'EXERCICI

SPRINT 5: Spring Framework Avançat
Tasca 1: Spring boot API rest + Aplicació web
Nivell 2: Exercici API Rest CRUD amb MySQL

********************************************************************************************
En aquesta tasca faràs un CRUD (Create, Read, Update, Delete) que pugui ser cridat com a API
Rest i, també, com aplicació web.

Aprendràs a usar correctament els verbs HTTP i a gestionar els codis de resposta.
********************************************************************************************

EXERCICI: Exercici API Rest CRUD amb MySQL

Accedeix a la pàgina -> https://start.spring.io/, i genera un projecte Spring boot amb les següents
característiques:

-------------------------------------------------------------------------------
* PROJECT (gestor de dependències): Maven.

* LANGUAGE: Java.

* SPRING BOOT: La darrera versió estable.

* PROJECT METADATA:
- Group: cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02
- Artifact: S05T01N02CognomsNom
- Name: S05T01N02CognomsNom
- Description: S05T01N02CognomsNom
- Package name: cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02

* PACKAGIN: Jar

* JAVA: Mínim versió 11

* DEPENDENCIES: Spring Boot DevTools, Spring Web, Spring Data JPA, MySQL Driver
-------------------------------------------------------------------------------

Tenim una entitat anomenada FlorEntity, que disposa de les següents propietats:

- Integer pk_FlorID
- String nomFlor
- String paisFlor

També tenim una DTO anomenada FlorDTO, que tindrà les mateixes propietats que
l’entitat Sucursal, afegint-ne una:

- String tipusFlor.

Aquesta propietat, en funció del país d'origen de la flor, haurà d’indicar si és
“UE” o “Fora UE”. Per a fer això, pots tenir una llista privada a la mateixa DTO
(per exemple: List<String> països), amb els països que formen part de la UE.

Aprofitant l’especificació JPA, hauràs de persistir l’entitat FlorEntity a una base
de dades MySQL, seguint el patró MVC.

El consell és que FlorDTO la facis servir al Controller, i FlorEntity al Repository.
La capa de serveis serà l’encarregada de fer la traducció entre les dues.

Per a això, depenent del package principal, crearàs una estructura de packages, on
ubicaràs les classes que necessitis:

- cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.controllers
- cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.model.domain
- cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.model.dto
- cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.model.services
- cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.model.repository

La classe ubicada al paquet controllers (FlorController, per exemple), haurà de ser
capaç de donar resposta a les següents peticions per actualitzar i consultar informació:

- http://localhost:9001/flor/add
- http://localhost:9001/flor/update
- http://localhost:9001/flor/delete/{id}
- http://localhost:9001/flor/getOne/{id}
- http://localhost:9001/flor/getAll

Com pots veure, a l’arxiu application.properties, hauràs de configurar que el port a usar sigui el 9001.

**********************************************************************************************
IMPORTANT:

Hauràs de tenir en compte les bones pràctiques de disseny de les API, fent servir correctament
els codis d'error i les respostes en cas d'invocacions incorrectes. (Pots consultar informació
sobre ResponseEntity).

Has d’incloure swagger perquè quasevol desenvolupador/a pugui tenir una idea ràpida dels
recursos de que disposa l’API.
**********************************************************************************************