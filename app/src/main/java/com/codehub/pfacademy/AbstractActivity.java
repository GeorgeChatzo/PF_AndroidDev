package com.codehub.pfacademy;

//Με τον ορο abstract θα την κληρονομησουν όσοι την κανουν extend!

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class AbstractActivity extends AppCompatActivity {

    abstract void initial();

    abstract void running();

    abstract void stopped();

    abstract void destroyOperation();

        //3 πρωτα σταδια ενος activity
    //Δημιουργια activity -> background thread
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //οριζουμε το content view
        setContentView(R.layout.activity_main);
        //Αν βαλω κωδικα εδω θα αργησει η εφαρμογη να τρεξει
        //αρα τα φορτωνουμε στην ui thread ολα και μετα ξεκιναμε
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    //Μετάβαση απο background -> σε UI thread
    //Τωρα ξεκιναω να μεταφερω στο foreground
    @Override
    protected void onStart() {
        super.onStart();
    }

    //σημαινει οτι το activity μολις δημιουργθηκε αλλα μόλις καλεστεί αυτή
    //είναι το activity στο ui thread
    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        initial();
    }

    //Καμία διαφορά με την onResume
    @Override
    protected void onPostResume() {
        super.onPostResume();
        running();
    }

    //Ειναι έτοιμο προς χρήση στο UI Thread
    @Override
    protected void onResume() {
        super.onResume();
    }

    // Running 3 callbacks
    //όταν η εφαρμογή δεν ειναι στο foreground παει στο back και ο
    //χρηστης δε την βλεπει αρα απο active -> γίνεται idle

    //Το OS μας ενημερώνει ότι απο foreground παμε background
    @Override
    protected void onPause() {
        super.onPause();
    }

    //Το OS μας ενημερώνει ότι αυτο το instance που ήτανε στο foreground έχει
    //γίνει μετάβαση στο Background...σταμάτησε να είναι focus στο χρήστη
    @Override
    protected void onStop() {
        stopped();
        super.onStop();
    }

    // onDestroy() καλείται όταν το OS καταστρέφει το app.
    // και κάνει release τα resources του application.
    @Override
    protected void onDestroy() {
        destroyOperation();
        super.onDestroy();
    }

}
