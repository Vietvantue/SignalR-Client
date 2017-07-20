
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/2a6d1b427f474b168240289fa4bcb3ea)](https://www.codacy.com/app/frankodoom/SignalR-Client?utm_source=github.com&utm_medium=referral&utm_content=frankodoom/SignalR-Client&utm_campaign=badger)
[![](https://jitpack.io/v/frankodoom/SignalR-Client.svg)](https://jitpack.io/#frankodoom/SignalR-Client)
# SignalR-Client

An easy to use SignalR client Android Library for real-time mobile communication. Just compile this library into your project
and you are ready to use SignalR in Android

SignalR requires internet permision, add this in your  `AndroidManifest.xml `

 `<uses-permission android:name="android.permission.INTERNET"/> `

Add this to your root build.gradle at the end of repositories

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  Add and compile the dependency for SignalR-Client library
  
  	dependencies {
	        compile 'com.github.frankodoom:SignalR-Client:1.0.1'
	}
 
 using the SignalRClient
    
    //Setup the server url 
    SignalRClient.setServerUrl("https://yourServerUrl");
    
    //Configure the Proxy    
      SignalRClient.ConfigureProxy("ChatHub")
          .subscribe("broadcastMessage")
          .addReceivedHandler(new Action<JsonElement[]>() {
          
          @Override
          public void run(JsonElement[] jsonElements) throws Exception {
              //Do something when Json Array Returns
             }
           });
           
       //Open the Socket Connection    
       SignalRClient.openConnection(); 
       

   Manage Connection States


        SignalRClient.getConnection().stateChanged(new StateChangedCallback() {
            @Override
            public void stateChanged(ConnectionState connectionState, ConnectionState connectionState2) {
                //Do Something when connection state changes
            }
        });


        SignalRClient.getConnection().closed(new Runnable() {
            @Override
            public void run() {
             //On connection closed you can open connection
             SignalRClient.openConnection();
            }
        });


Invoking Serverside Method
 This should be done in the background in order not to freeze the UI Thread
     
     public interface SignalRService {
      
      public class SendMessage extends AsyncTask {
        static Object result;
        @Override
        protected Object doInBackground( Object[] objects) {
            if (SignalRClient.getConnection().getState() == ConnectionState.Connected) {
                result = null;
                Object param1 = objects[0];
                //Object param2 = objects[1];
                try {
                    result = SignalRClient.getHubProxy().invoke(String.class, "broadcastMessage",param1).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            // Тут идет обработка результата.
        }`


Invoke the Service	
   
     //Send Message On Click
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatMessage = message.getText().toString();
                new SignalRService.ChatService().execute(chatMessage); //Execute The SignalRService
                message.setText("");
            }
        });
