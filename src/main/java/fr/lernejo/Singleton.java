package fr.lernejo;

public class Singleton {

        private static final Singleton instance = new Singleton();
    
        public static Singleton getInstance() {
            return instance;
        }
    
        public String supplySomeStr(int offset) {
            return String.valueOf(43 + offset);
        }
}

