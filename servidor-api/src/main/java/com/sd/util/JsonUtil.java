package com.sd.util; // 1. CORREÇÃO: Package ajustado

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Utilitário para operações com JSON e serialização.
 * Ajustado para remover referências ao pacote antigo br.ufc.
 */
public class JsonUtil {
    
    public static String toJson(Object obj) {
        if (obj == null) return "null";
        
        if (obj instanceof String) {
            return "\"" + ((String) obj).replace("\"", "\\\"") + "\"";
        }

        if (obj instanceof Number || obj instanceof Boolean) {
            return obj.toString();
        }

        if (obj instanceof Object[]) {
            Object[] arr = (Object[]) obj;
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < arr.length; i++) {
                sb.append(toJson(arr[i]));
                if (i < arr.length - 1) sb.append(",");
            }
            sb.append("]");
            return sb.toString();
        }

        if (obj instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) obj;
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            int i = 0;
            for (Map.Entry<String, Object> e : map.entrySet()) {
                sb.append("\"").append(e.getKey()).append("\":");
                sb.append(toJson(e.getValue()));
                if (i++ < map.size() - 1) sb.append(",");
            }
            sb.append("}");
            return sb.toString();
        }

        return "\"" + obj.toString().replace("\"", "\\\"") + "\"";
    }

    public static Map<String, String> fromJsonToMap(String json) {
        Map<String, String> map = new HashMap<>();
        if (json == null) return map;
        json = json.trim();
        if (json.startsWith("{") && json.endsWith("}")) {
            json = json.substring(1, json.length() - 1).trim();
            if (json.isEmpty()) return map;
            String[] parts = json.split(",");
            for (String p : parts) {
                String[] kv = p.split(":", 2);
                if (kv.length == 2) {
                    String k = kv[0].trim().replace("\"", "");
                    String v = kv[1].trim().replace("\"", "");
                    map.put(k, v);
                }
            }
        }
        return map;
    }

    public static String errorToJson(Exception e) {
        String msg = e == null ? "Erro desconhecido" : e.getMessage();
        return "{\"error\":\"" + (msg != null ? msg.replace("\"", "\\\"") : "null") + "\"}";
    }

    // --- REMOVIDOS OS MÉTODOS QUE USAVAM br.ufc.qxd.protocol ---
    // Em uma API REST, o SparkJava e o Gson cuidarão disso automaticamente.
    
    public static boolean isValidJson(String json) {
        if (json == null || json.isEmpty()) return false;
        json = json.trim();
        return (json.startsWith("{") && json.endsWith("}")) ||
               (json.startsWith("[") && json.endsWith("]"));
    }
    
    public static String inspecionarObjeto(Object obj) {
        if (obj == null) return "null";
        StringBuilder sb = new StringBuilder();
        sb.append("Classe: ").append(obj.getClass().getName()).append("\n");
        sb.append("Serializável: ").append(obj instanceof Serializable ? "Sim" : "Não").append("\n");
        sb.append("toString(): ").append(obj.toString());
        return sb.toString();
    }
}