
// Powered by https://www.nexmo.com/

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sender;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.sms.SmsSubmissionResult;
import com.nexmo.client.sms.messages.TextMessage;
import com.nexmo.client.auth.JWTAuthMethod; 
import com.nexmo.client.auth.TokenAuthMethod;

/**
 *
 * @author lgraml, mwitzlsperger
 */
public class SMSSender {
    // create the HttpURLConnection
    
    /**
     * Methode zum Senden von SMS
     * @param empfaenger Nummer des Nachrichtenziels (Nummer des Epfängers)
     * @param message Inhalt der Nachricht
     */
    public static void sendSMS(String empfaenger, String message) {
        try {
            AuthMethod auth = new TokenAuthMethod("dae3d3bf","27d45ac64cd4d222" );
            NexmoClient client = new NexmoClient(auth);
            //System.out.println("+491601899824");

            String[] empf = empfaenger.split(",");
            
            for (int i = 0; i<=empf.length; i++) {
                SmsSubmissionResult[] responses = client.getSmsClient().submitMessage(new TextMessage(
                    "+491601899824", // Sender
                    empf[i], // Empfänger
                    message));
                for (SmsSubmissionResult response : responses) {
                    System.out.println(response);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
