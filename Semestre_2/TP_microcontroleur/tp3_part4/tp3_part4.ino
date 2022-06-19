#define pin_B 32
#define pin_C 14

#include "FeatherShieldPinouts.h"
#include <Adafruit_SH110X.h>
Adafruit_SH1107 display = Adafruit_SH1107(64, 128, &Wire);

bool on = false;
int dernier_b=0;
int dernier_c=0;
bool tmp=false;
volatile int cptr=0;

volatile int interruptCounter;
hw_timer_t * timer=NULL;
int oldVallue;

void IRAM_ATTR timerISR(){
  if (on){
    cptr++;
  }
  
}

void IRAM_ATTR boutonISR(){
  if (millis()-dernier_b >= 200){
    on = !on;
    dernier_b = millis();
  }
}

void IRAM_ATTR bouton2ISR(){
  Serial.println("salut");
  if (millis()-dernier_c >= 200 && !on){
    cptr=0;
    dernier_c = millis();
  }
}

void setup() {
  // put your setup code here, to run once:
  display.begin(0x3c, true);
  display.clearDisplay();
  display.setRotation(1);
  
  

  timer = timerBegin(0,80,true);
  timerAttachInterrupt(timer, &timerISR, true);
  timerAlarmWrite(timer, 100000,true);
  timerAlarmEnable(timer);

  pinMode(pin_B, INPUT);
  pinMode(pin_C, INPUT);
  attachInterrupt (pin_B, boutonISR, FALLING);
  attachInterrupt (pin_C, bouton2ISR, FALLING);

  Serial.begin(115000);
}

void loop() {
  // put your main code here, to run repeatedly:
  int h=cptr/(60*60);
  int minute=(cptr-h*(60*60))/60;
  int second = cptr-minute*60;
  display.clearDisplay();
  display.setTextSize(2);
  display.setTextColor(SH110X_WHITE);
  display.setCursor(0,0);
  display.println((String)h + "h " + (String)minute + "m " + (String)second + "s ");
  display.display();
  Serial.println(cptr);
}
