#include "FeatherShieldPinouts.h"
int i=120;
bool smooth=false;
void setup() {
  // put your setup code here, to run once:
  Serial.begin(115000);
  pinMode(A0, OUTPUT);
  pinMode(A1, INPUT);
}

void loop() {
  
  if(digitalRead(A1)==LOW){
    delay(150);
    
    if (smooth==false){
     smooth=true;
    }
    else{
      smooth=false;
    }
  }
  delay(100);
  if(smooth==false && i<200){
      i+=5;
   }
   if (smooth==true && i>100){
     i-=5;
   }
  
  
  dacWrite(A0,i);
  Serial.println(i);
  Serial.println(smooth);
  Serial.println((i*3.3)/255);
  
  
}
