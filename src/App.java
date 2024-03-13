import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.util.Scanner;
import com.db4o.query.Query;
import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) throws Exception {
        ObjectContainer db = Db4o.openFile("aulu.txt");
        Scanner entrada = new Scanner(System.in);

        int opc = 0;

        while (opc != 5) {

            opc = Integer.parseInt(JOptionPane.showInputDialog(""
                    + "Qual opção deseja realizar?\n"
                    + "(1) - Registar Pokemon\n"
                    + "(2) - Ver a sua Pokédex\n"
                    + "(3) - Atualizar um Pokemon\n"
                    + "(4) - Deletar um Pokemon\n"
                    + "(5) - Sair da Pokédex"));

            switch (opc) {

                // caso pra adicionar um registro no banco de dados
                case 1:
                    Pokedex poke = new Pokedex(0, null, null, null);
                    poke.setNome((JOptionPane.showInputDialog("Qual nome do seu novo Pokemon?")));
                    poke.setID((Integer.parseInt(JOptionPane.showInputDialog("Qual ID do Pokemon?"))));
                    poke.setTipo((JOptionPane.showInputDialog("Qual tipo do seu Pokemon?")));
                    poke.setRegiao(JOptionPane.showInputDialog("De qual região é seu Pokemon?"));

                    // codigo pra armazenar o livro no db
                    db.store(poke);
                    break;

                // caso pra ver a lista toda de livros
                case 2:
                    ObjectSet<Pokedex> lista = db.queryByExample(Pokedex.class);

                    if (lista.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nenhum Pokemon na sua Pokédex.");
                    } else {
                        // StringBuilder é um String mutavel
                        StringBuilder MsgLista = new StringBuilder("Pokédex:\n");

                        for (Pokedex c : lista) {
                            MsgLista.append(c).append("\n"); // .append aqui é pra colocar mais string[q são os nomes e
                                                             // os codigo] e quebrar a linha, ai o db sai certinho
                        }
                        JOptionPane.showMessageDialog(null, MsgLista.toString()); // converte o StringBuilder de volta
                                                                                  // pra string normal e mostra o
                                                                                  // resultado
                    }
                    break;

                case 3:
                    int IdAtt = Integer
                            .parseInt(JOptionPane.showInputDialog("Qual ID do Pokemon que deseja atualizar?"));

                    Query consult = db.query(); // cria um objeto pra realizar a consulta no db
                    consult.constrain(Pokedex.class); // mostra onde vai ser a consulta(na classe poke)
                    consult.descend("ID").constrain(IdAtt); // ai procura o codigo pra atualizar

                    ObjectSet<Pokedex> resultado = consult.execute(); // executa o codigo em cima e armazena em
                                                                      // "resultado"

                    if (resultado.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "ID não encontrado!");
                    } else {
                        poke = resultado.next();

                        poke.setNome(JOptionPane.showInputDialog("Qual será o nome do novo Pokemon?"));
                        poke.setID(Integer.parseInt(JOptionPane.showInputDialog("Qual será o novo ID?")));
                        poke.setTipo(JOptionPane.showInputDialog("Qual é o tipo do novo Pokemon?"));
                        poke.setRegiao(JOptionPane.showInputDialog("De que região é o novo Pokemon"));

                        JOptionPane.showMessageDialog(null, "Pokédex atualizada!");
                    }

                    break;

                case 4:
                    int IdDel = Integer.parseInt(JOptionPane.showInputDialog("Qual ID do Pokemon que deseja excluir?"));

                    Query consultDel = db.query();// cria um objeto pra realizar a consulta no db
                    consultDel.constrain(Pokedex.class);// mostra onde vai ser a consulta(na classe pokeente)
                    consultDel.descend("ID").constrain(IdDel);// ai procura o codigo pra atualizar

                    ObjectSet<Pokedex> resultadoDel = consultDel.execute();// executa o codigo em cima e armazena em
                                                                           // "resultado"

                    if (resultadoDel.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "ID não encontrado!");
                    } else {
                        poke = resultadoDel.next();

                        db.delete(poke); // comando pra deletar do db

                        JOptionPane.showMessageDialog(null, "Pokemon excluído :(");

                    }

                    break;

                case 5:
                    JOptionPane.showMessageDialog(null, "Obrigado por usar sua Pokédex!");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Insira um valor válido."); // :)))
                    break;

            }
        }
        db.close();// fecha o db
        entrada.close();// fecha o scanner

    }
}
// q lindo
