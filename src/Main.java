public class Main {
    public static void main(String[] args){
        ServicoEmail servicoEmail = new ServicoEmail();
        ServicoSMS servicoSMS = new ServicoSMS();

        RecuperadorDeSenha recuperadorDeSenhaParaEmail = new RecuperadorDeSenha(servicoEmail) ;
        RecuperadorDeSenha recuperadorDeSenhaPorSMS = new RecuperadorDeSenha(servicoSMS);

        recuperadorDeSenhaPorSMS.recuperar();
        recuperadorDeSenhaParaEmail.recuperar();
    }
}
