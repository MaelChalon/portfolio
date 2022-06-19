#include "FeatherShieldPinouts.h"
int tmp;
void setup() {
  // put your setup code here, to run once:
  pinMode (A2, INPUT);
  pinMode (A0, OUTPUT);
  Serial.begin(115200);
  pinMode (A4, INPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  //Serial.println(2700-analogRead(A4));
  tmp=((2700-analogRead(A4))/2700.0)*60+140;
  Serial.println(tmp);
  dacWrite(A0,tmp);
  
}
