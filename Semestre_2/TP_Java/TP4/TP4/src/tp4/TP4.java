/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author p2103455
 */
public class TP4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        ArrayList<String> list = new ArrayList<>();
//        
//        list.add("Bleu");
//        list.add("Vert");
//        list.add("Rouge");
//        list.add("Violet");
//        list.add("Orange");
//        
//        System.out.println(list);
//        System.out.println(" ");
//        for (int i = 0; i < list.size(); i++) {
//            System.out.print(list.get(i) + " / ");
//        }
//        System.out.println(" ");
//        
//        
//        System.out.println(list.size());
//        list.add(0, "Jaune");
//        
//        System.out.println(list);
//        
//        System.out.println(list.get(3));
//        
//        String tmp = list.remove(2);
//        System.out.println(tmp);
//        
//        System.out.println(list.contains("Blanc"));
//        System.out.println(list.contains("Rouge"));
//        
//        Collections.sort(list);
//        System.out.println(list);
//        
//        Collections.shuffle(list);
//        System.out.println(list);
//        
//        System.out.println(list.subList(2, 4));
//        
//        Collections.swap(list, 1, 0);
//        System.out.println(list);
//        
//        ArrayList<String> new_List = new ArrayList<>();
//        new_List.add("55");
//        new_List.add("moi");
//        
//        list.addAll(new_List);
//        System.out.println(list);
//        
//        list.clear();
//        System.out.println(list);
        
//        ArrayList <Note> liste_Note = new ArrayList<>();
//        Note n = new Note("Algo", 12.8);
//        liste_Note.add(n);
//        n = new Note("Java", 12.8);
//        liste_Note.add(n);
//        n = new Note("Maths", 12.8);
//        liste_Note.add(n);
//        n = new Note("tmp", 12.8);
//        liste_Note.add(n);
//        n = new Note("salut", 12.8);
//        liste_Note.add(n);
//        
//        System.out.println(liste_Note);
//        
//        for (int i = 0; i < liste_Note.size(); i++) {
//            System.out.print(liste_Note.get(i) + " / " );
//        }
//        System.out.println("");
//        
//        n = new Note("C++", 18.2);
//        liste_Note.add(0, n);
//        System.out.println(liste_Note);
//        
//        System.out.println(liste_Note.get(3));
//        n=new Note("C++", 11.9);
//        System.out.println(liste_Note.contains(n));
//        
//        n=new Note("Algo", 12.8);
//        System.out.println(liste_Note.contains(n));
//        
//        Collections.sort(liste_Note);
//        System.out.println(liste_Note);
//        
//        ArrayList <Note> list_2 = new ArrayList<>(liste_Note.subList(2,4));
//        
//         n = new Note("salut",124.234);
//        list_2.add(n);
//        System.out.println(list_2);
//        System.out.println(liste_Note);
//        
//        liste_Note.addAll(list_2);
//        System.out.println(liste_Note);

        HashMap<String, Double> map = new HashMap<>();
        map.put("Algo", 11.9);
        map.put("Maths", 9.5);
        map.put("Anglais", 12.3);
        
        System.out.println(map.get("Maths"));
        
        System.out.println(map.keySet());
        
        System.out.println(map.entrySet());
       
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            System.out.println(entry);
            
        }
        map.put("Algo", 13.6);
        System.out.println(map);
        // remplace l'anciene valeur associé a la clef "Algo"
        
        System.out.println(map.get("C++"));
        // la clef "C++" n'est pas dans la map
        
        System.out.println(map.containsValue(12.3));
        
    }

   
    
}
