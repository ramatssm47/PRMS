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
public class SlotAlreadyExistsException extends Exception {

    public SlotAlreadyExistsException() {
        String message;
        message="Selected slot already Occupied, Please select different slot!";
    }
    
}
