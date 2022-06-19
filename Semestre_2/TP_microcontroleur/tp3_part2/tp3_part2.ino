#include "FeatherShieldPinouts.h"
bool on = false;
int dernier=0;
volatile int interruptCounter;
hw_timer_t * timer = NULL;
int oldValue;

void IRAM_ATTR timerISR(){
  interruptCounter++;
}

void IRAM_ATTR boutonISR(){
  if (millis()-dernier >= 200){
    on = !on;
  Serial.println("changement");
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
  timerAlarmWrite(timer, 1000000,true);
  timerAlarmEnable(timer);
 }

void loop() {
  // put your main code here, to run repeatedly:

  if (interruptCounter != oldValue){
    digitalWrite(A0, HIGH);
    oldValue = interruptCounter;
  }
 digitalWrite(A0,LOW);
}
