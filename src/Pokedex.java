public class Pokedex {
    private int ID;
    private String Nome;
    private String Tipo;
    private String Regiao;
    


    public Pokedex(int ID, String Nome, String Tipo, String Regiao) {
        this.ID = ID;
        this.Nome = Nome;
        this.Tipo = Tipo;
        this.Regiao = Regiao;
    }
    
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
    
    public String getNome() {
        return Nome;
    }
    public void setNome(String nome) {
        Nome = nome;
    }
    
    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getRegiao() {
        return Regiao;
    }

    public void setRegiao(String regiao) {
        Regiao = regiao;
    }

    @Override
    public String toString() {
        return "ID do Pokemon: " + ID +
                "\nNome do Pokemon: " + Nome +
                "\nTipo do Pokemon: " + Tipo +
                "\nRegião: " + Regiao + "\n";
}
}
