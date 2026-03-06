public class ServicoSMS implements NotificationService{


    @Override
    public void enviar(String mensagem) {
        System.out.println("Enviando SMS SMTP: " + mensagem);
    }
}
