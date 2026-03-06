
# 🏗️ Refatoração: Inversão de Dependência (DIP) e Injeção de Dependência

Este projeto demonstra como transformar um código com alto acoplamento em uma arquitetura flexível e escalável, aplicando o quinto princípio do **SOLID**: **DIP (Dependency Inversion Principle)**.

---

# 1. O Problema: Mau Design (Acoplamento Rígido)

No design original, a classe `RecuperadorDeSenha` estava excessivamente acoplada à classe concreta `ServicoEmail`.

### Riscos do Código Antigo

- **Dependência Concreta:** `RecuperadorDeSenha` instanciava diretamente `ServicoEmail`.
- **Dificuldade de Evolução:** Para trocar o envio de email por SMS seria necessário alterar a classe principal.
- **Dificuldade de Testes:** Testar a lógica dispararia emails reais.

Exemplo de código com problema:

public class RecuperadorDeSenha {

    private ServicoEmail servicoEmail = new ServicoEmail();

    public void recuperar() {
        String link = "http://techstore.com/reset?token=123";
        servicoEmail.enviarEmail("Clique no link para resetar sua senha: " + link);
    }

}

class ServicoEmail {

    public void enviarEmail(String mensagem) {
        System.out.println("Enviando E-mail SMTP: " + mensagem);
    }

}

---

# 2. A Solução: Aplicando DIP

O **Dependency Inversion Principle** diz que:

> Módulos de alto nível não devem depender de módulos de baixo nível. Ambos devem depender de **abstrações**.

A solução é criar uma **interface** que represente a comunicação.

---

# 2.1 Definindo a Interface (Abstração)

public interface Comunicador {

    void enviar(String mensagem);

}

---

# 2.2 Implementando os Serviços

Agora criamos diferentes implementações da interface.

## Serviço de Email

public class ServicoEmail implements Comunicador {

    @Override
    public void enviar(String mensagem) {
        System.out.println("Enviando E-mail via protocolo SMTP: " + mensagem);
    }

}

## Serviço de SMS

public class ServicoSMS implements Comunicador {

    @Override
    public void enviar(String mensagem) {
        System.out.println("Enviando SMS via Gateway de Telefonia: " + mensagem);
    }

}

---

# 2.3 Refatorando a Classe de Negócio

Agora o `RecuperadorDeSenha` **não depende mais de uma implementação concreta**, mas sim da **interface**.

A dependência é recebida pelo **construtor**.

public class RecuperadorDeSenha {

    private final Comunicador comunicador;

    public RecuperadorDeSenha(Comunicador comunicador) {
        this.comunicador = comunicador;
    }

    public void recuperar() {
        String link = "http://techstore.com/reset?token=123";
        comunicador.enviar("Clique no link para resetar sua senha: " + link);
    }

}

---

# 3. Demonstração de Flexibilidade (Main)

Agora podemos escolher qual serviço utilizar **sem alterar a classe de negócio**.

public class Main {

    public static void main(String[] args) {

        // Criamos as implementações
        Comunicador email = new ServicoEmail();
        Comunicador sms = new ServicoSMS();

        // Cenário 1: recuperação por email
        RecuperadorDeSenha recuperadorEmail = new RecuperadorDeSenha(email);
        recuperadorEmail.recuperar();

        // Cenário 2: recuperação por SMS
        RecuperadorDeSenha recuperadorSms = new RecuperadorDeSenha(sms);
        recuperadorSms.recuperar();

    }

}

---

# 🚀 Benefícios da Aplicação do DIP

## Desacoplamento
A lógica de negócio não depende mais de implementações específicas.

## Extensibilidade
Para adicionar um novo canal (WhatsApp, Push Notification, etc), basta criar uma nova classe que implemente `Comunicador`.

Exemplo:

public class ServicoWhatsApp implements Comunicador {

    @Override
    public void enviar(String mensagem) {
        System.out.println("Enviando mensagem via WhatsApp: " + mensagem);
    }

}

## Testabilidade
Agora é possível criar **mocks** para testes unitários.

## Flexibilidade
O sistema pode trocar facilmente o meio de comunicação sem alterar a lógica principal.

---

# Conclusão

A aplicação do **Dependency Inversion Principle (DIP)** reduz o acoplamento entre as classes e aumenta a flexibilidade do sistema.

A arquitetura passa a depender de **abstrações**, permitindo evolução, testes e manutenção muito mais simples.


