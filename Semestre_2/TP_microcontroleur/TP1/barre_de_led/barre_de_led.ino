#include <Adafruit_NeoPixel.h>
#ifdef __AVR__
 #include <avr/power.h> // Required for 16 MHz Adafruit Trinket
#endif
#include "FeatherShieldPinouts.h"
Adafruit_NeoPixel strip(10, D2, NEO_GRB + NEO_KHZ800);
int start = 0;

void setup() {
  // put your setup code here, to run once:
  strip.begin(); // Initialize NeoPixel strip object (REQUIRED)
  strip.show();  // Initialize all pixels to 'off'
  Serial.begin(115000) ;
  pinMode(A2, INPUT);
  pinMode(A4, INPUT);
}
int i = 0;
void loop() {
  if (digitalRead(A2)== HIGH){
  start+=1;
  delay(100);
  }
  if (digitalRead(A4)== HIGH and start%2==0){
  i=0;
  }
  if (start%2==1){
    i++;
    strip.setPixelColor(0, strip.Color(0,0,0));
    strip.setPixelColor(1, strip.Color(0,0,0));
    strip.setPixelColor(2, strip.Color(0,0,0));
    strip.setPixelColor(3, strip.Color(0,0,0));
    strip.setPixelColor(4, strip.Color(0,0,0));
    strip.setPixelColor(5, strip.Color(0,0,0));
    strip.setPixelColor(6, strip.Color(0,0,0));
    strip.setPixelColor(7, strip.Color(0,0,0));
    strip.setPixelColor(8, strip.Color(0,0,0));
    strip.setPixelColor(9, strip.Color(0,0,0));
   
    if (digitalRead(A2)== HIGH){
      start+=1;
      delay(100);
    }
    if (digitalRead(A4)== HIGH and start%2==0){
      i=0;
     }
    
    int tmp=i;
    delay(1000);
    Serial.println(tmp) ;
    int cpt=0;
    while (tmp>0){
      if (tmp%2==1){
        strip.setPixelColor(cpt, strip.Color(200,0,0));
      }
      else{
        strip.setPixelColor(cpt, strip.Color(0,0,0));
      }
      cpt++;
      tmp=tmp>>1;
    }
    strip.show();
  }
}
