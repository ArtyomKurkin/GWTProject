package com.netcracker.client;

public class Validator{

        public boolean isValidYear(String str){
            try {
                int enterYear = Integer.parseInt(str);
                    if ((enterYear <= 2019) && (enterYear > 0)) {
                        return true;
                    } else {return false;}
            }
            catch (NumberFormatException e){return false;}
        }
        public boolean isValidPages(String str){
            try {
                int enterPages = Integer.parseInt(str);
                    if (enterPages > 0) {
                        return true;
                    } else return false;
            }
            catch (NumberFormatException e){return false;}
        }

        public boolean isValidId(String str){
            try {
                int enterId = Integer.parseInt(str);
                    if (enterId > 0) {
                        return true;
                    } else {return false;}
                }

            catch (NumberFormatException e){return false;}
        }

    }
