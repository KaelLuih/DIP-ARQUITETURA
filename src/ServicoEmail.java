public class ServicoEmail implements NotificationService{


    @Override
    public void enviar(String mensagem) {
        System.out.println("Enviando E-mail SMTP: " + mensagem);
    }
}
