import java.io.*;

class GeradorCodigo implements Serializable {

    // declara as variaveis
    private static final String ARQUIVO_CODIGO = "codigo.dat";
    private static int codigoAtual = 0;

    /*
     * Função para gerar um novo codigo
     * Se o codigo atual estiver em zero
     * chama a função para ler o codigo do arquivo
     * aumenta em 1 o codigo
     * salva o codigo no arquivo
     * retorna o codigo atual
     */
    public static int gerarCodigo() {
        if (codigoAtual == 0) {
            lerCodigoDoArquivo();
        }

        codigoAtual++;
        salvarCodigoNoArquivo();

        return codigoAtual;
    }

    /*
     * Função para ler o código do arquivo.
     * Se o arquivo existir,
     * utiliza um ObjectInputStream para ler o objeto (o código) armazenado no
     * arquivo.
     * O código lido é atribuído à variável codigoAtual.
     */
    private static void lerCodigoDoArquivo() {
        try {
            File arquivo = new File(ARQUIVO_CODIGO);
            if (arquivo.exists()) {
                ObjectInputStream input = new ObjectInputStream(new FileInputStream(ARQUIVO_CODIGO));
                codigoAtual = (int) input.readObject();
                input.close();
            }
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
    }

    /*
     * Função para salvar o código atual no arquivo.
     * Utiliza um ObjectOutputStream para escrever o código no arquivo.
     */
    private static void salvarCodigoNoArquivo() {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(ARQUIVO_CODIGO));
            output.writeObject(codigoAtual);
            output.close();
        } catch (IOException e) {
        }
    }
}