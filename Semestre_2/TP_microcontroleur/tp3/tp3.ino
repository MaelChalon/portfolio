#include "FeatherShieldPinouts.h"
bool on = false;
int dernier=0;
bool tmp=false;

volatile int interruptCounter;
hw_timer_t * timer=NULL;
int oldVallue;
bool etat=false;

void IRAM_ATTR timerISR(){

  etat=!etat;
  tmp=true;
}

void IRAM_ATTR boutonISR(){
  if (millis()-dernier >= 200){
    on = !on;
    dernier = millis();
  }
}


void setup() {
  // put your setup code here, to run once:
  pinMode(A0, OUTPUT);
  pinMode(D4, OUTPUT);
  pinMode(A2,INPUT);
  attachInterrupt (A2, boutonISR, FALLING);
  Serial.begin(115000);

  timer = timerBegin(0,80,true);
  timerAttachInterrupt(timer, &timerISR, true);
  timerAlarmWrite(timer, 100000,true);
  timerAlarmEnable(timer);
 }

void loop() {
  if (on and tmp){
    tmp=false;
    digitalWrite(A0, etat);
    digitalWrite(D4, !etat);
    
  }
 
}
