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
	        compile 'com.github.frankodoom:SignalR-Client:1.0.0'
	}
 
 using the SignalRClient
    
    
    SignalRClient.setServerUrl("https://yourServerUrl");//Setup the server url 
    
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

