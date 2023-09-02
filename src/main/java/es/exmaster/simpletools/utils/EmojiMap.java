package es.exmaster.simpletools.utils;

import java.util.HashMap;
import java.util.Map;

import java.util.*;

public class EmojiMap<K extends String, V extends String> implements Map<String, String> {

    private Map<String, String> map;

    public EmojiMap() {
        map = new HashMap<>();
        map.put(":skull:", "☠");
        map.put(":happy:", "☺");
        map.put(":sad:", "☹");
        map.put(":love:", "❤");
        map.put(":fishingrod:", "🎣");
        map.put(":combat:", "⚔");
        map.put(":coffee:", "☕");
        map.put(":sun:", "☀");
        map.put(":cloud:", "☁");
        map.put(":snow:", "☃");
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return map.get(key);
    }

    @Override
    public String put(String key, String value) {
        return map.put(key, value);
    }

    @Override
    public String remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<String> values() {
        return map.values();
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        return map.entrySet();
    }

    public void addEmoji(String key, String value) {
        map.put(key, value);
    }
    
    public String getEmoji(String key) {
    	return map.get(key);
    }
}