package net.accedegh.signalr_client;

import android.os.AsyncTask;

import java.util.concurrent.ExecutionException;

import microsoft.aspnet.signalr.client.hubs.HubConnection;
import microsoft.aspnet.signalr.client.hubs.HubProxy;

/**
 * Created by FrankOdoom on 08/07/17.
 */


public  class SignalRClient {
    public static HubConnection connection;
    public static HubProxy hubProxy;
    public static String serverUrl;

    // Configure SignalR Client HUb Proxy
    public static HubProxy ConfigureProxy(String HubProxy){
        connection = new HubConnection(serverUrl);
        hubProxy= connection.createHubProxy(HubProxy);
        return hubProxy;
    }

    public static void setServerUrl(String url) {
        serverUrl = url;
    }

    public static HubConnection getConnection() {
        return connection;
    }

    public static HubProxy getHubProxy() {
        return hubProxy;
    }


    public static void connectSignalr() {
        try {
            //Toast.makeText(this, "Connecting to SignalR Service", Toast.LENGTH_SHORT).show();
            SignalRConnectTask signalRConnectTask = new SignalRConnectTask();
            signalRConnectTask.execute(connection);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Establish Connection in Background Async Task
    public static class SignalRConnectTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            HubConnection connection = (HubConnection) objects[0];
            try {
                Thread.sleep(1000);
                connection.start().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
