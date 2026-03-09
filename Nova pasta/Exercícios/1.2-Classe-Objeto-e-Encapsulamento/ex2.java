public class ex2 {
    class Roda {
        private double raio;
        private String material;
        private double peso;
        private double pesoMax;
        
        public Roda() {
            this.raio = 0.0;
            this.material = "";
            this.peso = 0.0;
            this.pesoMax = 0.0;
        }
        
        private void setRaio(double raio) {
            this.raio = raio;
        }
        private double getRaio() {
            return this.raio;
        }
        
        private void setMaterial(String material) {
            this.material = material;
        }
        private String getMaterial() {
            return this.material;
        }
        
        private void setPeso(double peso) {
            this.peso = peso;
        }
        private double getPeso() {
            return this.peso;
        }
        
        private void setPesoMax(double pesoMax) {
            this.pesoMax = pesoMax;
        }
        private double setPesoMax() {
            return this.pesoMax;
        }
    }
    
    public void main(String args[]) {
        Roda aro17 = new Roda();
        
        aro17.setRaio(14);
        aro17.setMaterial("Aco");
        aro17.setPeso(20);
        aro17.setPesoMax(1000);
        System.out.println("Material: " + aro17.getMaterial());
    }
}
