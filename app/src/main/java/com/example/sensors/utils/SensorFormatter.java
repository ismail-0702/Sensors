package com.example.sensors.utils;

import android.hardware.Sensor;

public class SensorFormatter {

    /**
     * Formate les informations d'un capteur pour un affichage propre.
     */
    public static String format(Sensor sensor) {
        if (sensor == null) {
            return "Capteur inconnu";
        }

        // Il manquait l'initialisation du StringBuilder ici
        StringBuilder builder = new StringBuilder();

        builder.append("📌 ").append(sensor.getName().toUpperCase()).append("\n");
        builder.append("Type: ").append(getSensorTypeName(sensor.getType())).append("\n");
        builder.append("Fournisseur: ").append(sensor.getVendor()).append("\n");
        builder.append("Consommation: ").append(sensor.getPower()).append(" mA\n");
        builder.append("Portée Max: ").append(sensor.getMaximumRange()).append("\n");
        builder.append("Résolution: ").append(sensor.getResolution());

        return builder.toString();
    }

    /**
     * Transforme le type entier du capteur en un nom compréhensible.
     */
    private static String getSensorTypeName(int type) {
        switch (type) {
            case Sensor.TYPE_ACCELEROMETER: return "Accéléromètre";
            case Sensor.TYPE_GYROSCOPE: return "Gyroscope";
            case Sensor.TYPE_MAGNETIC_FIELD: return "Champ Magnétique";
            case Sensor.TYPE_PROXIMITY: return "Proximité";
            case Sensor.TYPE_LIGHT: return "Luminosité";
            case Sensor.TYPE_AMBIENT_TEMPERATURE: return "Température";
            case Sensor.TYPE_RELATIVE_HUMIDITY: return "Humidité";
            case Sensor.TYPE_PRESSURE: return "Pression";
            case Sensor.TYPE_GRAVITY: return "Gravité";
            case Sensor.TYPE_LINEAR_ACCELERATION: return "Accélération Linéaire";
            case Sensor.TYPE_ROTATION_VECTOR: return "Vecteur de Rotation";
            case Sensor.TYPE_STEP_COUNTER: return "Compteur de pas";
            default: return "Autre (Type " + type + ")";
        }
    }
}