package fr.lernejo;

public sealed interface Image {

    void display();

    final class RealImage implements Image {

        private final String fileName;

        public RealImage(String fileName){
            this.fileName = fileName;
            loadFromDisk(fileName);
        }

        @Override
        public void display() {
            System.out.println("Displaying " + fileName);
        }

        private void loadFromDisk(String fileName){
            System.out.println("Loading " + fileName);
        }
    }

    final class LazyLoadedImage implements Image{

        private RealImage realImage;
        private final String fileName;

        public LazyLoadedImage(String fileName){
            this.fileName = fileName;
        }

        @Override
        public void display() {
            if(realImage == null){
                realImage = new RealImage(fileName);
            }
            realImage.display();
        }
    }
}
