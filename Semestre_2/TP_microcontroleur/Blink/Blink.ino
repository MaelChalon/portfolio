/*
  Blink

  Turns an LED on for one second, then off for one second, repeatedly.

  Most Arduinos have an on-board LED you can control. On the UNO, MEGA and ZERO
  it is attached to digital pin 13, on MKR1000 on pin 6. LED_BUILTIN is set to
  the correct LED pin independent of which board is used.
  If you want to know what pin the on-board LED is connected to on your Arduino
  model, check the Technical Specs of your board at:
  https://www.arduino.cc/en/Main/Products

  modified 8 May 2014
  by Scott Fitzgerald
  modified 2 Sep 2016
  by Arturo Guadalupi
  modified 8 Sep 2016
  by Colby Newman

  This example code is in the public domain.

  https://www.arduino.cc/en/Tutorial/BuiltInExamples/Blink
*/
#include "FeatherShieldPinouts.h"
// the setup function runs once when you press reset or power the board
int buttonState = 0;
int buttonState2=0;
void setup() {
  // initialize digital pin LED_BUILTIN as an output.
  pinMode(A0, OUTPUT);
  pinMode(D4, OUTPUT);
  Serial.begin(115000);
  pinMode(A2, INPUT);
  pinMode(A4, INPUT);
}

// the loop function runs over and over again forever
void loop() {
  if (digitalRead(A2)== HIGH){
    buttonState+=1;
    delay(100);
  }
  if (digitalRead(A4)== HIGH){
    buttonState2+=1;
    delay(100);
  }
  if(buttonState%2==1){
    digitalWrite (A0, HIGH);
  }
  else{
    digitalWrite (A0, LOW);
  }
  
  if(buttonState2%2==1){
    digitalWrite (D4, HIGH);
  }
  else{
    digitalWrite (D4, LOW);
  }
   
}
