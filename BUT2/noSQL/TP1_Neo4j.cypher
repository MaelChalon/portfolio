//afficher
match(n) return n

// afficher avec un where
match(p:personne)
where p.age>30
return p


//afficher avec un where sur un lien et return 1 champs
match(p:personne)-[:possede]->(:livre)
where p.age>30
return p.nom


//afficher avec un where sur un lien et return 1 champs
match(p:personne)-[:possede]->(l:livre)
where l.titre = "Finding Audrey"
return p.nom


//afficher avec un where sur un lien et return 1 champs
match(p:personne)-[r:lire]->(l:livre)
where p.nom = "Bruno"
return l.titre, r.note

//afficher avec un where sur un lien et return 1 champs
match(p:personne)-[r:lire]->(l:livre)
return p.nom, count(r) as nombre
order by nombre desc

//ajouter des nouveaux champs
MATCH (l:livre)
where l.titre=~'.*(kite runner)|(The firm).*'
SET l+= { BestSeller: "OUI"}

//case
match(p:personne)
case 
    when p.age>18 then set p+={catLect: "Adulte"}
    WHEN p.age<11 then set p+={catLect: "Enfant"}
    else set p+={catLect: "Jeune Adulte"}
end as result

// compter le nombre de noeud
match(l:livre)
return count(l)


//create node
create(:personne{nom : "Albert",age : 33})
create(:personne{nom : "Lina",age : 16})
create(:personne{nom : "Frantz",age : 25})
create(:personne{nom : "lila",age : 42})
create(:livre{titre : "Digital fortress",auteur : "Dan Brown"})
create(:livre{titre : "The firm",auteur : "John Grisham"})
create(:livre{titre : "Finding Audrey",auteur : "Sophie Kinsella"})
create(:livre{titre : "The kite runner",auteur : "Khaled Hosseini"})

//oppération sur des lien en chaine
match(p:personne)-[r:lire]->(:livre)<-[r2:lire]-(p2:personne)
where p.nom = "Lina"
return p2.nom


//schema de la base
call db.schema.visualization()

//set avec un case


//sous requete de calcule de moyenne
match(p:personne)-[r:lire]->(l:livre)
with p, count(r) as moyenne
return p.nom as nom, avg(moyenne) as moy

//une association
MATCH (p:personne),(l:livre)
WHERE p.nom = 'Albert' AND l.titre = 'Digital fortress'
CREATE (p)-[r:possede]->(l)RETURN r

//une association avec attribut
MATCH (p:personne),(l:livre)
WHERE p.nom = 'Albert' AND l.titre = 'Digital fortress'
CREATE (p)-[r:lire{note:8}]->(l)RETURN r