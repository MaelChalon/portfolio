#include <Adafruit_NeoPixel.h>
#ifdef __AVR__
 #include <avr/power.h> // Required for 16 MHz Adafruit Trinket
#endif
#include "FeatherShieldPinouts.h"
Adafruit_NeoPixel strip(10, D2, NEO_GRB + NEO_KHZ800);
bool on = false;
int dernier=0;
bool tmp=false;

volatile int interruptCounter;
hw_timer_t * timer=NULL;
int oldVallue;
bool etat=false;
int pos=0;
bool retour=false;
bool retour_couleur=false;
int couleur=10;

void IRAM_ATTR timerISR(){
  tmp=true;
  
  if (pos==8){
    retour=true;
  }
  
  if (pos==0){
    retour=false;
  }
  
  if (on){
    if(retour==true){
      pos--;
    }
    else{
      pos++;
    }
  }
   if (couleur == 200){
      retour_couleur=true;
   }
   if(couleur == 10){
    retour_couleur=false;
   }
   if(retour_couleur){
    couleur-=10;
   }
   else{
    couleur+=10;
   }
   
}

void IRAM_ATTR boutonISR(){
  if (millis()-dernier >= 200){
    on = !on;
    dernier = millis();
  }
}


void setup() {
  // put your setup code here, to run once:

  pinMode(A2,INPUT);
  attachInterrupt (A2, boutonISR, FALLING);
  Serial.begin(115000);

  timer = timerBegin(0,80,true);
  timerAttachInterrupt(timer, &timerISR, true);
  timerAlarmWrite(timer, 100000,true);
  timerAlarmEnable(timer);

  strip.begin(); // Initialize NeoPixel strip object (REQUIRED)
  strip.show();  // Initialize all pixels to 'off'
 }

void loop() {
  if (on and tmp){
    tmp=false;
    strip.clear();
    strip.setPixelColor(pos, strip.Color(couleur,couleur,couleur));
    strip.setPixelColor(pos+1, strip.Color(couleur,couleur,couleur));
    strip.show();
  }
 
}
