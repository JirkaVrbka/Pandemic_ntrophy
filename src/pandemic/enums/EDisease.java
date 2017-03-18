/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pandemic.enums;

/**
     * Represents type of disease
     */
    public enum EDisease {
        FLU,
        DIABETES,
        RADIATION,
        RAGE,
        ZOMBIE,
        LOVERS,
        PATOGEN;
        
        /**
         * Get EDisease by its integer value
         * @param toSelect to be selected by
         * @return EDiesease
         */
        public static EDisease byInteger(int toSelect){
            return EDisease.values()[toSelect % EDisease.values().length];
        }
        
    }
