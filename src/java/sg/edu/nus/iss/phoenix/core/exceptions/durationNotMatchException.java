/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.edu.nus.iss.phoenix.core.exceptions;

/**
 *
 * @author a0120086r
 */
public class durationNotMatchException extends Exception {

    public durationNotMatchException() {
        String message;
        message="Program duration doesn't match Time Slot";
    }
    
}
