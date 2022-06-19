#include<SDL.h> //ne pas oublier
#include<SDL_ttf.h> //ne pas oublier
#include<iostream>
#include<fstream>
#include "config_sdl.h"
using namespace std;
const int LARGEUR = 12; //largeur fenetre
const int HAUTEUR = 16;  //hauteur fenetre
const int CARRE = 30;
const int TAILLEX = CARRE * LARGEUR + 120;
const int TAILLEY = CARRE * HAUTEUR;


void charger(const char* nom_fichier, SDL_Color tabcouleur[HAUTEUR][LARGEUR], SDL_Renderer* rendu) {
    int cptr = 0;
    ifstream entree(nom_fichier);
    char ligne[10];
    if (!entree)
        cout << "Probleme d'ouverture \n";
    else {
        for (int i = 0; i < HAUTEUR; i++) {
            for (int j = 0; j < LARGEUR; j++) {

                entree >> ligne;

                if (strcmp(ligne, "B") == 0) {
                    SDL_Color B = { 255, 255, 255 };
                    tabcouleur[i][j] = B;

                }
                if (strcmp(ligne, "N") == 0) {
                    SDL_Color N = { 0,0,0 };
                    tabcouleur[i][j] = N;

                }
                if (strcmp(ligne, "R") == 0) {
                    SDL_Color R = { 255,0,0 };
                    tabcouleur[i][j] = R;

                }
                if (strcmp(ligne, "V") == 0) {
                    SDL_Color V = { 106, 164, 30 };
                    tabcouleur[i][j] = V;

                }
                if (strcmp(ligne, "J") == 0) {
                    SDL_Color J = { 255, 255, 0 };
                    tabcouleur[i][j] = J;

                }
            }
            
        }
        SDL_Rect rect;
        for (int i = 0; i < HAUTEUR; i++) {
            for (int j = 0; j < LARGEUR; j++) {
                rect.x = 0 + CARRE * j;
                rect.y = 0 + CARRE * i;
                rect.w = CARRE;
                rect.h = CARRE;
                SDL_SetRenderDrawColor(rendu, tabcouleur[i][j].r, tabcouleur[i][j].g, tabcouleur[i][j].b, 255);
                SDL_RenderFillRect(rendu, &rect);
            }
        }
    }
}

void afficher(SDL_Color tabcouleur[HAUTEUR][LARGEUR], SDL_Renderer* rendu) {
    SDL_Rect rect;
    for (int i = 0; i < HAUTEUR; i++) {
        for (int j = 0; j < LARGEUR; j++) {
            rect.x = 0 + CARRE * j;
            rect.y = 0 + CARRE * i;
            rect.w = CARRE;
            rect.h = CARRE;
            SDL_SetRenderDrawColor(rendu, tabcouleur[i][j].r, tabcouleur[i][j].g, tabcouleur[i][j].b, 255);
            SDL_RenderFillRect(rendu, &rect);
        }
    }
}

void negatif(SDL_Color tabcouleur[HAUTEUR][LARGEUR], SDL_Renderer* rendu) {
    SDL_Rect rect;
    for (int i = 0; i < HAUTEUR; i++) {
        for (int j = 0; j < LARGEUR; j++) {
            rect.x = 0 + CARRE * j;
            rect.y = 0 + CARRE * i;
            rect.w = CARRE;
            rect.h = CARRE;
            SDL_SetRenderDrawColor(rendu, 255-tabcouleur[i][j].r, 255 - tabcouleur[i][j].g, 255 - tabcouleur[i][j].b, 255);
            SDL_RenderFillRect(rendu, &rect);
        }
    }
}

void charger_palette(SDL_Renderer* rendu) {
    
    SDL_Rect rect;
    rect.x = 375;
    rect.y = 30;
    rect.w = CARRE;
    rect.h = CARRE;
    SDL_SetRenderDrawColor(rendu, 0, 0, 0, 255);
    SDL_RenderFillRect(rendu, &rect);
    SDL_SetRenderDrawColor(rendu, 255, 255, 255, 255); //pinceau blanc
    SDL_RenderDrawRect(rendu, &rect); //on trace un rectangle vide

    rect.x = 435;
    rect.y = 30;
    rect.w = CARRE;
    rect.h = CARRE;
    SDL_SetRenderDrawColor(rendu, 255, 255, 255, 255);
    SDL_RenderFillRect(rendu, &rect);
    SDL_SetRenderDrawColor(rendu, 255, 255, 255, 255); //pinceau blanc
    SDL_RenderDrawRect(rendu, &rect); //on trace un rectangle vide

    rect.x = 375;
    rect.y = 90;
    rect.w = CARRE;
    rect.h = CARRE;
    SDL_SetRenderDrawColor(rendu, 255, 0, 0, 255);
    SDL_RenderFillRect(rendu, &rect);
    SDL_SetRenderDrawColor(rendu, 255, 255, 255, 255); //pinceau blanc
    SDL_RenderDrawRect(rendu, &rect); //on trace un rectangle vide

    rect.x = 435;
    rect.y = 90;
    rect.w = CARRE;
    rect.h = CARRE;
    SDL_SetRenderDrawColor(rendu, 106, 164, 30, 255);
    SDL_RenderFillRect(rendu, &rect);
    SDL_SetRenderDrawColor(rendu, 255, 255, 255, 255); //pinceau blanc
    SDL_RenderDrawRect(rendu, &rect); //on trace un rectangle vide

    rect.x = 375;
    rect.y = 150;
    rect.w = CARRE;
    rect.h = CARRE;
    SDL_SetRenderDrawColor(rendu, 255, 255, 0, 255);
    SDL_RenderFillRect(rendu, &rect);
    SDL_SetRenderDrawColor(rendu, 255, 255, 255, 255); //pinceau blanc
    SDL_RenderDrawRect(rendu, &rect); //on trace un rectangle vide

    rect.x = 435;
    rect.y = 150;
    rect.w = CARRE;
    rect.h = CARRE;
    SDL_SetRenderDrawColor(rendu, 0, 0, 255, 255);
    SDL_RenderFillRect(rendu, &rect);
    SDL_SetRenderDrawColor(rendu, 255, 255, 255, 255); //pinceau blanc
    SDL_RenderDrawRect(rendu, &rect); //on trace un rectangle vide
}



int main(int argn, char* argv[]) { //entête imposée
   //ouverture de la SDL
    //ouverture de la SDL
    if (SDL_Init(SDL_INIT_VIDEO) != 0) {
        cout << "Echec à l’ouverture";
        return 1;
    }


    TTF_Init();
    TTF_Font* font = TTF_OpenFont("C:\\Windows\\Fonts\\calibri.ttf", 25);


    //on crée la fenêtre
    SDL_Window* win = SDL_CreateWindow("Titre de la fenetre",
        SDL_WINDOWPOS_CENTERED,     //pos. X: autre option: SDL_WINDOWPOS_UNDEFINED
        SDL_WINDOWPOS_CENTERED,     //pos. Y: autre option: SDL_WINDOWPOS_UNDEFINED 
        TAILLEX,   //largeur en pixels    
        TAILLEY,   //hauteur en pixels
        SDL_WINDOW_SHOWN //d’autres options (plein ecran, resizable, sans bordure...)
    );
    if (win == NULL)
        cout << "erreur ouverture fenetre";


    //Création d’un dessin associé à la fenêtre (1 seul renderer par fenetre)
    SDL_Renderer* rendu = SDL_CreateRenderer(
        win,  //nom de la fenêtre
        -1, //par défaut
        SDL_RENDERER_ACCELERATED); //utilisation du GPU, valeur recommandée

    bool continuer = true;   //booléen fin de programme
    SDL_Event event;//gestion des évènements souris/clavier, 
      //SDL_Event est de type struct

    SDL_Color tab[HAUTEUR][LARGEUR];

    charger("Mystere.txt", tab, rendu);
    
    charger_palette(rendu);
    SDL_RenderPresent(rendu);

    while (continuer)
    {
        SDL_WaitEvent(&event);//attente d’un évènement
        switch (event.type) //test du type d’évènement
        {

        case SDL_QUIT: //clic sur la croix de fermeture
          //on peut enlever SDL_Delay
            continuer = false;
            break;

        case SDL_KEYDOWN:
            if (event.key.keysym.sym == SDLK_n) {
                negatif(tab, rendu);
                SDL_RenderPresent(rendu);
            }
            if (event.key.keysym.sym == SDLK_p) {
                afficher(tab, rendu);
                SDL_RenderPresent(rendu);
            }
            break;
        case SDL_MOUSEBUTTONUP://appui souris
            if (event.button.button == SDL_BUTTON_LEFT) {
                if (event.button.x > 375 && event.button.x<375 + 30 && event.button.y>30 && event.button.y < 30 + 30) {
                    SDL_SetRenderDrawColor(rendu, 0, 0, 0, 255);
                }
            }
            break;
    }
    
    //destruction du renderer à la fin
    SDL_DestroyRenderer(rendu);

    //destruction à la fin
    SDL_DestroyWindow(win);   //equivalent du delete
    SDL_Quit();
    return 0;
}
