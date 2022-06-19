-- 1)

select g1.parent groupe, g2.idGroupe a_pour_petit_fils
from Groupes g1 join Groupes g2 on g1.idGroupe = g2.parent
where g1.parent is not null;

-- 2)

select g1.idGroupe idGroupe, g2.idGroupe est_inclus_dans
from Groupes g1 join Groupes g2 on g1.parent = g2.idGroupe
union 
select g2.idGroupe idGroupe, g1.parent est_inclus_dans
from Groupes g1 join Groupes g2 on g1.idGroupe = g2.parent
where g1.parent is not null;