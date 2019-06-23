//package com.vinodh.config;
//
//import com.bol.crypt.CryptVault;
//import com.bol.crypt.FieldCryptException;
//import com.bol.secure.AbstractEncryptionEventListener;
//import com.bol.secure.Encrypted;
//import org.bson.Document;
//import org.springframework.data.mongodb.core.mapping.event.AfterConvertEvent;
//import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ReflectionUtils;
//
//import java.lang.reflect.Field;
//import java.util.Map;
//
///**
// * @author thimmv
// * @createdAt 23-06-2019 22:40
// */
////@Component
//public class MongoEncryptionListener extends AbstractEncryptionEventListener<MongoEncryptionListener> {
//
//    public MongoEncryptionListener(CryptVault cryptVault) {
//        super(cryptVault);
//    }
//
//    @Override
//    public void onBeforeConvert(BeforeConvertEvent event) {
//        Class<? extends String> targetClass = event.getCollectionName().getClass();
//        Document targetDocument = event.getDocument();
//        for (Map.Entry<String, Object> fieldsMap : targetDocument.entrySet()) {
//            String fieldName = fieldsMap.getKey();
//            if (fieldName.equals("_class")) continue;
//            Field field = ReflectionUtils.findField(targetClass, fieldName);
//            field.setAccessible(true);
//            if (field == null) continue;
//
//            Object fieldValue = fieldsMap.getValue();
//            if (field.isAnnotationPresent(Encrypted.class)) {
//                // direct encryption
//                try {
//                    targetDocument.put(fieldName, cryptVault.encrypt(fieldValue.toString().getBytes()));
//                } catch (Exception e) {
//                    throw new FieldCryptException(fieldName, e);
//                }
//
//            }
//            field.setAccessible(true);
//        }
//    }
//
//    @Override
//    public void onAfterConvert(AfterConvertEvent event) {
//        Class<? extends String> targetClass = event.getCollectionName().getClass();
//        Document targetDocument = event.getDocument();
//        for (Map.Entry<String, Object> fieldsMap : targetDocument.entrySet()) {
//            String fieldName = fieldsMap.getKey();
//            if (fieldName.equals("_class")) continue;
//            Field field = ReflectionUtils.findField(targetClass, fieldName);
//            field.setAccessible(true);
//            if (field == null) continue;
//
//            Object fieldValue = fieldsMap.getValue();
//            if (field.isAnnotationPresent(Encrypted.class)) {
//                // direct encryption
//                try {
//                    targetDocument.put(fieldName, cryptVault.decrypt(fieldValue.toString().getBytes()));
//                } catch (Exception e) {
//                    throw new FieldCryptException(fieldName, e);
//                }
//
//            }
//            field.setAccessible(true);
//        }
//    }
//}
