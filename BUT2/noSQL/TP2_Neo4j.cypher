// Adherent de sexe masculin
match(a:Adherent)
where a.civilite = "Mr."
return a

// les 3 sports les plus pratiqué
match(s:Sport)<-[p:PRATIQUE]-(a:Adherent)
return s.splibelle, count(s.spid) as nbPratique
order by nbPratique desc
limit 3

// lister le nombre de sport par adherent
match(a:Adherent)optional match(a)-[p:PRATIQUE]->(s:Sport)
return a.nom, count(p)



// lister le nombre de sport par adherent + lister des sports 
match(a:Adherent) optional match(a)-[p:PRATIQUE]->(s:Sport)
return a.nom, count(p), collect(s.splibelle)



//load csv
LOAD CSV WITH HEADERS FROM 
'https://liris.cnrs.fr/hamida.seba/IUTNeo4j/adherent.csv' AS line
CREATE (:Adherent { 
adhid: toInteger(line.adhid), 
nom: line.nom, 
prenom: line.prenom,
civilite: line.civilite,
date_naiss:line.date_naiss,
tel:line.tel})

//load csv liaison
LOAD CSV WITH HEADERS 
FROM 'https://liris.cnrs.fr/hamida.seba/IUTNeo4j/pratique.csv' AS line
MERGE (a : Adherent { adhid : toInteger(line.adhid) })
MERGE (s : Sport { spid : toInteger(line.spid) })
CREATE (a)-[:PRATIQUE]->(s);

// nom et prenom d'adherent
match(a:Adherent)
return a.nom, a.prenom

// nombre de sport pratiqué par plus de 8 adherent
match(s:Sport)<-[p:PRATIQUE]-(a:Adherent)
with s, count(a.adhid) as nb
where nb > 8
return s

// ordonner les adherent sans sport
match(a:Adherent)
where not (a)-[:PRATIQUE]->(:Sport)
return a.nom, a.prenom
order by a.nom, a.prenom


//personne pratiquant l'escrime
match(a:Adherent)-[:PRATIQUE]->(s:Sport)
where s.splibelle = "Escrime"
return a.nom


// recuperer les adherent pratiquant les memes sports que VALOGNES MARC 
match(a:Adherent)-[p:PRATIQUE]->(s:Sport)<-[p2:PRATIQUE]-(a2:Adherent)
where a.nom = "VALOGNES" AND a.prenom = "MARC"
return a2.nom, a2.prenom, count(s), collect(s.splibelle)