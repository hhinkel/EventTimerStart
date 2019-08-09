package com.example.eventtimerstart;

import android.content.Context;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroid.Client;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttHelper {

    public MqttAndroidClient mqttAndroidClient;

    final String serverUri = "tcp://ad_astra/feeds.event-timer:8885";

    final String clientId = "1148847";
    final String subscriptionTopic = "startTime/+";

    final String username = "ad_astra";
    final String key = "0a8f97b274ad4cd785e8ff3350838110";

    public MqttHelper(Context context) {
        mqttAndroidClient = new MqttAndroidClient(context, serverUri, clientId);
        mqttAndroidClient.setCallback(new MqttCallbackExtended(){
           @Override
           public void connectComplete(boolean b, String s) {
               Log.w("mqtt", s);
           }

           @Override
           public void connectionLost(Throwable throwable){

           }

           @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
               Log.w("Mqtt", mqttMessage.toString());
           }

           @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken){

           }

        });
        connect();
    }

    public void setConnect(MqttCallbackExtended callback) {
        mqttAndroidClient.setCallback(callback);
    }

}


