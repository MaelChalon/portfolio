<?php

namespace App\Controller;

class TicketsController extends AppController
{
    public function listing(){

    }

    public function edit(){

    }

    public function add(){
        $ticket = $this->Tickets->newEmptyEntity();
        if($this->Tickets->save($ticket)){
            $this->Flash->success("creation succeded");
            //return $this->redirect(['action' => "edit", $tickets->id]);
        }
        else{
            $this->Flash->error("Creation failled");
        }
    }

    public function delete(){

    }
}
