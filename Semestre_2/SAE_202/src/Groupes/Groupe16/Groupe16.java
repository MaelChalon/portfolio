/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Groupes.Groupe16;

import java.util.ArrayList;
import java.util.Scanner;
import packMesClassesEtInterfaces.SAE202_Interface;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import static packMesClassesEtInterfaces.SAE202_Algos.calcul_caracteristiques;


/**
 *cette classe sert a appeler les methodes pour faire toute la modelisation et le chalenge de coloration.
 * elle ne posséde pas d'attribut.
 * 
 */
public class Groupe16 implements SAE202_Interface{
    
    /**
     * cette methode permet d'executer les coloration de tout les graphes stocker dans des dossiers passé en paramettre.
     * @param prefixeFichier correspond au debut du nom du chemin d'acces de chaque fichier contenant les graphes à charger. 
     * @param nbFichiers correspond au nombre de fichier que la methode utilisera.
     * @param millisecondes correspond au temps maximum que la methode aura pour chercher la meilleur cooloration de tous les fichiers.
     */
    @Override
    public void challenge(String prefixeFichier, Integer nbFichiers, Long millisecondes) {
        
    }
    
    /**
     * cette fonction permet de modeliser l'ensemble des plans de vols des avions depuis plusieurs fichiers csv. Cette methode s'arrête si le temps d'execution depasse le temps passé en paramètre.
     * @param prefixeFichier correspond au debut du nom du chemin d'acces de chaque fichier contenant les plans de vols à charger. 
     * @param nbFichiers correspond au nombre de fichier que la methode utilisera.
     * @param millisecondes correspond a utemps d'execution maximum.
     */
    @Override
    public void modelisation(String prefixeFichier, Integer nbFichiers, Long millisecondes) {
        long temps_debut = System.currentTimeMillis();
        long temps_courant;
        ArrayList <Aeroport> liste_Aeroport = new ArrayList<>();
        ArrayList<Vol> liste_Vols = new ArrayList<>();
        try {
            lectureAeroport("Donnees/aeroports.csv", liste_Aeroport);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Groupe16.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            FileWriter fw = new FileWriter("Resultat/modelisation-groupe16.csv");
            for (int i = 0; i < nbFichiers; i++) {
                temps_courant = temps_debut - System.currentTimeMillis();
                if(temps_courant >=millisecondes){
                    break;
                }
                liste_Vols.clear();
                try {
                    LectureVols("Donnees/" + prefixeFichier + i + ".csv", liste_Vols, liste_Aeroport);
                    Graph g;
                    g = this.creationGraphe(liste_Vols, liste_Aeroport);
                    HashMap<String, Object> caracteristique = new HashMap<>();
                    caracteristique = calcul_caracteristiques(g);
                    int nbNoeuds = (int)(caracteristique.get("nbNoeuds"));
                    int nbAretes = (int)(caracteristique.get("nbAretes"));
                    double degreMoyen = (double)(caracteristique.get("degreMoyen"));
                    int nbComposantes = (int)(caracteristique.get("nbComposantes"));
                    int diametre = (int)(caracteristique.get("diametre"));

                    String content = "groupe16;model-test"+ i +".csv;"+ nbNoeuds + ";" + nbAretes + ";" + degreMoyen + ";" + nbComposantes + ";" + diametre + '\n';
                    fw.append(content);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Groupe16.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Groupe16.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * cette methode permet de charger un fichier contenant les données des aéroports donné en paramètre dans une liste d'Aeroport.
     * @param nom_fichier correspond au nom du fichier a charger.
     * @param liste correspond à la liste dans laquelle les aeroport seront chargé.
     * @throws FileNotFoundException cette exeption est levé si le fichier passé en paramètre est introuvable.
     */
    public void lectureAeroport(String nom_fichier, ArrayList <Aeroport> liste) throws FileNotFoundException{
        FileInputStream file = new FileInputStream(nom_fichier);
        Scanner scan = new Scanner(file);
        
        while(scan.hasNextLine()){
            scan.useDelimiter(";");
            Aeroport aero = new Aeroport();
            String tmp2 = scan.next();
            if (tmp2.length()>3){
                aero.setCode((tmp2.charAt(1)+ "" )+ (tmp2.charAt(2)+ "" )+(tmp2.charAt(3)+ ""));
            }
            else{
                aero.setCode(tmp2);
            }
            aero.setNom(scan.next());
            
            aero.setDegres_latitude(Integer.parseInt(scan.next()));
            aero.setMinute_latitude(Integer.parseInt(scan.next()));
            aero.setSeconde_latitude(Integer.parseInt(scan.next()));
            aero.setLatitude(scan.next());
            
            aero.setDegres_longitude(Integer.parseInt(scan.next()));
            aero.setMinute_longitude(Integer.parseInt(scan.next()));
            aero.setSeconde_longitude(Integer.parseInt(scan.next()));
            scan.useDelimiter("\n");
            String tmp = scan.next().charAt(1)+"";
            aero.setLongitude(tmp);
            
            aero.set_degres_decimal();
            aero.set_x_y();
            liste.add(aero);
        }     
    }
    
    /**
     * cette methode permet de trouver l'indice de l'aeroport passé en paramètre.
     * @param liste correspond à la liste des aeroport dans laquelle on cherche l'aeroport.
     * @param code correspond au code d'identification de l'aeroport a chercher.
     * @return renvoie l'indice dans l'arrayliste d'aeroport de l'aeroport recherché ou -1 si il n'existe pas dans la liste.
     */
    public int rechercheAeroport(ArrayList <Aeroport> liste, String code){
        for (int i = 0; i< liste.size(); i++){
            String tmp = liste.get(i).getCode();
            if (code.equals(tmp)){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * cette methode permet d'implementer dans une arraylist de vol la liste des vols d'un fichier passé en paramètre.
     * @param nom_fichier correspond au nom du fichier a lire.
     * @param listeVols correspond a l'arraylist qui concervera la liste des vols.
     * @param listeAeroport correspond a la liste des aeroport par lequel les vols passe. ( cette liste n'est pas créée dans la fonction mais doit etre deja implementée).
     * @throws FileNotFoundException la methode leve une exception si le fichier passé en paramètre est introuvable.
     */
    public void LectureVols(String nom_fichier, ArrayList <Vol> listeVols, ArrayList <Aeroport> listeAeroport ) throws FileNotFoundException{
        FileInputStream file = new FileInputStream(nom_fichier);
        Scanner scan = new Scanner(file);
        
        while(scan.hasNext()){
            
            Vol v = new Vol();
            scan.useDelimiter(";");
            String tmp2 = scan.next();
            if(tmp2.length()>8){
                v.setIdentifiant((tmp2.charAt(1)+"")+(tmp2.charAt(2)+"")+(tmp2.charAt(3)+"")+(tmp2.charAt(4)+"")+(tmp2.charAt(5)+"")+(tmp2.charAt(6)+"")+(tmp2.charAt(7)+"")+(tmp2.charAt(8)+""));
            }
            else{
                v.setIdentifiant(tmp2);
            }
            v.setAeroportDepart(rechercheAeroport(listeAeroport, scan.next()));
            v.setAeroportArrive(rechercheAeroport(listeAeroport, scan.next()));
            v.setH_depart(Integer.parseInt(scan.next()));
            v.setMin_depart(Integer.parseInt(scan.next()));
            
            scan.useDelimiter("\n");
            String tmp = scan.next().charAt(1)+"";
            v.setTemps(Integer.parseInt(tmp));
            
            listeVols.add(v);
        }
    }
    
    /**
     * cette méthode permet de créer un graphe a partir d'une ArrayList de vols et d'aeroport. les noeud correspond a chaque vols et les aretes corresponde au potentiel conflit entre les vols.
     * @param listeVols correspond a l'ArrayList deja implementé des vols.
     * @param listeAeroport correspond à l'Arraylist des Aeroports deja implementé.
     * @return renvoie un graphe correspondant au plan de vols et des risque de collision entre les avions.
     */
    public Graph creationGraphe(ArrayList <Vol> listeVols, ArrayList <Aeroport> listeAeroport){
        Graph g = new SingleGraph("graphe");
        
        for (Vol v : listeVols) {
            g.addNode(v.getIdentifiant());
        }
        
        for (int i = 0; i< listeVols.size()-1; i++) {
            double x_depart_segment_i = listeAeroport.get(listeVols.get(i).getAeroportDepart()).getX();
            double y_depart_segment_i = listeAeroport.get(listeVols.get(i).getAeroportDepart()).getY();
            double x_arrive_segment_i = listeAeroport.get(listeVols.get(i).getAeroportArrive()).getX();
            double y_arrive_segment_i = listeAeroport.get(listeVols.get(i).getAeroportArrive()).getY();
            
            double a_segement_i = (y_arrive_segment_i - y_depart_segment_i)/(x_arrive_segment_i-x_depart_segment_i);
            double b_segment_i = y_depart_segment_i - (a_segement_i * x_depart_segment_i);
            
            for(int j = i+1; j< listeVols.size(); j++){
                double x_depart_segment_j = listeAeroport.get(listeVols.get(j).getAeroportDepart()).getX();
                double y_depart_segment_j = listeAeroport.get(listeVols.get(j).getAeroportDepart()).getY();
                double x_arrive_segment_j = listeAeroport.get(listeVols.get(j).getAeroportArrive()).getX();
                double y_arrive_segment_j = listeAeroport.get(listeVols.get(j).getAeroportArrive()).getY();
            
                double a_segement_j = (y_arrive_segment_j - y_depart_segment_j)/(x_arrive_segment_j-x_depart_segment_j);
                double b_segment_j = y_depart_segment_j - (a_segement_j * x_depart_segment_j);
                
                double x_intersect;
                double y_intersect;
                if(a_segement_i != a_segement_j){
                    x_intersect = (b_segment_j-b_segment_i)/(a_segement_i-a_segement_j);
                    y_intersect = x_intersect * a_segement_i + b_segment_i;
                    
                    if(x_intersect > Math.min(x_depart_segment_i, x_arrive_segment_i) && x_intersect < Math.max(x_depart_segment_i, x_arrive_segment_i)){
                        if(y_intersect > Math.min(y_depart_segment_i, y_arrive_segment_i) && y_intersect < Math.max(y_depart_segment_i, y_arrive_segment_i)){
                            if(x_intersect > Math.min(x_depart_segment_j, x_arrive_segment_j) && x_intersect < Math.max(x_depart_segment_j, x_arrive_segment_j)){
                                if(y_intersect > Math.min(y_depart_segment_j, y_arrive_segment_j) && y_intersect < Math.max(y_depart_segment_j, y_arrive_segment_j)){
                                    int temps_min_dep_i = listeVols.get(i).getH_depart()*60 + listeVols.get(i).getMin_depart();
                                    int temps_min_dep_j = listeVols.get(j).getH_depart()*60 + listeVols.get(j).getMin_depart();
                                    
                                    double dist1_i = Math.sqrt((x_depart_segment_i - x_arrive_segment_i) * (x_depart_segment_i - x_arrive_segment_i) + (y_depart_segment_i - y_arrive_segment_i) * (y_depart_segment_i - y_arrive_segment_i));
                                    double dist2_i = Math.sqrt((x_depart_segment_i - x_intersect) * (x_depart_segment_i - x_intersect) + (y_depart_segment_i - y_intersect) * (y_depart_segment_i - y_intersect));
                                    
                                    double dist1_j = Math.sqrt((x_depart_segment_j - x_arrive_segment_j) * (x_depart_segment_j - x_arrive_segment_j) + (y_depart_segment_j - y_arrive_segment_j) * (y_depart_segment_j - y_arrive_segment_j));
                                    double dist2_j = Math.sqrt((x_depart_segment_j - x_intersect) * (x_depart_segment_j - x_intersect) + (y_depart_segment_j - y_intersect) * (y_depart_segment_j - y_intersect));
                                    
                                    double vitesse_i = dist1_i/ listeVols.get(i).getTemps();
                                    double vitesse_j = dist1_j/ listeVols.get(i).getTemps();
                                                                        
                                    double temps_intersect_min = Math.min(temps_min_dep_i + (dist2_i/vitesse_i), temps_min_dep_j+(dist2_j/vitesse_j));
                                    double temps_intersect_max = Math.max(temps_min_dep_i + (dist2_i/vitesse_i), temps_min_dep_j+(dist2_j/vitesse_j));
                                    
//                                    System.out.println(temps_intersect_max-temps_intersect_min);
                                    if(temps_intersect_min + 15 >= temps_intersect_max){
                                        g.addEdge(listeVols.get(i).getIdentifiant()+listeVols.get(j).getIdentifiant(), listeVols.get(i).getIdentifiant(), listeVols.get(j).getIdentifiant());
                                    }
                                }
                            }
                        }
                    }
                }
                else{
                    int temps_min_dep_i = listeVols.get(i).getH_depart()*60 + listeVols.get(i).getMin_depart();
                    int temps_min_dep_j = listeVols.get(j).getH_depart()*60 + listeVols.get(j).getMin_depart();
                    if(listeVols.get(i).getAeroportDepart() == listeVols.get(j).getAeroportDepart()){
                        if (Math.abs(temps_min_dep_i - temps_min_dep_j ) <= 15){
                            g.addEdge(listeVols.get(i).getIdentifiant()+listeVols.get(j).getIdentifiant(), listeVols.get(i).getIdentifiant(), listeVols.get(j).getIdentifiant());
                        }
                    }
                    else if(listeVols.get(i).getAeroportDepart() == listeVols.get(j).getAeroportArrive()){
                        if (Math.abs(temps_min_dep_i - (temps_min_dep_j + listeVols.get(j).getTemps()) ) <= 15){
                            g.addEdge(listeVols.get(i).getIdentifiant()+listeVols.get(j).getIdentifiant(), listeVols.get(i).getIdentifiant(), listeVols.get(j).getIdentifiant());
                        }
                    }
                    else if(listeVols.get(i).getAeroportArrive() == listeVols.get(j).getAeroportArrive()){
                        if (Math.abs((temps_min_dep_i + listeVols.get(i).getTemps())- (temps_min_dep_j + listeVols.get(j).getTemps()) ) <= 15){
                            g.addEdge(listeVols.get(i).getIdentifiant()+listeVols.get(j).getIdentifiant(), listeVols.get(i).getIdentifiant(), listeVols.get(j).getIdentifiant());
                        }
                    }
                }
            }
        }
         return g;
    }
}