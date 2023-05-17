package com.travelease.nitant;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText etRecoverEmail;
    Button btnRecover,btnValidate;
    APIInterface apiInterface;
    List<Recover> recoverdata;
    String userEmail,userPassword,userName;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        getID();
        btnRecover.setVisibility(View.GONE);

        apiInterface = APIClient.getAvik().create(APIInterface.class);


        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String receiverEmail=etRecoverEmail.getText().toString().trim();
                if(receiverEmail.isEmpty())
                {
                    etRecoverEmail.setError("Email is Required");
                    etRecoverEmail.requestFocus();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(receiverEmail).matches())
                {
                    etRecoverEmail.setError("Enter Valid Email ID");
                    etRecoverEmail.requestFocus();
                    return;
                }


                Call<RecoverPW> call = apiInterface.recoverdata(receiverEmail);

                call.enqueue(new Callback<RecoverPW>() {
                    @Override
                    public void onResponse(Call<RecoverPW> call, Response<RecoverPW> response) {
                        if (response.body() != null) {
                            recoverdata =  response.body().getRecover();
                            Recover recovermodel = new Recover();
                            recovermodel = recoverdata.get(0);
                            userPassword =recovermodel.getPassword();
                            userEmail=recovermodel.getEmail();
                            userName=recovermodel.getUname();
                            btnRecover.setVisibility(View.VISIBLE);
//                            Toast.makeText(ForgotPasswordActivity.this, ""+recovermodel.getPassword(), Toast.LENGTH_SHORT).show();
//                            Toast.makeText(ForgotPasswordActivity.this, ""+recovermodel.getEmail(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RecoverPW> call, Throwable t) {
                        Toast.makeText(ForgotPasswordActivity.this, "No Account Found" , Toast.LENGTH_SHORT).show();
                        etRecoverEmail.setError("No account found with this email address");
                        btnRecover.setVisibility(View.GONE);
                        etRecoverEmail.requestFocus();
                        return;
                    }
                });


                //
                btnRecover.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            String stringSenderEmail = "TravelEase23@gmail.com";
                            String stringReceiverEmail = userEmail;
                            String stringPasswordSenderEmail = "uozapllavomjqljz";

                            String stringHost = "smtp.gmail.com";

                            Properties properties = System.getProperties();

                            properties.put("mail.smtp.host", stringHost);
                            properties.put("mail.smtp.port", "465");
                            properties.put("mail.smtp.ssl.enable", "true");
                            properties.put("mail.smtp.auth", "true");

                            javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
                                @Override
                                protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(stringSenderEmail, stringPasswordSenderEmail);
                                }
                            });

                            MimeMessage mimeMessage = new MimeMessage(session);
                            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(stringReceiverEmail));

                            mimeMessage.setSubject("Subject: Recover Password");
                            mimeMessage.setText("Hello "+userName+", \n\nYour account in TravelEase with Email :"+userEmail+" \n\n Your Password is :"+userPassword);

                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Transport.send(mimeMessage);
                                    } catch (MessagingException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            thread.start();
                            Toast.makeText(ForgotPasswordActivity.this, "Your Password is send to your email ", Toast.LENGTH_LONG).show();
                            handler.postDelayed(toast,2000);
                        } catch (AddressException e) {
                            e.printStackTrace();
                        } catch (MessagingException e) {
                            e.printStackTrace();
                        }
                    }
                });


            }
        });
    }

    private void getID() {
        etRecoverEmail= findViewById(R.id.et_forgotpw_recoverEmail);
        btnRecover= findViewById(R.id.btn_forgotpw_recover);
        btnValidate= findViewById(R.id.btn_forgotpw_validate);

    }

    private Runnable toast = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(ForgotPasswordActivity.this, "Check Spam folder if not received", Toast.LENGTH_LONG).show();
        }
    };
}