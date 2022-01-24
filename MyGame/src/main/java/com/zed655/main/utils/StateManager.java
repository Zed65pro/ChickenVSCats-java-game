
package com.zed655.main.utils;

import com.zed655.main.states.State;

public class StateManager {
    
     //State Manager functions - all static to get em anytime
    private static State currentState=null; // curennt state of the game
    
    public static void setState(State state){
        currentState = state;
    }
    public static State getState(){
        return currentState;
    }
    //How this works? we have a global state to work on, we set it according to our game functions
    //eg: we start with menu state, by clicking on start we setState() -> gamestate-level1 
    // after completing level 1 we move to level 2 etc
}
