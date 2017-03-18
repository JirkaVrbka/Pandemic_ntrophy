/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.globals;

/**
 * Global variables to store and probably change
 * @author Jiri Vrbka
 */
public class Global {
    
    public static class VariablesDiseases{
        private static int mortalityAging = 2000;

        
        /**
         * Get mortality for aging
         * @return number bigger than 0
         */
        public static int getMortalityAging() {
            return mortalityAging;
        }

        /**
         * Set mortality of aging.
         * If @param is smaller than zero, zero will be set
         * @param MORTALITY_AGIGNG number
         */
        public static void setMortalityAging(int mortalityAging) {
            
            VariablesDiseases.mortalityAging = mortalityAging >= 0 ? mortalityAging : 0;
        }
        
        
        
        
    }
    
    public  static class  Variables{
        /**
        * Should be between 0-100
        */
        private static int PERSON_IMMUNITY = 95;
        
        private static int PERSON_MORTALITY = 1000;
        
        public static int PERSON_AVERAGE_LIVE = 10000;
    
        public static final int TIMELINE_SPEED_DEFAULT = 50;
        
        public static int getPersonImunity(){
            return PERSON_IMMUNITY;
        }
        
        public static void setPersonImmunity(int immunity){
            if(immunity > 100 || immunity < 0){
                throw new IllegalArgumentException("immunity has to be in range 0-100 and is "+immunity);
            }
            
            PERSON_IMMUNITY = immunity;
        }

        public static int getPersonMortality() {
            return PERSON_MORTALITY;
        }

        public static void setPersonMortality(int PERSON_MORTALITY) {
            if(PERSON_MORTALITY < 0){
                throw new IllegalArgumentException("mortality has to be in bigger than 0 and is "+PERSON_MORTALITY);
            }
            
            Variables.PERSON_MORTALITY = PERSON_MORTALITY;
        }
    }
    
}
